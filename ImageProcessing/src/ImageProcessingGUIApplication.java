import controller.ImageProcessingFeaturesController;
import controller.ImageProcessingFeaturesControllerImpl;
import model.CurrentImageProcessingModel;
import model.CurrentImageProcessingModelImpl;
import view.ImageProcessingGUIView;
import view.ImageProcessingSwingGUIView;

public class ImageProcessingGUIApplication {
  public static void main(String[] args) {
    CurrentImageProcessingModel model = new CurrentImageProcessingModelImpl();
    ImageProcessingGUIView view = new ImageProcessingSwingGUIView();
    ImageProcessingFeaturesController controller
            = new ImageProcessingFeaturesControllerImpl(view, model);
  }
}
