package controller.commands;

import model.ImageProcessingModel;

public class Load implements ImageProcessingCommand {
  private String filePath;
  private String name;

  public Load(String filePath, String name) {
    this.filePath = filePath;
    this.name = name;
  }

  @Override
  public void execute(ImageProcessingModel model) {
    model.loadFile(filePath, name);
  }
}
