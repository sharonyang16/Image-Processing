package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.GreenGreyscalePixelOperation;

public class GreenGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new GreenGreyscalePixelOperation());
  }
}
