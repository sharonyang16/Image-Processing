package model.image.operations;

import java.util.ArrayList;

import model.image.MyImage;

/**
 * This class represents a filtering image operation.
 */
public class FilterImageOperation implements ImageOperation {
  private final double [][] filter;

  /**
   * Creates a filtering operation using the given matrix.
   *
   * @param filter the matrix/kernel used for this operation
   */
  public FilterImageOperation(double[][] filter) {
    if (filter == null) {
      throw new IllegalArgumentException("Filter can't be null!");
    }
    if (filter.length == 0) {
      throw new IllegalArgumentException("Filter can't have no height!");
    }
    if (filter.length != filter[0].length || filter.length % 2 == 0) {
      throw new IllegalArgumentException("Filter must be square and have odd dimensions!");
    }

    this.filter = filter;
  }

  /**
   * An enum to represent the 3 RGB colors.
   */
  public enum RGBColor {
    RED, BLUE, GREEN
  }

  @Override
  public void execute(MyImage image) {
    // 2D array the same size as the image and the inner most ArrayList represents the RGB
    // components of a pixel
    ArrayList<ArrayList<ArrayList<Integer>>> colors = new ArrayList<>();

    // calculates all the new RGB values for each pixel of the image and adds them to the colors
    // ArrayList
    for (int i = 0; i < image.getHeight(); i = i + 1) {
      ArrayList<ArrayList<Integer>> row = new ArrayList<>();
      for (int j = 0; j < image.getWidth(); j = j + 1) {
        ArrayList<Integer> components = new ArrayList<>();

        components.add(this.applyFilter(i, j, image, RGBColor.RED));
        components.add(this.applyFilter(i, j, image, RGBColor.GREEN));
        components.add(this.applyFilter(i, j, image, RGBColor.BLUE));

        row.add(components);
      }
      colors.add(row);
    }

    // uses the colors ArrayList to set all of the pixels to the new colors
    for (int i = 0; i < image.getHeight(); i = i + 1) {
      for (int j = 0; j < image.getWidth(); j = j + 1) {
        image.getPixelAt(i, j).setRed(colors.get(i).get(j).get(0));
        image.getPixelAt(i, j).setGreen(colors.get(i).get(j).get(1));
        image.getPixelAt(i, j).setBlue(colors.get(i).get(j).get(2));
      }
    }
  }

  /**
   * Calculates the value of this operation's filter onto the given image, placing the center of the
   * kernel onto the pixel at the given position and using the pixel's given RGB component.
   *
   * @param row the row of the center pixel
   * @param col the column of the center pixel
   * @param image the image
   * @param color which RGB component is being performed on
   * @return the new value of the component of the center pixel
   */
  private int applyFilter(int row, int col, MyImage image, RGBColor color) {
    double sum = 0;
    int distFromFilterCenter = filter.length / 2;
    for (int i = 0; i < filter.length; i = i + 1) {
      for (int j = 0; j < filter[i].length; j = j + 1) {
        // the current row of the pixel in the image
        int curRow = row - distFromFilterCenter + i;
        // the current column of the pixel in the image
        int curCol = col - distFromFilterCenter + j;

        // if the current row and current column represent a non existent pixel, don't calculate it
        if (this.isValid(curRow, curCol, image)) {
          int valueAtPixel;
          if (color == RGBColor.RED) {
            valueAtPixel = image.getPixelAt(curRow, curCol).getRed();
          }
          else if (color == RGBColor.GREEN) {
            valueAtPixel = image.getPixelAt(curRow, curCol).getGreen();
          }
          else {
            valueAtPixel = image.getPixelAt(curRow, curCol).getBlue();
          }
          sum += filter[i][j] * valueAtPixel;
        }
      }
    }

    return (int) sum;
  }

  /**
   * Determines if the given position is valid place for a pixel for the given image.
   *
   * @param row the row of the position
   * @param col the column of the position
   * @param image the image
   * @return if the given position is valid place for a pixel for the given image
   */
  private boolean isValid(int row, int col, MyImage image) {
    return row >= 0 && row < image.getHeight()
            && col >= 0 && col < image.getWidth();
  }
}
