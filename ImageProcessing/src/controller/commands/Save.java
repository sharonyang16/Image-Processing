package controller.commands;

import model.ImageProcessingModel;

public class Save implements ImageProcessingCommand {
	private String filePath;
	private String name;

  public Save(String filePath, String name) {
    this.filePath = filePath;
    this.name = name;
  }
  @Override
  public void execute(ImageProcessingModel model) {

  }
}
