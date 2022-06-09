package model.pixel.operations;

import model.pixel.Pixel;

/**
 * This interface represents an operation that can be performed on a pixel to cause it to mutate.
 */
public interface PixelOperation {
  /**
   * Executes this operation on the given pixel.
   *
   * @param pixel the pixel being operated on
   */
  void execute(Pixel pixel);
}
