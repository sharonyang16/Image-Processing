package model.image.operations;

import java.util.ArrayList;

import model.image.MyImage;

/**
 * This class represents a filtering image operation
 */
public class FilterImageOperation implements ImageOperation {
  private final double [][] filter;

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

  public enum RGBColor {
    RED, BLUE, GREEN
  }

  @Override
  public void execute(MyImage image) {
    ArrayList<ArrayList<ArrayList<Integer>>> colors = new ArrayList<>();


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

    for (int i = 0; i < image.getHeight(); i = i + 1) {
      for (int j = 0; j < image.getWidth(); j = j + 1) {
        image.getPixelAt(i, j).setRed(colors.get(i).get(j).get(0));
        image.getPixelAt(i, j).setGreen(colors.get(i).get(j).get(1));
        image.getPixelAt(i, j).setBlue(colors.get(i).get(j).get(2));
      }
    }
  }

  private int applyFilter(int row, int col, MyImage image, RGBColor color) {
    double sum = 0;
    int distFromFilterCenter = filter.length / 2;
    for (int i = 0; i < filter.length; i = i + 1) {
      for (int j = 0; j < filter[i].length; j = j + 1) {
        int curRow = row - distFromFilterCenter + i;
        int curCol = col - distFromFilterCenter + j;
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

  private boolean isValid(int row, int col, MyImage image) {
    return row >= 0 && row < image.getHeight()
            && col >= 0 && col < image.getWidth();
  }
}
