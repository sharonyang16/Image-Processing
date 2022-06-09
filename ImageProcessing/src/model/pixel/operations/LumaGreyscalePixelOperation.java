package model.pixel.operations;

import model.pixel.Pixel;

/**
 * This class represents an operation that greyscales a pixel with the luma, the weighted sum of the
 * RGB components represented by the expression 0.2126R + 0.7152G + 0.0722B, of the pixel; the sum
 * is rounded down.
 */
public class LumaGreyscalePixelOperation implements PixelOperation {
  @Override
  public void execute(Pixel pixel) {
    int weightedSum = (int) ((0.2126 * pixel.getRed())
            + (0.7152 * pixel.getGreen())
            + (0.0722 * pixel.getBlue()));
    pixel.greyscaleWith(weightedSum);
  }
}
