package controller;

import model.image.MyImage;

/**
 * This interface represents the controller of an image processing application. This controller also
 * handles IO (reading and writing) to load and save images.
 */
public interface ImageProcessingController {
  /**
   * Executes this image processing application.
   */
  void execute();

  /**
   * Reads the filePath to create and return a MyImage representation of the image
   * under that filePath.
   *
   * @param filePath the file path of the image
   * @return a MyImage representation of the image
   * @throws IllegalArgumentException if the file at the given path cannot be read
   */
  MyImage getMyImage(String filePath) throws IllegalArgumentException;

  /**
   * Writes a image file with the given MyImage representation of the image at the given file path.
   *
   * @param image the MyImage representation of the image that's going to be saved
   * @param filePath the location of where the image is going to be saved
   * @throws IllegalArgumentException if the file at the given path cannot be written
   */
  void writeImage(MyImage image, String filePath) throws IllegalArgumentException;
}
