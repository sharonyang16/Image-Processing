package model.image.operations;

import model.image.Image;
import model.pixel.operations.BlueGreyscalePixelOperation;

public class BlueGreyscaleImageOperation extends AbstractImageOperation {
  @Override
  public void execute(Image image) {
    helpExecute(image, new BlueGreyscalePixelOperation());
  }
}
