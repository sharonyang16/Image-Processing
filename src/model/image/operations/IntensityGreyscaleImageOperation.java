package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.IntensityGreyscalePixelOperation;


/**
 * This class represents an operation that greyscales an image based on the intensity of each
 * pixel of the image.
 */
public final class IntensityGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new IntensityGreyscalePixelOperation());
  }
}
