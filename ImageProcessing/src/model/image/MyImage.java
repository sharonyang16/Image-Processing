package model.image;

import model.image.operations.ImageOperation;
import model.pixel.Pixel;

public interface MyImage {
  void accept(ImageOperation o);

  int getHeight();

  int getWidth();

  Pixel getPixelAt(int row, int col);

  void flipHorizontally();

  void flipVertically();

  MyImage getCopy();
}

