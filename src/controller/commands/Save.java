package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;
import model.image.MyImage;

/**
 * This class represents a saving command for an image processing application. This command takes
 * an image saved within the application and saves it to a file path on the machine it is being run
 * on.
 */
public final class Save implements ImageProcessingCommand {
  private final ImageProcessingController controller;
  private final String filePath;
  private final String name;

  /**
   * Creates a saving command with the given file path and name.
   *
   * @param filePath the file path this new image will be stored at
   * @param name the name of the image in the application
   */
  public Save(ImageProcessingController controller, String filePath, String name) {
    this.controller = controller;
    this.filePath = filePath;
    this.name = name;
  }

  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    MyImage image = model.getImageNamed(name);
    this.controller.writeImage(image, filePath);
  }
}
