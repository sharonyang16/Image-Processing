package model;

import model.image.MyImage;
import model.image.operations.ImageOperation;

/**
 * This interface represents the model of an image processing application. This model is able to
 * hold multiple images at once and has the capabilities to load and save.
 */
public interface ImageProcessingModel {

  /**
   * Loads the given MyImage object into this model under the given name.
   *
   * @param image the image
   * @param name the name of the image stored in this model
   * @throws IllegalArgumentException if the MyImage object is null
   */
  void loadImage(MyImage image, String name) throws IllegalArgumentException;

  /**
   * Performs the given operation on a copy of an image already saved in this model and saves it
   * to this model under the new name.
   *
   * @param originalName the name of the image already in the model
   * @param newName the name of the modified version of the original image
   * @param op the operation being performed on a copy of the original image
   * @throws IllegalArgumentException if there's no image saved in this model under the given name
   *                                  or the operation cannot be performed
   */
  void performAndSaveAs(String originalName, String newName, ImageOperation op)
          throws IllegalArgumentException;

  /**
   * Returns the image that's stored under the given name in this model.
   *
   * @param name the name of the image in this model
   * @return the image under the given name
   * @throws IllegalArgumentException if the image doesn't exist in this model
   */
  MyImage getImageNamed(String name) throws IllegalArgumentException;
}

