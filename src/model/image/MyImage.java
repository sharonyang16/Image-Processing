package model.image;

import model.image.operations.ImageOperation;
import model.pixel.TransparentPixel;

/**
 * This interface represents an image represented by pixels with RGBA components.
 * This image is able to be mutated.
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

  TransparentPixel getPixelAt(int row, int col);

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

