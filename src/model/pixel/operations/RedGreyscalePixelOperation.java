package model.pixel.operations;

import model.pixel.Pixel;

/**
 * This class represents an operation that greyscales a pixel with the red component
 * of the pixel.
 */
public final class RedGreyscalePixelOperation implements PixelOperation {
  @Override
  public void execute(Pixel pixel) {
    pixel.greyscaleWith(pixel.getRed());
  }
}
