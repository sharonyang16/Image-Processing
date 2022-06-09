package model.pixel.operations;

import model.pixel.Pixel;

/**
 * This class represents an operation that greyscales a pixel with the green component
 * of the pixel.
 */
public final class GreenGreyscalePixelOperation implements PixelOperation {
  @Override
  public void execute(Pixel pixel) {
    pixel.greyscaleWith(pixel.getGreen());
  }
}
