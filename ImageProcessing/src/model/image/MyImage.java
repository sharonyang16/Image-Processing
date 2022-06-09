package model.image;

import model.image.operations.ImageOperation;
import model.pixel.Pixel;

/**
 * This interface represents an image represented by pixels. This image is able to be mutated.
 */
public interface MyImage {
  /**
   * Accepts an operation that is then executed on this image.
   * @param o the operation that will be executed on this image
   */
  void accept(ImageOperation o);

  /**
   * Returns the height of this image.
   *
   * @return the height of this image
   */
  int getHeight();

  /**
   * Returns the width of this image.
   *
   * @return the width of this image
   */
  int getWidth();

  /**
   * Returns the pixel at the given row and column.
   *
   * @param row the row of the desired pixel
   * @param col the column of the desired pixel
   * @return the pixel at the given row and column
   * @throws IllegalArgumentException if the given position is an invalid spot for this image
   */
  Pixel getPixelAt(int row, int col) throws IllegalArgumentException;

  /**
   * Flips this image horizontally.
   */
  void flipHorizontally();

  /**
   * Flips this image vertically.
   */
  void flipVertically();

  /**
   * Returns a copy of this image.
   *
   * @return a copy of this image.
   */
  MyImage getCopy();
}

