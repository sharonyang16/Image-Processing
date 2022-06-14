package model.pixel;

import model.pixel.operations.PixelOperation;

/**
 * This class represents a pixel that has 3 components, representing the RGB values.
 * Each channel is represented in 8-bit, meaning the minimum value of each component is 0
 * and the maximum value of each component is 255.
 */
public class SimplePixel implements Pixel {
  private int[] components = new int[3];

  /**
   * Creates a simple 3 component pixel with the given RGB values.
   *
   * @param red the value of the red component
   * @param green the value of the green component
   * @param blue the value of the blue component
   * @throws IllegalArgumentException a value given cannot be represented in 8-bit
   */
  public SimplePixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255) {
      throw new IllegalArgumentException("Invalid RGB component value.");
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
  public int getAlpha() {
    return 255;
  }

  @Override
  public void setRed(int red) {
    this.components[0] = clampHelper(red);
  }

  @Override
  public void setGreen(int green) {
    this.components[1] = clampHelper(green);
  }

  @Override
  public void setBlue(int blue) {
    this.components[2] = clampHelper(blue);
  }

  /**
   * Greyscales this pixel by setting the RGB components all to the same given value if the given
   * value is between 0 and 255 (inclusive). Otherwise, throws an exception.
   *
   * @param value the value each RGB component is being set to
   * @throws IllegalArgumentException if the given value is greater than 255 or less than 0
   */
  @Override
  public void greyscaleWith(int value) throws IllegalArgumentException {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException("8-bit RGB channels cannot be set to " + value + ".");
    }
    for (int i = 0; i < this.components.length; i = i + 1) {
      this.components[i] = value;
    }
  }

  /**
   * Adjusts the brightness of this pixel by summing each component with the given value.
   * If the sum of a component and the value is greater than the maximum value (255)
   * or less than the minimum value (0) it can be, clamp it to the maximum and minimum respectively.
   *
   * @param value the value each RGB component is summed with
   */
  @Override
  public void adjustBrightness(int value) {
    for (int i = 0; i < this.components.length; i = i + 1) {
      this.components[i] = clampHelper(this.components[i] + value);
    }
  }

  private int clampHelper(int value) {
    if (value < 0) {
      return 0;
    }
    else if (value > 255) {
      return 255;
    }
    else {
      return value;
    }
  }

  @Override
  public Pixel getCopy() {
    return new SimplePixel(components[0], components[1], components[2]);
  }
}
