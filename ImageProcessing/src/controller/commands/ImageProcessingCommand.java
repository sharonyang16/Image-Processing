package controller.commands;

import model.ImageProcessingModel;

public interface ImageProcessingCommand {
  void execute(ImageProcessingModel model) throws IllegalArgumentException;
}
