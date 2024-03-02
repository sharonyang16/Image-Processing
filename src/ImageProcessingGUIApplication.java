import controller.ImageProcessingFeaturesController;
import controller.ImageProcessingFeaturesControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingGUIView;
import view.ImageProcessingSwingGUIView;

public class ImageProcessingGUIApplication {
  public static void main(String[] args) {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingGUIView view = new ImageProcessingSwingGUIView();
    ImageProcessingFeaturesController controller
            = new ImageProcessingFeaturesControllerImpl(view, model);
  }
}
