package model.pixel;

import model.pixel.operations.PixelOperation;

public class SimplePixel implements Pixel {
  private int[] components = new int[3];

  public SimplePixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255) {
      throw new IllegalArgumentException("Invalid component value.");
    }
    this.components[0] = red;
    this.components[1] = green;
    this.components[2] = blue;
  }

  @Override
  public void accept(PixelOperation o) {
    o.execute(this);
  }

  @Override
  public int getRed() {
    return components[0];
  }

  @Override
  public int getGreen() {
    return components[1];
  }

  @Override
  public int getBlue() {
    return components[2];
  }

  @Override
  public void greyscaleWith(int value) {
    for (int i = 0; i < this.components.length; i = i + 1) {
      this.components[i] = value;
    }
  }

  @Override
  public void adjustBrightness(int value) {
    for (int i = 0; i < this.components.length; i = i + 1) {
      if (this.components[i] + value < 0) {
        this.components[i] = 0;
      }
      else if (this.components[i] + value > 255) {
        this.components[i] = 255;
      }
      else {
        this.components[i] += value;
      }
    }
  }

  @Override
  public Pixel getCopy() {
    return new SimplePixel(components[0], components[1], components[2]);
  }
}
