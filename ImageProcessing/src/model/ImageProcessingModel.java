package model;

import model.image.Image;
import model.image.operations.ImageOperation;

public interface ImageProcessingModel {
  void loadFile(String filePath, String name);
  void performAndSaveAs(String originalName, String newName, ImageOperation op);
}

