package model.pixel.operations;

import model.pixel.Pixel;

/**
 * This class represents an operation that performs a color transformation on a pixel. The new RGB
 * values are rounded down.
 */
public class ColorTransformationPixelOperation implements PixelOperation {
  private final double[][] matrix;

  public ColorTransformationPixelOperation(double[][] matrix) {
    if (matrix.length != 3) {
      throw new IllegalArgumentException("Color transformation matrix must be a 3x3 matrix");
    }
    if (matrix[0].length != 3) {
      throw new IllegalArgumentException("Color transformation matrix must be a 3x3 matrix");
    }
    this.matrix = matrix;
  }

  @Override
  public void execute(Pixel pixel) {
    int red = pixel.getRed();
    int green = pixel.getGreen();
    int blue = pixel.getBlue();

    pixel.setRed((int) ((matrix[0][0] * red) + (matrix[0][1] * green) + (matrix[0][2] * blue)));
    pixel.setGreen((int) ((matrix[1][0] * red) + (matrix[1][1] * green) + (matrix[1][2] * blue)));
    pixel.setBlue((int) ((matrix[2][0] * red) + (matrix[2][1] * green) + (matrix[2][2] * blue)));
  }
}
