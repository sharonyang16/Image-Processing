package model.pixel.operations;

import model.pixel.Pixel;

public class LumaGreyscalePixelOperation implements PixelOperation {
  @Override
  public void execute(Pixel pixel) {
    int weightedSum = (int) ((0.2126 * pixel.getRed())
            + (0.7152 * pixel.getGreen())
            + (0.0722 * pixel.getBlue()));
    pixel.greyscaleWith(weightedSum);
  }
}
