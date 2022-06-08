package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.DarkenPixelOperation;

public class DarkenImageOperation extends AbstractImageOperation {
  private int value;

  public DarkenImageOperation(int value) {
    this.value = value;
  }

  @Override
  public void execute(MyImage image) {
    helpExecute(image, new DarkenPixelOperation(value));
  }
}
