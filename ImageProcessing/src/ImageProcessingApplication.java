import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

/**
 * This class represents an image processing application. Users are able to interact with this
 * application through the messages rendered in the console and providing inputs to the console.
 * Currently, users are able to load, red-greyscale, green-greyscale, blue-greyscale,
 * value-greyscale, intensity-greyscale, luma-greyscale, flip-horizontally, flip-vertically,
 * brighten, darken, and save in this application. This application currently is able to
 * support PPM, JPG, PNG, and BMP files.
 */
public class ImageProcessingApplication {
  /**
   * Creates and executes a text based program. The application is able to accept script files using
   * "-file" along with the name of the script file as command line arguments. If the given script
   * file cannot be found or read properly, the program will resort to taking inputs in the
   * terminal/console.
   *
   * @param args the command line arguments provided
   */
  public static void main(String[] args) {
    ImageProcessingModel model =  new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingTextView(System.out);
    ImageProcessingController controller;

    Readable in = new InputStreamReader(System.in);

    if (args.length >= 2) {
      if (args[0].equals("-file")) {
        try {
          in = new BufferedReader( new InputStreamReader(new FileInputStream(new File(args[1]))));
        }
        catch (FileNotFoundException e){
          System.err.println("Cannot load the desired file.");
          in = new InputStreamReader(System.in);
        }
      }
      else {
        System.err.println("Invalid command-line arguments.");
      }
    }

    controller = new ImageProcessingControllerImpl(model, view, in);
    controller.execute();
  }
}
