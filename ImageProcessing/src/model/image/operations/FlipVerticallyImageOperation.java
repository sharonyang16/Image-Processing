package model.image.operations;

import model.image.MyImage;

public class FlipVerticallyImageOperation implements ImageOperation {

  @Override
  public void execute(MyImage image) {
    image.flipVertically();
  }
}
