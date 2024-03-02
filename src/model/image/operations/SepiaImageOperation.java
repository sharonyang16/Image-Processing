package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.SepiaPixelOperation;

/**
 * This class represents an operation that applies sepia tone onto an image.
 */
public class SepiaImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new SepiaPixelOperation());
  }
}
