package model.image.operations;

import model.image.Image;
import model.pixel.operations.LumaGreyscalePixelOperation;

public class LumaGreyscaleImageOperation extends AbstractImageOperation {

  @Override
  public void execute(Image image) {
    helpExecute(image, new LumaGreyscalePixelOperation());
  }
}
