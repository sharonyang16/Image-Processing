package controller.commands;

import model.ImageProcessingModel;
import model.image.operations.ImageOperation;

/**
 * This class represents a command that modifies an image within an image processing application in
 * and saves it within the application as a new image.
 */
public final class ImageCommand implements ImageProcessingCommand {
  private final String originalName;
  private final String newName;
  private final ImageOperation op;

  /**
   * Creates an image modifying command that contains the name of the original image, the name
   * of the image after it's been modified, and the operation being performed on the image.
   *
   * @param originalName the name of the original image
   * @param newName the name of the new image being created
   * @param op the operation being performed on the original image
   */
  public ImageCommand(String originalName, String newName, ImageOperation op) {
    this.originalName = originalName;
    this.newName = newName;
    this.op = op;
  }

  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    model.performAndSaveAs(originalName, newName, op);
  }
}
