package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.RedGreyscalePixelOperation;


/**
 * This class represents an operation that greyscales an image based on the red component of each
 * pixel of the image.
 */
public class RedGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new RedGreyscalePixelOperation());
  }
}
