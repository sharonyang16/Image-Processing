package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.BlueGreyscalePixelOperation;

/**
 * This class represents an operation that greyscales an image based on the blue component of each
 * pixel of the image.
 */
public final class BlueGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new BlueGreyscalePixelOperation());
  }
}
