package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.PixelOperation;

/**
 * This abstract class represents an operation on an image that requires each pixel of the image
 * to be mutated the same way.
 */
public abstract class AbstractImageOperation implements ImageOperation {

  @Override
  public abstract void execute(MyImage image);

  /**
   * Used to help classes that extend this one execute the operation that the class represents.
   *
   * @param image the image being operated on
   * @param o the pixel operation each pixel of the image is being executed on
   */
  protected void helpExecute(MyImage image, PixelOperation o) {
    for (int i = 0; i < image.getHeight(); i = i + 1) {
      for (int j = 0; j < image.getWidth(); j = j + 1) {
        image.getPixelAt(i, j).accept(o);
      }
    }
  }
}
