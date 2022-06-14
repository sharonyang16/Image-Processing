package model.pixel.operations;

/**
 * This class represents an operation that greyscales a pixel (using the luma of the pixel).
 */
public class GreyscalePixelOperation extends ColorTransformationPixelOperation {

  /**
   * Creates a greyscale pixel operation with the correct matrix representation of the operation.
   */
  public GreyscalePixelOperation() {
    super(new double[][]{
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}});
  }
}
