package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.BrightenPixelOperation;


/**
 * This class represents an operation that brightens an image by brightening each pixel of the image
 * by a value.
 */
public final class BrightenImageOperation extends AbstractImageOperation{
  private final int value;

  /**
   * Creates a image brightening operation that brightens the image by the given value
   *
   * @param value the value the image is being brightened by
   */
  public BrightenImageOperation(int value) {
    this.value = value;
  }

  @Override
  public void execute(MyImage image) {
    helpExecute(image, new BrightenPixelOperation(value));
  }
}
