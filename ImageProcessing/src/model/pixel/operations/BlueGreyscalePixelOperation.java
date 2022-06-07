package model.pixel.operations;

import model.pixel.Pixel;

public class BlueGreyscalePixelOperation implements PixelOperation {

  @Override
  public void execute(Pixel pixel) {
    pixel.greyscaleWith(pixel.getBlue());
  }
}
