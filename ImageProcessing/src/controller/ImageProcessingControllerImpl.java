package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import controller.commands.ImageCommand;
import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
import controller.commands.Save;
import model.ImageProcessingModel;
import model.image.MyImage;
import model.image.SimpleImage;
import model.image.operations.BlueGreyscaleImageOperation;
import model.image.operations.BlurImageOperation;
import model.image.operations.BrightenImageOperation;
import model.image.operations.DarkenImageOperation;
import model.image.operations.FlipHorizontallyImageOperation;
import model.image.operations.FlipVerticallyImageOperation;
import model.image.operations.GreenGreyscaleImageOperation;
import model.image.operations.GreyscaleImageOperation;
import model.image.operations.IntensityGreyscaleImageOperation;
import model.image.operations.LumaGreyscaleImageOperation;
import model.image.operations.RedGreyscaleImageOperation;
import model.image.operations.SepiaImageOperation;
import model.image.operations.SharpenImageOperation;
import model.image.operations.ValueGreyscaleImageOperation;
import model.pixel.Pixel;
import model.pixel.RGBAPixel;
import model.pixel.TransparentPixel;
import view.ImageProcessingView;

/**
 * This class represents an implementation of a controller for an image processing application. It
 * specifically implements the ImageProcessingController interface. It is able to read and write
 * (load and save) PPM, JPG, PNG, and BMP images. The operations it currently supports when
 * executing the program are the following script commands: load, red-greyscale, green-greyscale,
 * blue-greyscale, value-greyscale, intensity-greyscale, luma-greyscale, flip-horizontally,
 * flip-vertically, brighten, darken, blur, sharpen, greyscale, sepia, and save.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private ImageProcessingModel model;
  private ImageProcessingView view;
  private Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands;
  private Scanner input;

  /**
   * Creates a controller with the given model, view and scanner with the given Readable object.
   * Additionally, a container of known commands is initialized.
   *
   * @param model the model being used
   * @param view the view being used
   * @param in the Readable object being used
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public ImageProcessingControllerImpl(
          ImageProcessingModel model, ImageProcessingView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Model, view, and Readable object cannot be null!");
    }
    this.model = model;
    this.view = view;
    this.input = new Scanner(in);
    this.knownCommands = new HashMap<String, Function<Scanner, ImageProcessingCommand>>();
    this.knownCommands.put("load", (Scanner s) -> {
      return new Load(this, s.next(), s.next()); });
    this.knownCommands.put("red-greyscale",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new RedGreyscaleImageOperation()); });
    this.knownCommands.put("green-greyscale",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new GreenGreyscaleImageOperation()); });
    this.knownCommands.put("blue-greyscale",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new BlueGreyscaleImageOperation()); });
    this.knownCommands.put("value-greyscale",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new ValueGreyscaleImageOperation()); });
    this.knownCommands.put("intensity-greyscale",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new IntensityGreyscaleImageOperation()); });
    this.knownCommands.put("luma-greyscale",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new LumaGreyscaleImageOperation()); });
    this.knownCommands.put("flip-horizontally",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new FlipHorizontallyImageOperation()); });
    this.knownCommands.put("flip-vertically",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new FlipVerticallyImageOperation()); });
    this.knownCommands.put("brighten",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new BrightenImageOperation(Integer.parseInt(s.next()))); });
    this.knownCommands.put("darken",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new DarkenImageOperation(Integer.parseInt(s.next()))); });
    this.knownCommands.put("blur",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new BlurImageOperation()); }); //ADDED
    this.knownCommands.put("sharpen",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new SharpenImageOperation()); }); //ADDED
    this.knownCommands.put("greyscale",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new GreyscaleImageOperation()); }); //ADDED
    this.knownCommands.put("sepia",
        (Scanner s) -> {
          return new ImageCommand(
                    s.next(), s.next(), new SepiaImageOperation()); }); //ADDED
    this.knownCommands.put("save",
        (Scanner s) -> {
          return new Save(this, s.next(), s.next()); });
  }

  /**
   * Executes this image processing application. The user is able to execute from the list of known
   * commands and is also able to quit the application by entering 'q' or "quit". A menu containing
   * how to enter these commands is displayed at the start of this method. This method continues to
   * run until it runs out of inputs, the user decides to quit, or an exception is thrown due to the
   * program being unable to transmit a message. Once the user begins a valid command, they are
   * unable to quit until the command is fully completed; for example, if the user begins the
   * blue-greyscale command, they must provide two more inputs (they can be valid or invalid)
   * before they are able to quit through entering "q" or "quit". The user receives feedback after
   * completing commands (whether they were completed successfully or if something went wrong).
   *
   * @throws IllegalStateException if transmission fails
   */
  @Override
  public void execute() throws IllegalStateException {
    this.printMenu();
    while (input.hasNext()) {
      ImageProcessingCommand c = null;
      String in = input.next();

      // if 'q' or "quit" is entered, end the method
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        try {
          this.view.renderMessage("Thanks for using this program! Goodbye!");
        }
        catch (IOException e) {
          throw new IllegalStateException("Failed to transmit message");
        }
        return;
      }

      Function<Scanner, ImageProcessingCommand> cmd = knownCommands.getOrDefault(in, null);

      if (cmd == null) {
        try {
          this.view.renderMessage("Invalid command; try again.");
        }
        catch (IOException e) {
          throw new IllegalStateException("Failed to transmit message");
        }

      }
      else {
        try {
          c = cmd.apply(input);
        }
        catch (NumberFormatException e) {
          try {
            this.view.renderMessage("Command unsuccessful! Invalid number.");
          }
          catch (IOException ex) {
            throw new IllegalStateException("Failed to transmit message");
          }
        }
        catch (NoSuchElementException e) {
          try {
            this.view.renderMessage("Command unsuccessful! Not enough inputs.");
          }
          catch (IOException ex) {
            throw new IllegalStateException("Failed to transmit message");
          }
        }
        catch (IllegalArgumentException e) {
          try {
            this.view.renderMessage("Command unsuccessful! " + e.getMessage());
          }
          catch (IOException ex) {
            throw new IllegalStateException("Failed to transmit message");
          }
        }

        if (c != null) {
          try {
            c.execute(model);
            try {
              this.view.renderMessage("Command successful! (used " + in + " on image)"); // CHANGE
            }
            catch (IOException e) {
              throw new IllegalStateException("Failed to transmit message");
            }
          }
          catch (IllegalArgumentException e) {
            try {
              this.view.renderMessage("Command unsuccessful! " + e.getMessage());
            }
            catch (IOException ex) {
              throw new IllegalStateException("Failed to transmit message");
            }
          }
        }

      }
    }
  }

  @Override
  public MyImage getMyImage(String filePath) throws IllegalArgumentException {
    BufferedImage image;

    // if the given file path is a PPM image, use method to translate PPM file into BufferedImage
    // as ImageIO.read doesn't support PPM
    if (filePath.endsWith("ppm")) {
      image = getPPMAsBufferedImage(filePath);
    }
    else {
      try {
        image = ImageIO.read(new File(filePath));
      }
      catch (IOException e) {
        throw new IllegalArgumentException("Unable to read " + filePath + ".");
      }
    }

    // if image is null after translating it from PPM to BufferedImage or from using ImageIO
    // read method, throw exception
    if (image == null) {
      throw new IllegalArgumentException("Unsupported file format.");
    }

    ArrayList<ArrayList<TransparentPixel>> imageArray
            = new ArrayList<ArrayList<TransparentPixel>>();

    // builds the image pixel by pixel
    for (int i = 0; i < image.getHeight(); i = i + 1) {
      ArrayList<TransparentPixel> row = new ArrayList<TransparentPixel>();
      for (int j = 0; j < image.getWidth(); j = j + 1 ) {
        TransparentPixel pixel;
        // if the original image supports alpha, use the alpha
        if (image.getColorModel().hasAlpha()) {
          Color color = new Color(image.getRGB(j, i), true);
          pixel = new RGBAPixel(color.getRed(), color.getGreen(),
                  color.getBlue(), color.getAlpha());
        }
        // else, use the constructor that automatically makes it opaque
        else {
          Color color = new Color(image.getRGB(j, i));
          pixel = new RGBAPixel(color.getRed(), color.getGreen(), color.getBlue());
        }
        row.add(pixel);
      }
      imageArray.add(row);
    }

    return new SimpleImage(imageArray);
  }

  /**
   * Translates the PPM image at the given file path to a BufferedImage.
   * @param filePath the file path of the PPM Image
   * @return a BufferedImage with the contents of the PPM image
   * @throws IllegalArgumentException if the file at the file path cannot be read properly
   */
  private BufferedImage getPPMAsBufferedImage(String filePath) throws IllegalArgumentException {
    Scanner scan;

    try {
      scan = new Scanner(new FileInputStream(filePath));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Could not find file; invalid file path.");
    }

    StringBuilder builder = new StringBuilder();

    // reads the file line by line, and creates a string
    // deletes comment lines
    while (scan.hasNextLine()) {
      String s = scan.nextLine();
      try {
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }
      catch (StringIndexOutOfBoundsException e) {
        throw new IllegalArgumentException("File is empty or cannot cannot be read.");
      }

    }
    scan = new Scanner(builder.toString());

    // the first string in the file
    String token = scan.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file; file should begin with \"P3\"");
    }

    int width = scan.nextInt();
    int height = scan.nextInt();

    BufferedImage image
            = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    int maxValue = scan.nextInt();

    for (int i = 0; i < height; i = i + 1) {
      for (int j = 0; j < width; j = j + 1) {
        int r = scan.nextInt();
        int g = scan.nextInt();
        int b = scan.nextInt();

        Color currentColor = new Color(r, g, b);

        image.setRGB(j, i, currentColor.getRGB());
      }
    }

    return image;
  }

  @Override
  public void writeImage(MyImage image, String filePath) throws IllegalArgumentException {
    // if the given file path is a PPM file, use saveAsPPM to save as ImageIO write doesn't
    // support PPM
    if (filePath.endsWith(".ppm")) {
      this.saveAsPPM(image, filePath);
    }
    // else use saveWithBufferedImage (uses ImageIO write method)
    else {
      this.saveWithBufferedImage(image, filePath);
    }
  }

  /**
   * Saves the given MyImage representation of an image to the given file path as a PPM image.
   * @param image the MyImage representation of the image being saved
   * @param filePath the file path of the ppm image being saved
   * @throws IllegalArgumentException if there's an error writing the file
   */
  private void saveAsPPM(MyImage image, String filePath) throws IllegalArgumentException {
    PrintWriter writer;
    try {
      writer = new PrintWriter(filePath);
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Error creating file named " + filePath);
    }

    writer.println("P3");
    writer.println("# Created 2022");
    writer.println(image.getWidth() + " " + image.getHeight());
    writer.println("255");

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel curPixel = image.getPixelAt(i, j);
        writer.println(curPixel.getRed() + " " + curPixel.getGreen() + " " + curPixel.getBlue());
      }
    }

    writer.close();
  }

  /**
   * Saves the given MyImage representation of an image to the given file path, translating the
   * MyImage object to a BufferedImage and using ImageIO write method.
   * @param imageAsMyImage the MyImage representation of the image being saved
   * @param filePath the file path of the image being saved
   * @throws IllegalArgumentException if the file cannot be written
   */
  private void saveWithBufferedImage(MyImage imageAsMyImage, String filePath)
          throws IllegalArgumentException {
    int imageType;
    if (!filePath.endsWith("png")) {
      imageType = BufferedImage.TYPE_INT_RGB;
    }
    else {
      imageType = BufferedImage.TYPE_INT_ARGB;
    }

    BufferedImage image
            = new BufferedImage(imageAsMyImage.getWidth(), imageAsMyImage.getHeight(), imageType);

    // builds the BufferedImage pixel by pixel
    for (int i = 0; i < imageAsMyImage.getHeight(); i = i + 1) {
      for (int j = 0; j < imageAsMyImage.getWidth(); j = j + 1) {
        TransparentPixel curPixel = imageAsMyImage.getPixelAt(i, j);
        Color pixelColor;
        // if the new file supports alpha channels, use the current pixel's alpha channel
        if (image.getColorModel().hasAlpha()) {
          pixelColor = new Color(curPixel.getRed(), curPixel.getGreen(),
                  curPixel.getBlue(), curPixel.getAlpha());
        }
        // else, don't use it
        else {
          pixelColor = new Color(curPixel.getRed(), curPixel.getGreen(), curPixel.getBlue());
        }
        image.setRGB(j, i, pixelColor.getRGB());
      }
    }

    // the file format
    String fileType = "";
    try {
      fileType = filePath.substring(filePath.length() - 3);
    }
    catch (StringIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Given file path isn't long enough.");
    }


    try {
      File file = new File(filePath);
      ImageIO.write(image, fileType, file);
    }
    catch (IOException e) {
      throw new IllegalArgumentException("Could not write file.");
    }
  }

  /**
   * Renders a menu containing information about the known commands for the user to see.
   *
   * @throws IllegalStateException if transmission fails
   */
  private void printMenu() throws IllegalStateException {
    try {
      this.view.renderMessage("Menu");
      this.view.renderMessage(
              "load file-path image-name"
                      + "(load an image into this program and call it as the name)");
      this.view.renderMessage(
              "red-greyscale image-name new-name (greyscales the image using "
                      + "the red component and calls it by the new name)");
      this.view.renderMessage(
              "green-greyscale image-name new-name (greyscales the image using "
                      + "the green component and calls it by the new name)");
      this.view.renderMessage(
              "blue-greyscale image-name new-name (greyscales the image using "
                      + "the blue component and calls it by the new name)");
      this.view.renderMessage(
              "value-greyscale image-name new-name (greyscales the image using "
                      + "the value component and calls it by the new name)");
      this.view.renderMessage(
              "intensity-greyscale image-name new-name (greyscales the image using "
                      + "the intensity component and calls it by the new name)");
      this.view.renderMessage(
              "luma-greyscale image-name new-name (greyscales the image using "
                      + "the luma component and calls it by the new name)");
      this.view.renderMessage(
              "flip-horizontally image-name new-name "
                      + "(flips the image horizontally and calls it by the new name)");
      this.view.renderMessage(
              "flip-vertically image-name new-name "
                      + "(flips the image vertically and calls it by the new name)");
      this.view.renderMessage(
              "brighten image-name new-name value "
                      + "(brightens the image by the given value and calls it by the new name)");
      this.view.renderMessage(
              "darken image-name new-name value "
                      + "(darkens the image by the given value and calls it by the new name)");
      this.view.renderMessage(
              "blur image-name new-name (blurs the image and calls it by the new name)");
      this.view.renderMessage(
              "sharpen image-name new-name (sharpens the image and calls it by the new name)");
      this.view.renderMessage(
              "greyscale image-name new-name (greyscales the image (using the luma of each pixel) "
                      + "and calls it by the new name)");
      this.view.renderMessage(
              "sepia image-name new-name (puts the image in sepia tone "
                      + "and calls it by the new name)");
      this.view.renderMessage(
              "save file-path image-name (saves the image into the file path)");
      this.view.renderMessage(
              "q or quit (quits this program)");
    }
    catch (IOException e) {
      throw new IllegalStateException("Failed to transmit message");
    }
  }

}

