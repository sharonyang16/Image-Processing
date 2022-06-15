package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.image.MyImage;
import model.image.SimpleImage;
import model.image.operations.ImageOperation;
import model.pixel.Pixel;
import model.pixel.RGBAPixel;
import model.pixel.TransparentPixel;

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
    else if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".bmp")) {
      try {
        BufferedImage image = ImageIO.read(new File(fileName));
        return this.processImageWithBufferedImage(image);
      }
      catch (IOException e) {
        throw new IllegalArgumentException("Error reading file");
      }

    }
    else {
      throw new IllegalArgumentException("Unsupported file type!");
    }
  }

  private MyImage processImageWithBufferedImage(BufferedImage image) {
    ArrayList<ArrayList<TransparentPixel>> imageArray = new ArrayList<ArrayList<TransparentPixel>>();
    for (int i = 0; i < image.getHeight(); i = i + 1) {
      ArrayList<TransparentPixel> row = new ArrayList<TransparentPixel>();
      for (int j = 0; j < image.getWidth(); j = j + 1 ) {
        TransparentPixel pixel;
        if (image.getColorModel().hasAlpha()) {
          Color color = new Color(image.getRGB(j, i), true);
          pixel = new RGBAPixel(color.getRed(), color.getGreen(),
                  color.getBlue(), color.getAlpha());
        }
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

    ArrayList<ArrayList<TransparentPixel>> imageList = new ArrayList<ArrayList<TransparentPixel>>();

    for (int i = 0; i < height; i = i + 1) {
      ArrayList<TransparentPixel> row = new ArrayList<TransparentPixel>();
      for (int j = 0; j < width; j = j + 1) {
        int r = scan.nextInt();
        int g = scan.nextInt();
        int b = scan.nextInt();
        TransparentPixel pixel = new RGBAPixel(r, g, b);
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
    else if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".bmp")) {
      this.saveAsWithBufferedImage(fileName, name);
    }
    else {
      throw new IllegalArgumentException("Unsupported file type!");
    }

  }

  private void saveAsWithBufferedImage(String fileName, String name)
          throws IllegalArgumentException {
    MyImage imageAsMyImage = this.images.getOrDefault(name, null);

    if (imageAsMyImage == null) {
      throw new IllegalArgumentException("Image not found.");
    }

    int imageType;
    if (fileName.endsWith("jpg")) {
      imageType = BufferedImage.TYPE_INT_RGB;
    }
    else {
      imageType = BufferedImage.TYPE_INT_ARGB;
    }

    BufferedImage image
            = new BufferedImage(imageAsMyImage.getWidth(), imageAsMyImage.getHeight(), imageType);

    for (int i = 0; i < imageAsMyImage.getHeight(); i = i + 1) {
      for (int j = 0; j < imageAsMyImage.getWidth(); j = j + 1) {
        TransparentPixel curPixel = imageAsMyImage.getPixelAt(i, j);
        Color pixelColor;
        if (image.getColorModel().hasAlpha()) {
          pixelColor = new Color(curPixel.getRed(), curPixel.getGreen(),
                  curPixel.getBlue(), curPixel.getAlpha());
        }
        else {
          pixelColor = new Color(curPixel.getRed(), curPixel.getGreen(), curPixel.getBlue());
        }
        image.setRGB(j, i, pixelColor.getRGB());

      }
    }

    String fileType = fileName.substring(fileName.length() - 3);

    try {
      File file = new File(fileName);
      ImageIO.write(image, fileType, file);
    }
    catch (IOException e) {
      throw new IllegalArgumentException();
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
