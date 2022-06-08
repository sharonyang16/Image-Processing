package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.BlueGreyscalePixelOperation;

public class BlueGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new BlueGreyscalePixelOperation());
  }
}
