package model.image.operations;

import model.image.MyImage;

public class FlipHorizontallyImageOperation implements ImageOperation {

  @Override
  public void execute(MyImage image) {
    image.flipHorizontally();
  }
}
