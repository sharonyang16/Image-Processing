package model.image.operations;

import model.image.Image;
import model.pixel.operations.ValueGreyscalePixelOperation;

public class ValueGreyscaleImageOperation extends AbstractImageOperation {

  @Override
  public void execute(Image image) {
    helpExecute(image, new ValueGreyscalePixelOperation());
  }
}
