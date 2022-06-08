package model.pixel;

import model.pixel.operations.PixelOperation;

public interface Pixel {
  void accept(PixelOperation o);

  int getRed();

  int getGreen();

  int getBlue();

  void greyscaleWith(int value);

  void adjustBrightness(int value);

  Pixel getCopy();
}