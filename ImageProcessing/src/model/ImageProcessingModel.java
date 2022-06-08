package model;

import model.image.operations.ImageOperation;

public interface ImageProcessingModel {
  void loadFile(String filePath, String name) throws IllegalArgumentException;
  void performAndSaveAs(String originalName, String newName, ImageOperation op);
  void saveAs(String fileName, String name) throws IllegalArgumentException;
}

