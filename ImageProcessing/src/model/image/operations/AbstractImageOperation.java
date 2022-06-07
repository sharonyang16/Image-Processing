package model.image.operations;

import model.image.Image;
import model.pixel.operations.PixelOperation;

public abstract class AbstractImageOperation implements ImageOperation {

  public abstract void execute(Image image);

  protected void helpExecute(Image image, PixelOperation o) {
    for (int i = 0; i < image.getHeight(); i = i + 1) {
      for (int j = 0; j < image.getWidth(); j = j + 1) {
        image.getPixelAt(i, j).accept(o);
      }
    }
  }
}
