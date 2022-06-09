package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.ValueGreyscalePixelOperation;


/**
 * This class represents an operation that greyscales an image based on the value of each
 * pixel of the image.
 */
public class ValueGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new ValueGreyscalePixelOperation());
  }
}
