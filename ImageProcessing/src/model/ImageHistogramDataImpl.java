package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageHistogramDataImpl implements ImageHistogramData {
  private BufferedImage image;
  private int[] redComponentValues = new int[256];
  private int[] greenComponentValues = new int[256];
  private int[] blueComponentValues = new int[256];
  private int[] intensityValues = new int[256];

  public ImageHistogramDataImpl(BufferedImage image) {
    this.image = image;
    this.initializeValues();
  }

  private void initializeValues() {
    for (int i = 0; i < this.image.getHeight(); i = i + 1) {
      for (int j = 0; j < this.image.getWidth(); j = j + 1) {
        Color color = new Color(this.image.getRGB(j, i));
        int curRed = color.getRed();
        int curGreen = color.getGreen();
        int curBlue = color.getBlue();
        int curIntensity = (curRed + curGreen + curBlue) / 3;

        redComponentValues[curRed] += 1;
        greenComponentValues[curGreen] += 1;
        blueComponentValues[curBlue] += 1;
        intensityValues[curIntensity] += 1;
      }
    }
  }

  @Override
  public int getNumRedAtValue(int value) throws IllegalArgumentException {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException();
    }
    return redComponentValues[value];
  }

  @Override
  public int getNumGreenAtValue(int value) throws IllegalArgumentException {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException();
    }
    return greenComponentValues[value];
  }

  @Override
  public int getNumBlueAtValue(int value) throws IllegalArgumentException {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException();
    }
    return blueComponentValues[value];
  }

  @Override
  public int getNumIntensityAtValue(int value) throws IllegalArgumentException {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException();
    }
    return intensityValues[value];
  }
}
