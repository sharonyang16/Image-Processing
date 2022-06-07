package model.pixel.operations;

import model.pixel.Pixel;
import model.pixel.operations.PixelOperation;

public class RedGreyscalePixelOperation implements PixelOperation {
  @Override
  public void execute(Pixel pixel) {
    pixel.greyscaleWith(pixel.getRed());
  }
}
