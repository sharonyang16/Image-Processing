package model.pixel;

public interface TransparentPixel extends Pixel {
  int getAlpha();

  void setAlpha(int alpha);

  TransparentPixel getCopy();
}
