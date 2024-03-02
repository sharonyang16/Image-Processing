package model.image.operations;

import model.image.MyImage;

/**
 * This class represents an operation that flips an image vertically.
 */
public final class FlipVerticallyImageOperation implements ImageOperation {
  @Override
  public void execute(MyImage image) {
    image.flipVertically();
  }
}
