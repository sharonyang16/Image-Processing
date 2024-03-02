package model.image;

import java.util.ArrayList;

import model.image.operations.ImageOperation;
import model.pixel.TransparentPixel;

/**
 * This class represents an image that's a collection of pixels with RGBA components with operations
 * allowing it to be mutated.
 */
public class SimpleImage implements MyImage {
  private ArrayList<ArrayList<TransparentPixel>> image;

  /**
   * Creates a simple image with the given 2D ArrayList representing an image. Throws an exception
   * if the given 2D ArrayList is null, is empty, or the ArrayLists inside the outer ArrayList is
   * empty.
   *
   * @param image the 2D array representing the desired image
   * @throws IllegalArgumentException if the given 2D ArrayList is invalid
   */
  public SimpleImage(ArrayList<ArrayList<TransparentPixel>> image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null!");
    }
    if (image.size() == 0) {
      throw new IllegalArgumentException("Image cannot have no height!");
    }
    if (image.get(0).size() == 0) {
      throw new IllegalArgumentException("Image cannot have no width!");
    }
    this.image = image;
  }

  @Override
  public void accept(ImageOperation o) {
    o.execute(this);
  }

  @Override
  public int getHeight() {
    return this.image.size();
  }

  @Override
  public int getWidth() {
    return this.image.get(0).size();
  }

  @Override
  public TransparentPixel getPixelAt(int row, int col) {
    return this.image.get(row).get(col);
  }

  @Override
  public void flipHorizontally() {
    for (int i = 0; i < this.getHeight(); i = i + 1) {
      for (int j = 0; j < this.getWidth() / 2; j = j + 1) {
        // swaps the pixels from the left and right
        TransparentPixel front = this.getPixelAt(i, j);
        TransparentPixel back = this.getPixelAt(i, this.getWidth() - 1 - j);
        this.image.get(i).set(j, back);
        this.image.get(i).set(this.getWidth() - 1 - j, front);
      }
    }
  }

  @Override
  public void flipVertically() {
    for (int i = 0; i < this.getHeight() / 2; i = i + 1) {
      for (int j = 0; j < this.getWidth(); j = j + 1) {
        // swaps the pixels fom the bottom and top
        TransparentPixel front = this.getPixelAt(i, j);
        TransparentPixel back = this.getPixelAt(this.getHeight() - 1 - i, j);
        this.image.get(i).set(j, back);
        this.image.get(this.getHeight() - 1 - i).set(j, front);
      }
    }
  }

  @Override
  public MyImage getCopy() {
    ArrayList<ArrayList<TransparentPixel>> copied = new ArrayList<ArrayList<TransparentPixel>>();
    // creates a 2D array of a copied version of each pixel in this image
    for (int i = 0; i < this.getHeight(); i++) {
      ArrayList<TransparentPixel> row = new ArrayList<TransparentPixel>();
      for (int j = 0; j < this.getWidth(); j = j + 1) {
        row.add(this.getPixelAt(i, j).getCopy());
      }
      copied.add(row);
    }
    return new SimpleImage(copied);
  }
}
