package model.pixel.operations;

/**
 * This class represents an operation that puts a pixel into sepia tone.
 */
public class SepiaPixelOperation extends ColorTransformationPixelOperation {

  /**
   * Creates a sepia pixel operation with the correct matrix representation of the operation.
   */
  public SepiaPixelOperation() {
    super(new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}});
  }
}
