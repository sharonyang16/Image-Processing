package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.DarkenPixelOperation;

/**
 * This class represents an operation that darkens an image by darkening each pixel of the image
 * by a value.
 */
public class DarkenImageOperation extends AbstractImageOperation {
  private final int value;

  /**
   * Creates a image darkening operation that darkens the image by the given value
   *
   * @param value the value the image is being darkened by
   */
  public DarkenImageOperation(int value) {
    this.value = value;
  }

  @Override
  public void execute(MyImage image) {
    helpExecute(image, new DarkenPixelOperation(value));
  }
}
