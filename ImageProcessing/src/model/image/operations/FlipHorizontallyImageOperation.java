package model.image.operations;

import model.image.Image;

public class FlipHorizontallyImageOperation implements ImageOperation {

  @Override
  public void execute(Image image) {
    image.flipHorizontally();
  }
}
