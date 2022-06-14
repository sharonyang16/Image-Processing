package controller.commands;

import model.ImageProcessingModel;

/**
 * This class represents a saving command for an image processing application. This command takes
 * an image saved within the application and saves it to a file path on the machine it is being run
 * on.
 */
public final class Save implements ImageProcessingCommand {
  private final String filePath;
  private final String name;

  /**
   * Creates a saving command with the given file path and name.
   *
   * @param filePath the file path this new image will be stored at
   * @param name the name of the image in the application
   */
  public Save(String filePath, String name) {
    this.filePath = filePath;
    this.name = name;
  }

  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    model.saveAs(filePath, name);
  }
}
