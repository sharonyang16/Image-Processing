package controller.commands;

import model.ImageProcessingModel;

/**
 * This class represents a loading command for an image processing application.
 */
public class Load implements ImageProcessingCommand {
  private String filePath;
  private String name;

  /**
   * Creates a loading command with the given file path and name.
   *
   * @param filePath the file path of the image
   * @param name the name the image is being stored as in the application
   */
  public Load(String filePath, String name) {
    this.filePath = filePath;
    this.name = name;
  }

  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    model.loadFile(filePath, name);
  }
}
