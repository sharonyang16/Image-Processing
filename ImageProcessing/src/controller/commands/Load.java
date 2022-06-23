package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * This class represents a loading command for an image processing application. This command
 * loads a file from a file path and saves it within the application under a name.
 */
public final class Load implements ImageProcessingCommand {
  private final ImageProcessingController controller;
  private final String filePath;
  private final String name;

  /**
   * Creates a loading command with the given file path and name.
   *
   * @param filePath the file path of the image
   * @param name the name the image is being stored as in the application
   */
  public Load(ImageProcessingController controller, String filePath, String name) {
    this.controller = controller;
    this.filePath = filePath;
    this.name = name;
  }

  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    model.loadImage(this.controller.getMyImage(filePath), name);
  }
}
