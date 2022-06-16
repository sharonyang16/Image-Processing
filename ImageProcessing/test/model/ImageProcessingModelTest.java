package model;

import org.junit.Before;
import org.junit.Test;

import model.image.operations.BrightenImageOperation;

import static org.junit.Assert.fail;

/**
 * A JUnit test class for the ImageProcessingModel interface.
 */
public class ImageProcessingModelTest {
  private ImageProcessingModel model;

  @Before
  public void init() {
    model = new ImageProcessingModelImpl();
  }

  @Test
  public void testValidLoadFilePPM() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testValidLoadFileJPG() {
    try {
      model.loadFile("res/Landscape.jpg", "land");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testValidLoadFilePNG() {
    try {
      model.loadFile("res/Landscape.png", "land");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testValidLoadFileBMP() {
    try {
      model.loadFile("res/Landscape.bmp", "land");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLoadFile() {
    model.loadFile("res/Landscape.txt", "land");
  }

  @Test
  public void testValidPerformAndSaveAs() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
      model.performAndSaveAs("land", "land-bright",
              new BrightenImageOperation(10));
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testInvalidPerformAndSaveAs() {
    try {
      model.performAndSaveAs("land", "land-bright",
              new BrightenImageOperation(10));
      fail("Exception wasn't thrown");
    }
    catch (IllegalArgumentException e) {
      // exception successfully thrown
    }
  }

  @Test
  public void testValidSavePPM() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
      model.performAndSaveAs("land", "land-bright",
              new BrightenImageOperation(10));
      model.saveAs("res/Landscape-new.ppm", "land-bright");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testInvalidSaveImage() {
    try {
      model.saveAs("res/sdfsd.ppm", "image");
      fail("Exception wasn't thrown");
    }
    catch (IllegalArgumentException e) {
      // exception successfully thrown
    }
  }

  @Test
  public void testValidSaveJPG() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
      model.performAndSaveAs("land", "land-bright",
              new BrightenImageOperation(10));
      model.saveAs("res/Landscape-new.jpg", "land-bright");

    }
    catch (IllegalArgumentException e) {
      fail("Exception was thrown");
    }
  }

  @Test
  public void testValidSavePNG() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
      model.performAndSaveAs("land", "land-bright",
              new BrightenImageOperation(10));
      model.saveAs("res/Landscape-new.png", "land-bright");
    }
    catch (IllegalArgumentException e) {
      fail("Exception was thrown");
    }
  }

  @Test
  public void testValidSaveBMP() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
      model.performAndSaveAs("land", "land-bright",
              new BrightenImageOperation(10));
      model.saveAs("res/Landscape-new.bmp", "land-bright");
    }
    catch (IllegalArgumentException e) {
      fail("Exception was thrown");
    }
  }

  @Test
  public void testInvalidSave() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
      model.performAndSaveAs("land", "land-bright",
              new BrightenImageOperation(10));
      model.saveAs("res/Landscape-new.pdf", "land-bright");
      fail("Exception wasn't thrown");
    }
    catch (IllegalArgumentException e) {
      // exception successfully thrown
    }
  }

  @Test
  public void testGetImageNamed() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
      model.performAndSaveAs("land", "land-bright",
              new BrightenImageOperation(10));
      model.getImageNamed("land");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testInvalidGetImageNamed() {
    try {
      model.getImageNamed("hi");
      fail("Exception wasn't thrown");
    }
    catch (IllegalArgumentException e) {
      // exception successfully thrown
    }
  }
}