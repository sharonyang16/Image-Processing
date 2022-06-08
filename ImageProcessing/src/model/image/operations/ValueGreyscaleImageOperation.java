package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.ValueGreyscalePixelOperation;

public class ValueGreyscaleImageOperation extends AbstractImageOperation {

  @Override
  public void execute(MyImage image) {
    helpExecute(image, new ValueGreyscalePixelOperation());
  }
}
