package model.image.operations;

/**
 * This class represents an operation that blurs an image.
 */
public class BlurImageOperation extends FilterImageOperation {
  /**
   * Creates a new blurring operation that uses filtering to achieve it's goal.
   */
  public BlurImageOperation() {
    super(new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}});
  }
}
