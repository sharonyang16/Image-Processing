package model.image.operations;

import model.image.Image;
import model.pixel.operations.DarkenPixelOperation;

public class DarkenGreyscaleImageOperation extends AbstractImageOperation {
  private int value;

  public DarkenGreyscaleImageOperation(int value) {
    this.value = value;
  }

  @Override
  public void execute(Image image) {
    helpExecute(image, new DarkenPixelOperation(value));
  }
}
