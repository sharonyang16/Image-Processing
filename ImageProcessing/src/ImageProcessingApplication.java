import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

/**
 * This class represents an image processing application. Users are able to interact with this
 * application through the messages rendered in the console and providing inputs to the console.
 * Currently, users are able to load, red-greyscale, green-greyscale, blue-greyscale,
 * value-greyscale, intensity-greyscale, luma-greyscale, flip-horizontally, flip-vertically,
 * brighten, darken, and save in this application. This application currently is only able to
 * support PPM files.
 */
public class ImageProcessingApplication {
  /**
   * Creates and executes a text based program.
   * @param args the command line arguments provided
   */
  public static void main(String[] args) {
    ImageProcessingModel model =  new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl(model, System.out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view,
            new InputStreamReader(System.in));

    controller.execute();
  }
}
