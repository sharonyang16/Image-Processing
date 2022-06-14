package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.SepiaPixelOperation;

public class SepiaImageOperation extends AbstractImageOperation {
  @Override
  public void execute(MyImage image) {
    helpExecute(image, new SepiaPixelOperation());
  }
}
