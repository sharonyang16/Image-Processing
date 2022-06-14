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
  public void testValidLoadFile() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLoadFile() {
    model.loadFile("res/Landscape.jpg", "land");
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
      fail("Exception thrown");
    }
    catch (IllegalArgumentException e) {
      // exception successfully thrown
    }
  }

  @Test
  public void testValidSave() {
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
      fail("Exception thrown");
    }
    catch (IllegalArgumentException e) {
      // exception successfully thrown
    }
  }

  @Test
  public void testInvalidSaveType() {
    try {
      model.loadFile("res/Landscape.ppm", "land");
      model.performAndSaveAs("land", "land-bright",
              new BrightenImageOperation(10));
      model.saveAs("res/Landscape-new.jpg", "land-bright");
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