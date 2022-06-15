package model.pixel;

public class RGBAPixel extends SimplePixel implements TransparentPixel {
  private int alpha;

  public RGBAPixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    super(red, green, blue);
    if (alpha > 255 || alpha < 0) {
      throw new IllegalArgumentException("Invalid alpha value.");
    }
    this.alpha = alpha;
  }

  public RGBAPixel(int red, int green, int blue) throws IllegalArgumentException {
    this(red, green, blue, 255);
  }

  @Override
  public int getAlpha() {
    return this.alpha;
  }

  @Override
  public void setAlpha(int alpha) {
    this.alpha = SimplePixel.clampHelper(alpha);
  }

  @Override
  public TransparentPixel getCopy() {
    return new RGBAPixel(this.getRed(), this.getGreen(), this.getBlue(), this.alpha);
  }
}
