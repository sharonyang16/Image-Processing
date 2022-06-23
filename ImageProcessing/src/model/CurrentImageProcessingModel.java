package model;

import model.image.MyImage;

/**
 * This interface represents the model of an image processing application. This model is able to
 * hold multiple images at once and also keeps track of the last image worked on (loaded or an image
 * operation was performed on it), described as the "current" image.
 */
public interface CurrentImageProcessingModel extends ImageProcessingModel {
  /**
   * Returns the last image worked on (known as the current image).
   * @return the last image worked on
   */
  MyImage getCurrentImage();
}
