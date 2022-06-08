package model.image.operations;

import model.image.MyImage;
import model.pixel.operations.BrightenPixelOperation;

public class BrightenImageOperation extends AbstractImageOperation{
  private int value;

  public BrightenImageOperation(int value) {
    this.value = value;
  }


  @Override
  public void execute(MyImage image) {
    helpExecute(image, new BrightenPixelOperation(value));
  }
}
