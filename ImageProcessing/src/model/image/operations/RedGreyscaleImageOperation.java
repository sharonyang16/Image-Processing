package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.RedGreyscalePixelOperation;

public class RedGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new RedGreyscalePixelOperation());
  }
}
