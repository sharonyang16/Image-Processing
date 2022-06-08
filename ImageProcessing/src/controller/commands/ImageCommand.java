package controller.commands;

import model.ImageProcessingModel;
import model.image.operations.ImageOperation;

/**
 * This class represents a command that modifies an image in someway and saves it as a
 * new image.
 */
public class ImageCommand implements ImageProcessingCommand {
  private String originalName;
  private String newName;
  private ImageOperation op;

  public ImageCommand(String originalName, String newName, ImageOperation op) {
    this.originalName = originalName;
    this.newName = newName;
    this.op = op;
  }

  public void execute(ImageProcessingModel model) {
    model.performAndSaveAs(originalName, newName, op);
  }


}
