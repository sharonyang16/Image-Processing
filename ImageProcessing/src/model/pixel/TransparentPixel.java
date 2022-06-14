package model.pixel;

public class TransparentPixel extends SimplePixel {
  private int alpha;

  public TransparentPixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    super(red, green, blue);
    if (alpha > 255 || alpha < 0) {
      throw new IllegalArgumentException("Invalid alpha value.");
    }
    this.alpha = alpha;
  }

  @Override
  public int getAlpha() {
    return this.alpha;
  }
}
