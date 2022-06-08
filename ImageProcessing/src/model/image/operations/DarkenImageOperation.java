package model.image.operations;

import model.image.Image;
import model.pixel.operations.DarkenPixelOperation;

public class DarkenImageOperation extends AbstractImageOperation {
  private int value;

  public DarkenImageOperation(int value) {
    this.value = value;
  }

  @Override
  public void execute(Image image) {
    helpExecute(image, new DarkenPixelOperation(value));
  }
}
