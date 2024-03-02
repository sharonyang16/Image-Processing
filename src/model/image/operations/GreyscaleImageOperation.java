package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.GreyscalePixelOperation;

/**
 * This class represents an operation that greyscales an image (using the luma of each pixel).
 */
public class GreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new GreyscalePixelOperation());
  }
}
