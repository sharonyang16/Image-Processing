package model.image.operations;

import model.image.MyImage;

/**
 * This class represents an operation that flips an image horizontally.
 */
public final class FlipHorizontallyImageOperation implements ImageOperation {
  @Override
  public void execute(MyImage image) {
    image.flipHorizontally();
  }
}
