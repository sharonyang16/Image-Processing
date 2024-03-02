package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.GreenGreyscalePixelOperation;

/**
 * This class represents an operation that greyscales an image based on the green component of each
 * pixel of the image.
 */
public final class GreenGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new GreenGreyscalePixelOperation());
  }
}
