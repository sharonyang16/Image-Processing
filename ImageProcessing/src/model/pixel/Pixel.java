package model.pixel;

import model.pixel.operations.PixelOperation;

/**
 * This interface represents a pixel of an image.
 */
public interface Pixel {
  /**
   * Accepts an operation that is then executed on this pixel. (Causes this pixel to mutate.)
   *
   * @param o the operation being performed
   */
  void accept(PixelOperation o);

  /**
   * Gets the integer value of the red component of this pixel.
   *
   * @return the value of the red component
   */
  int getRed();

  /**
   * Gets the integer value of the green component of this pixel.
   *
   * @return the value of the green component
   */
  int getGreen();

  /**
   * Gets the integer value of the blue component of this pixel.
   *
   * @return the value of the blue component
   */
  int getBlue();

  /**
   * Greyscales this pixel by setting the RGB components all to the same given value.
   *
   * @param value the value each RGB component is being set to
   */
  void greyscaleWith(int value);

  /**
   * Adjusts the brightness of this pixel by adding the given value to all RGB components.
   * A positive value brightens the pixel and a negative value darkens the pixel.
   *
   * @param value the value each RGB component is summed with
   */
  void adjustBrightness(int value);

  /**
   * Returns a copy of this pixel.
   * @return a copy of this pixel
   */
  Pixel getCopy();
}