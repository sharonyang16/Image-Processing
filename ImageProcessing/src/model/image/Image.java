package model.image;

import model.image.operations.ImageOperation;
import model.pixel.Pixel;

public interface Image {
  void accept(ImageOperation o);

  int getHeight();

  int getWidth();

  Pixel getPixelAt(int row, int col);

  void flipHorizontally();

  void flipVertically();

  Image getCopy();
}

