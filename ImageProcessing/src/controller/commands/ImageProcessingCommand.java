package controller.commands;

import model.ImageProcessingModel;

/**
 * This interface represents a command in an image processing application.
 */
public interface ImageProcessingCommand {
  /**
   * Executes this command on the given model.
   *
   * @param model the model this command is being performed on
   * @throws IllegalArgumentException if the command is unable to be successfully completed
   */
  void execute(ImageProcessingModel model) throws IllegalArgumentException;
}
