package controller.commands;

import model.ImageProcessingModel;

/**
 * This class represents a saving command for an image processing application.
 */
public class Save implements ImageProcessingCommand {
	private String filePath;
	private String name;

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
