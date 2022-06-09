package model;

import model.image.operations.ImageOperation;

/**
 * This interface represents the model of an imagine processing application. This model is able to
 * hold multiple images at once and has the capabilities to load and save.
 */
public interface ImageProcessingModel {
  /**
   *
   * @param filePath
   * @param name
   * @throws IllegalArgumentException
   */
  void loadFile(String filePath, String name) throws IllegalArgumentException;

  /**
   *
   * @param originalName
   * @param newName
   * @param op
   * @throws IllegalArgumentException
   */
  void performAndSaveAs(String originalName, String newName, ImageOperation op)
          throws IllegalArgumentException;

  /**
   *
   * @param fileName
   * @param name
   * @throws IllegalArgumentException
   */
  void saveAs(String fileName, String name) throws IllegalArgumentException;
}

