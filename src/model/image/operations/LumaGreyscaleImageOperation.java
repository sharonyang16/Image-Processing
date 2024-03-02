package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.LumaGreyscalePixelOperation;


/**
 * This class represents an operation that greyscales an image based on the luma of each
 * pixel of the image.
 */
public final class LumaGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new LumaGreyscalePixelOperation());
  }
}
