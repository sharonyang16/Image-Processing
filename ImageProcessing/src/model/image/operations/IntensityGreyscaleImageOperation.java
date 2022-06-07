package model.image.operations;

import model.image.Image;
import model.pixel.operations.IntensityGreyscalePixelOperation;

public class IntensityGreyscaleImageOperation extends AbstractImageOperation {

  @Override
  public void execute(Image image) {
    helpExecute(image, new IntensityGreyscalePixelOperation());
  }
}
