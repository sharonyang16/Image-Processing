package model.pixel.operations;

import model.pixel.Pixel;

public class ValueGreyscalePixelOperation implements PixelOperation {

  @Override
  public void execute(Pixel pixel) {
    int max = Math.max(pixel.getRed(), pixel.getGreen());
    max = Math.max(max, pixel.getBlue());
    pixel.greyscaleWith(max);
  }
}
