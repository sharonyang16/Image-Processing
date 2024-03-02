package model;

import java.util.HashMap;
import java.util.Map;

import model.image.MyImage;
import model.image.operations.ImageOperation;

/**
 * This class represents an implementation of a model for an image processing application. It
 * specifically implements the ImageProcessingModel interface. Currently, this model only supports
 * loading, storing, and saving PPM, JPG, PNG, and BMP files. Previously, only able to support PPM
 * files.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  protected Map<String, MyImage> images;

  /**
   * Creates an empty model with no images.
   */
  public ImageProcessingModelImpl() {
    this.images = new HashMap<String, MyImage>();
  }

  @Override
  public void loadImage(MyImage image, String name) {
    if (image == null) {
      throw new IllegalArgumentException("MyImage object is null!");
    }
    this.images.put(name, image);
  }

  @Override
  public void performAndSaveAs(String originalName, String newName, ImageOperation op)
          throws IllegalArgumentException {
    MyImage image = this.images.getOrDefault(originalName, null);

    if (image == null) {
      throw new IllegalArgumentException("Image not found.");
    }
    else {
      MyImage newImage = image.getCopy();
      newImage.accept(op);

      this.images.put(newName, newImage);
    }
  }

  @Override
  public MyImage getImageNamed(String name) throws IllegalArgumentException {
    MyImage image = this.images.getOrDefault(name, null);

    // if the name of this image doesn't exist in this model, throw an exception
    if (image == null) {
      throw new IllegalArgumentException("This image does not exist!");
    }
    // else, return the MyImage representation of it
    else {
      return image;
    }
  }
}
