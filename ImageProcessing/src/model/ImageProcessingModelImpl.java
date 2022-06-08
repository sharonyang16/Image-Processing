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

public class ImageProcessingModelImpl implements ImageProcessingModel {
  private Map<String, MyImage> images;

  public ImageProcessingModelImpl() {
    this.images = new HashMap<String, MyImage>();
  }

  @Override
  public void loadFile(String fileName, String name) throws IllegalArgumentException {
    this.images.put(name, this.processImage(fileName));
  }

  private MyImage processImage(String fileName) throws IllegalArgumentException {
    if (fileName.endsWith(".ppm")) {
      return this.processPPM(fileName);
    }
    else {
      throw new IllegalArgumentException("Unsupported file type!");
    }
  }

  private MyImage processPPM(String fileName) throws IllegalArgumentException {
    Scanner scan;

    try {
      scan = new Scanner(new FileInputStream(fileName));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }

    StringBuilder builder = new StringBuilder();

    //read the file line by line, and populate a string. This will throw away any comment lines
    while (scan.hasNextLine()) {
      String s = scan.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s + System.lineSeparator());
      }
    }
    scan = new Scanner(builder.toString());

    String token;

    token = scan.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException();
    }
    int width = scan.nextInt();
    int height = scan.nextInt();

    ArrayList<ArrayList<Pixel>> imageList = new ArrayList<ArrayList<Pixel>>();

    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> row = new ArrayList<Pixel>();
      for (int j = 0; j < width; j++) {

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
      throw new IllegalArgumentException();
    }
    else {
      MyImage newImage = image.getCopy();
      newImage.accept(op);

      this.images.put(newName, newImage);
    }
  }

  @Override
  public void saveAs(String fileName, String name) throws IllegalArgumentException {
    if (fileName.endsWith(".ppm")) {
      this.saveAsPPM(fileName, name);
    }
    else {
      throw new IllegalArgumentException("Unsupported file type!");
    }

  }

  private void saveAsPPM(String fileName, String name) throws IllegalArgumentException {
    PrintWriter writer;
    try {
      writer = new PrintWriter(fileName);
    }
    catch(FileNotFoundException e) {
      throw new IllegalArgumentException("Error creating file named " + fileName);
    }

    MyImage image = this.images.getOrDefault(name, null);

    if (image == null) {
      throw new IllegalArgumentException("Image not found.");
    }
    writer.println("P3");
    writer.println("# ");
    writer.println(image.getWidth() + " " + image.getHeight());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel curPixel = image.getPixelAt(i, j);
        writer.println(curPixel.getRed() + " " + curPixel.getGreen() + " " + curPixel.getBlue());
      }
    }

    writer.close();
  }
}
