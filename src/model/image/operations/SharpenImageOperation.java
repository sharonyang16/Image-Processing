package model.image.operations;

/**
 * This class represents an operation that sharpens an image.
 */
public class SharpenImageOperation extends FilterImageOperation {
  /**
   * Creates a new sharpening operation that uses filtering to achieve it's goal.
   */
  public SharpenImageOperation() {
    super(new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}});
  }
}
