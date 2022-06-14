package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.image.MyImage;
import model.image.SimpleImage;
import model.image.operations.ImageOperation;
import model.pixel.Pixel;
import model.pixel.SimplePixel;

/**
 * This class represents an implementation of a model for an image processing application. It
 * specifically implements the ImageProcessingModel interface. Currently, this model only supports
 * loading, storing, and saving PPM files.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  private Map<String, MyImage> images;

  /**
   * Creates an empty model with no images.
   */
  public ImageProcessingModelImpl() {
    this.images = new HashMap<String, MyImage>();
  }

  @Override
  public void loadFile(String fileName, String name) throws IllegalArgumentException {
    this.images.put(name, this.processImage(fileName));
  }

  /**
   * Creates a MyImage object representation of the image file with the given file name. Currently,
   * only able to process PPM files; will throw an exception if the file given isn't a PPM file.
   *
   * @param fileName the name of the image file/the path of the image file being processed
   * @return the image file represented as a MyImage object
   * @throws IllegalArgumentException if the file isn't a PPM file
   */
  private MyImage processImage(String fileName) throws IllegalArgumentException {
    if (fileName.endsWith(".ppm")) {
      return this.processPPM(fileName);
    }
    else {
      throw new IllegalArgumentException("Unsupported file type!");
    }
  }

  /**
   * Processes the given PPM file as a SimpleImage created of SimplePixel objects and returns it.
   *
   * @param filePath the name of the image file/the path of the image file being processed
   * @return the SimpleImage created representing the image from the given file path
   * @throws IllegalArgumentException if the file cannot be found or is written wrong
   */
  private MyImage processPPM(String filePath) throws IllegalArgumentException {
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

    int maxValue = scan.nextInt();

    ArrayList<ArrayList<Pixel>> imageList = new ArrayList<ArrayList<Pixel>>();

    for (int i = 0; i < height; i = i + 1) {
      ArrayList<Pixel> row = new ArrayList<Pixel>();
      for (int j = 0; j < width; j = j + 1) {
        int r = scan.nextInt();
        int g = scan.nextInt();
        int b = scan.nextInt();
        Pixel pixel = new SimplePixel(r, g, b);
        row.add(pixel);
      }
      imageList.add(row);
    }
    MyImage image = new SimpleImage(imageList);

    return image;
  }

  @Override
  public void performAndSaveAs(String originalName, String newName, ImageOperation op)
          throws IllegalArgumentException {
    MyImage image = this.images.getOrDefault(originalName, null);

    if (image == null) {
      throw new IllegalArgumentException("Image not found.");
    }
    else {
      MyImage newImage = image.getCopy();
      newImage.accept(op);

      this.images.put(newName, newImage);
    }
  }

  /**
   * Writes an image file of the given file name with the image saved in this model of the given
   * name. Currently is only able save images as a PPM file; will throw an exception of the file
   * name does not match that of a PPM file.
   *
   * @param fileName the name of the file being saved
   * @param name the image in this model that's being saved as a file
   * @throws IllegalArgumentException if the file being written isn't a PPM file
   */
  @Override
  public void saveAs(String fileName, String name) throws IllegalArgumentException {
    if (fileName.endsWith(".ppm")) {
      this.saveAsPPM(fileName, name);
    }
    else {
      throw new IllegalArgumentException("Unsupported file type!");
    }

  }

  /**
   * Saves the image under the given name in this model in a PPM file under the given file name.
   *
   * @param fileName the name of the file being written
   * @param name the name of the image in this model
   * @throws IllegalArgumentException if the given image name doesn't exist in this model or the
   *                                  file is unable to be written.
   */
  private void saveAsPPM(String fileName, String name) throws IllegalArgumentException {
    PrintWriter writer;
    try {
      writer = new PrintWriter(fileName);
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Error creating file named " + fileName);
    }

    MyImage image = this.images.getOrDefault(name, null);

    if (image == null) {
      throw new IllegalArgumentException("Image not found.");
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

  @Override
  public MyImage getImageNamed(String name) throws IllegalArgumentException {
    MyImage image = this.images.getOrDefault(name, null);

    if (image == null) {
      throw new IllegalArgumentException("This image does not exist!");
    }
    else {
      return image;
    }
  }
}
