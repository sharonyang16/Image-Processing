package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.LumaGreyscalePixelOperation;

public class LumaGreyscaleImageOperation extends AbstractImageOperation {

  @Override
  public void execute(MyImage image) {
    helpExecute(image, new LumaGreyscalePixelOperation());
  }
}
