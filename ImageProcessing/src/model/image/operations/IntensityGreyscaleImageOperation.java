package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.IntensityGreyscalePixelOperation;

public class IntensityGreyscaleImageOperation extends AbstractImageOperation {

  @Override
  public void execute(MyImage image) {
    helpExecute(image, new IntensityGreyscalePixelOperation());
  }
}
