package model.pixel;

/**
 * This class represents a pixel with 4 channels; this pixel supports the RGB channels and an alpha
 * channel where each channel is represented in 8-bit.
 */
public class RGBAPixel extends RGBPixel implements TransparentPixel {
  private int alpha;

  /**
   * Creates a pixel with the given RGBA values.
   *
   * @param red the red component
   * @param green the green component
   * @param blue the blue component
   * @param alpha the red component
   * @throws IllegalArgumentException if any of the RGBA components are over the max or min
   */
  public RGBAPixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    super(red, green, blue);
    if (alpha > 255 || alpha < 0) {
      throw new IllegalArgumentException("Invalid alpha value.");
    }
    this.alpha = alpha;
  }

  /**
   * Creates a pixel with the given RGB vaues and makes this pixel fully opaque.
   *
   * @param red the red component
   * @param green the green component
   * @param blue the blue component
   * @throws IllegalArgumentException if any of the RGB components are over the max or min
   */
  public RGBAPixel(int red, int green, int blue) throws IllegalArgumentException {
    this(red, green, blue, 255);
  }

  @Override
  public int getAlpha() {
    return this.alpha;
  }

  /**
   * Sets the alpha channel of this pixel to the given value. Automatically clamps the value to
   * 0 or 255 if the given value goes under or over the minimum or maximum respectively.
   *
   * @param alpha the desired value of the alpha channel
   */
  @Override
  public void setAlpha(int alpha) {
    this.alpha = clampHelper(alpha);
  }

  @Override
  public TransparentPixel getCopy() {
    return new RGBAPixel(this.getRed(), this.getGreen(), this.getBlue(), this.getAlpha());
  }
}
