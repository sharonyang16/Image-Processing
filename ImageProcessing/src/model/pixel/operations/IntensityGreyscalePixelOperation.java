package model.pixel.operations;

import model.pixel.Pixel;

public class IntensityGreyscalePixelOperation implements PixelOperation {
  @Override
  public void execute(Pixel pixel) {
    int average = (pixel.getRed() + pixel.getGreen() + pixel.getGreen()) / 3;
    pixel.greyscaleWith(average);
  }
}
