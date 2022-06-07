package model.image.operations;

import model.image.Image;
import model.pixel.operations.GreenGreyscalePixelOperation;

public class GreenGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(Image image) {
    helpExecute(image, new GreenGreyscalePixelOperation());
  }
}
