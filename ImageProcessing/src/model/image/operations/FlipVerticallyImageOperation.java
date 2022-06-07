package model.image.operations;

import model.image.Image;

public class FlipVerticallyImageOperation implements ImageOperation {

  @Override
  public void execute(Image image) {
    image.flipVertically();
  }
}
