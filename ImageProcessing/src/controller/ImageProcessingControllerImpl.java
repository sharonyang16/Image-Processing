package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ImageCommand;
import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
import controller.commands.Save;
import model.ImageProcessingModel;
import model.image.operations.BlueGreyscaleImageOperation;
import model.image.operations.BrightenImageOperation;
import model.image.operations.DarkenImageOperation;
import model.image.operations.FlipHorizontallyImageOperation;
import model.image.operations.FlipVerticallyImageOperation;
import model.image.operations.GreenGreyscaleImageOperation;
import model.image.operations.IntensityGreyscaleImageOperation;
import model.image.operations.LumaGreyscaleImageOperation;
import model.image.operations.RedGreyscaleImageOperation;
import model.image.operations.ValueGreyscaleImageOperation;
import view.ImageProcessingView;

/**
 * This class represents an implementation of a controller for an image processing application. It
 * specifically implements the ImageProcessingController interface. Currently supports the following
 * commands: load, red-greyscale, green-greyscale, blue-greyscale, value-greyscale,
 * intensity-greyscale, luma-greyscale, flip-horizontally, flip-vertically, brighten, darken,
 * and save.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private ImageProcessingModel model;
  private ImageProcessingView view;
  private Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands;
  private Scanner input;

  /**
   * Creates a controller with the given model, view and scanner with the given Readable object.
   * Additionally, a container of known commands is initialized.
   *
   * @param model
   * @param view
   * @param in
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public ImageProcessingControllerImpl(
          ImageProcessingModel model, ImageProcessingView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Model, view, and Readable object cannot be null!");
    }
    this.model = model;
    this.view = view;
    this.input = new Scanner(in);
    this.knownCommands = new HashMap<String, Function<Scanner, ImageProcessingCommand>>();
    this.knownCommands.put("load", (Scanner s) -> {return new Load(s.next(), s.next()); });
    this.knownCommands.put("red-greyscale",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new RedGreyscaleImageOperation());});
    this.knownCommands.put("green-greyscale",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new GreenGreyscaleImageOperation());});
    this.knownCommands.put("blue-greyscale",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new BlueGreyscaleImageOperation());});
    this.knownCommands.put("value-greyscale",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new ValueGreyscaleImageOperation());});
    this.knownCommands.put("intensity-greyscale",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new IntensityGreyscaleImageOperation());});
    this.knownCommands.put("luma-greyscale",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new LumaGreyscaleImageOperation());});
    this.knownCommands.put("flip-horizontally",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new FlipHorizontallyImageOperation());});
    this.knownCommands.put("flip-vertically",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new FlipVerticallyImageOperation());});
    this.knownCommands.put("brighten",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new BrightenImageOperation(s.nextInt()));});
    this.knownCommands.put("darken",
            (Scanner s) -> {return new ImageCommand(
                    s.next(), s.next(), new DarkenImageOperation(s.nextInt()));});
    this.knownCommands.put("save",
            (Scanner s) -> {return new Save(s.next(), s.next());});
  }

  /**
   * Executes this image processing application. The user is able to execute from the list of known
   * commands and is also able to quit the application by entering 'q' or "quit". A menu containing
   * how to enter these commands is displayed at the start of this method. This method continues to
   * run until it runs out of inputs, the user decides to quit, or an exception is thrown due to the
   * program being unable to transmit a message.
   *
   * @throws IllegalStateException if transmission fails
   */
  @Override
  public void execute() throws IllegalStateException {
    this.printMenu();
    while(input.hasNext()) {
      ImageProcessingCommand c;
      String in = input.next();

      // if 'q' or "quit" is entered, end the method
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        try {
          this.view.renderMessage("Thanks for using this program! Goodbye!");
        }
        catch(IOException e) {
          throw new IllegalStateException("Failed to transmit message");
        }
        return;
      }

      Function<Scanner, ImageProcessingCommand> cmd = knownCommands.getOrDefault(in, null);

      if (cmd == null) {
        try {
          this.view.renderMessage("Invalid command; try again.");
        }
        catch(IOException e) {
          throw new IllegalStateException("Failed to transmit message");
        }

      }
      else {
        c = cmd.apply(input);
        try {
          c.execute(model);
        }
        catch (IllegalArgumentException e) {
          try {
            this.view.renderMessage("Command unsuccessful! " + e.getMessage());
          }
          catch (IOException ex) {
            throw new IllegalStateException("Failed to transmit message");
          }
        }

        try {
          this.view.renderMessage("Command successful!");
        }
        catch (IOException e) {
          throw new IllegalStateException("Failed to transmit message");
        }

      }
    }
  }

  /**
   * Renders a menu containing information about the known commands for the user to see.
   *
   * @throws IllegalStateException if transmission fails
   */
  private void printMenu() throws IllegalStateException {
    try {
      this.view.renderMessage("Menu");
      this.view.renderMessage(
              "load file-path image-name"
                      + "(load an image into this program and call it as the name)");
      this.view.renderMessage(
              "red-greyscale image-name new-name (greyscales the image using "
                      + "the red component and calls it by the new name)");
      this.view.renderMessage(
              "green-greyscale image-name new-name (greyscales the image using "
                      + "the green component and calls it by the new name)");
      this.view.renderMessage(
              "blue-greyscale image-name new-name (greyscales the image using "
                      + "the blue component and calls it by the new name)");
      this.view.renderMessage(
              "value-greyscale image-name new-name (greyscales the image using "
                      + "the value component and calls it by the new name)");
      this.view.renderMessage(
              "intensity-greyscale image-name new-name (greyscales the image using "
                      + "the intensity component and calls it by the new name)");
      this.view.renderMessage(
              "luma-greyscale image-name new-name (greyscales the image using "
                      + "the luma component and calls it by the new name)");
      this.view.renderMessage(
              "flip-horizontally image-name new-name " +
                      "(flips the image horizontally and calls it by the new name)");
      this.view.renderMessage(
              "flip-vertically image-name new-name " +
                      "(flips the image vertically and calls it by the new name)");
      this.view.renderMessage(
              "brighten image-name new-name value" +
                      "(brightens the image by the given value and calls it by the new name)");
      this.view.renderMessage(
              "darken image-name new-name value" +
                      "(darkens the image by the given value and calls it by the new name)");
      this.view.renderMessage(
              "save file-path image-name (saves the image into the file path)");
      this.view.renderMessage(
              "q or quit (quits this program)");
    }
    catch (IOException e) {
      throw new IllegalStateException("Failed to transmit message");
    }
  }

}

