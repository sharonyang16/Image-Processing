package model.image.operations;

import model.image.Image;
import model.pixel.operations.RedGreyscalePixelOperation;

public class RedGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(Image image) {
    helpExecute(image, new RedGreyscalePixelOperation());
  }
}
