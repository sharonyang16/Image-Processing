package model.image.operations;

import model.image.Image;
import model.pixel.operations.BrightenPixelOperation;

public class BrightenImageOperation extends AbstractImageOperation{
  private int value;

  public BrightenImageOperation(int value) {
    this.value = value;
  }


  @Override
  public void execute(Image image) {
    helpExecute(image, new BrightenPixelOperation(value));
  }
}
