package model.pixel.operations;

import model.pixel.Pixel;

/**
 * This class represents an operation that greyscales a pixel with the intensity, the average of the
 * RGB components, of the pixel; the average is rounded down.
 */
public final class IntensityGreyscalePixelOperation implements PixelOperation {
  @Override
  public void execute(Pixel pixel) {
    int average = (pixel.getRed() + pixel.getGreen() + pixel.getGreen()) / 3;
    pixel.greyscaleWith(average);
  }
}
