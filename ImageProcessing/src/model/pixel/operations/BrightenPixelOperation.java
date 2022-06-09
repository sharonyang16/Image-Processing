package model.pixel.operations;

import model.pixel.Pixel;

/**
 * This class represents an operation that brightens a pixel by a value.
 */
public final class BrightenPixelOperation implements PixelOperation {
  private final int value;

  /**
   * Creates a brightening pixel operation that brightens the pixel by
   * the given value.
   *
   * @param value the amount the pixel is being brightened by
   * @throws IllegalArgumentException if the given value is negative
   */
  public BrightenPixelOperation(int value) throws IllegalArgumentException {
    if (value < 0) {
      throw new IllegalArgumentException("Cannot brighten by a negative integer!");
    }
    this.value = value;
  }

  @Override
  public void execute(Pixel pixel) {
    pixel.adjustBrightness(this.value);
  }
}
