package model.image.operations;

import model.image.MyImage;

/**
 * This interface represents an operation that changes an image.
 */
public interface ImageOperation {
  /**
   * Executes this operation on the given image.
   *
   * @param image the image being operated on
   */
  void execute(MyImage image);
}
