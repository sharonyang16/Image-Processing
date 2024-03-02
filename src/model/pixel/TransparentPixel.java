package model.pixel;

/**
 * This interface represents a pixel that supports transparency; this pixel has RGB channels and an
 * alpha channel.
 */
public interface TransparentPixel extends Pixel {
  /**
   * Gets the integer value of the alpha component of this pixel.
   * @return the value of the alpha component
   */
  int getAlpha();

  /**
   * Sets the alpha channel of this pixel to the given value.
   * @param alpha the desired value of the alpha channel
   */
  void setAlpha(int alpha);

  /**
   * Returns a copy of this transparent pixel.
   * @return a copy of this transparent pixel
   */
  TransparentPixel getCopy();
}
