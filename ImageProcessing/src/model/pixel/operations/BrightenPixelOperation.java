package model.pixel.operations;

import model.pixel.Pixel;

public class BrightenPixelOperation implements PixelOperation {
  private int value;

  public BrightenPixelOperation(int value) throws IllegalArgumentException {
    if (value < 0) {
      throw new IllegalArgumentException();
    }
    this.value = value;
  }

  @Override
  public void execute(Pixel pixel) {
    pixel.adjustBrightness(this.value);
  }
}
