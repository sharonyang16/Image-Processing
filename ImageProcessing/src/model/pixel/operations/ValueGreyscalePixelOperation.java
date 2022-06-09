package model.pixel.operations;

import model.pixel.Pixel;

/**
 * This class represents an operation that greyscales a pixel with the maximum
 * value of the RGB components.
 */
public class ValueGreyscalePixelOperation implements PixelOperation {

  @Override
  public void execute(Pixel pixel) {
    // gets the max of the red and green components
    int max = Math.max(pixel.getRed(), pixel.getGreen());
    // gets the max of the previous max and the blue component
    max = Math.max(max, pixel.getBlue());

    // greyscales with the max of all three components
    pixel.greyscaleWith(max);
  }
}
