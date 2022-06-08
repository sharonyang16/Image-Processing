package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ImageCommand;
import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
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

public class ImageProcessingControllerImpl implements ImageProcessingController {
  private ImageProcessingModel model;
  private ImageProcessingView view;
  private Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands;
  private Scanner input;

  public ImageProcessingControllerImpl(ImageProcessingModel model,
                                       ImageProcessingView view, Readable in) throws IllegalArgumentException {
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
  }

  @Override
  public void execute() {
    while(input.hasNext()) {
      ImageProcessingCommand c;
      String in = input.next();

      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        this.view.renderMessage("Goodbye!");
        return;
      }

      Function<Scanner, ImageProcessingCommand> cmd = knownCommands.getOrDefault(in, null);

      if (cmd == null) {
        this.view.renderMessage("Invalid command; try again.");
      }
      else {
        c = cmd.apply(input);
        c.execute(model);
        this.view.renderMessage("Command successful!");
      }
    }
  }

  /**
   * Prints a menu of options for the user to input.
   */
  private void printMenu() {

  }

}

