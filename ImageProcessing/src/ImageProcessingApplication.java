import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

public class ImageProcessingApplication {
  public static void main(String[] args) {
    ImageProcessingModel model =  new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl(model, System.out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view,
            new InputStreamReader(System.in));

    controller.execute();
  }
}
