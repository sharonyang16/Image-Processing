package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.GreyscalePixelOperation;

public class GreyscaleImageOperation extends AbstractImageOperation {

  @Override
  public void execute(MyImage image) {
    helpExecute(image, new GreyscalePixelOperation());
  }
}
