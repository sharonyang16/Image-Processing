package model;

import model.image.MyImage;
import model.image.operations.ImageOperation;

public class CurrentImageProcessingModelImpl
        extends ImageProcessingModelImpl implements CurrentImageProcessingModel {
  private MyImage currentImage;

  public CurrentImageProcessingModelImpl() {
    super();
    currentImage = null;
  }

  @Override
  public void loadImage(MyImage image, String name) {
    super.loadImage(image, name);
    this.currentImage = image;
  }

  @Override
  public void performAndSaveAs(String originalName, String newName, ImageOperation op)
          throws IllegalArgumentException {
    super.performAndSaveAs(originalName, newName, op);
    this.currentImage = this.images.get(newName);
  }

  @Override
  public MyImage getCurrentImage() {
    return this.currentImage;
  }
}
