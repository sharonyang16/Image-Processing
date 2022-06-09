package model.pixel.operations;

import model.pixel.Pixel;

/**
 * This class represents an operation that darkens a pixel by a value.
 */
public class DarkenPixelOperation implements PixelOperation {
  private final int value;

  /**
   * Creates a Darkening pixel operation that darkens the pixel by
   * the given value.
   *
   * @param value the amount the pixel is being darkened by
   * @throws IllegalArgumentException if the given value is negative
   */
  public DarkenPixelOperation(int value) throws IllegalArgumentException {
    if (value < 0) {
      throw new IllegalArgumentException("Cannot darken by a negative integer!");
    }
    this.value = value;
  }

  @Override
  public void execute(Pixel pixel) {
    pixel.adjustBrightness(-this.value);
  }
}
