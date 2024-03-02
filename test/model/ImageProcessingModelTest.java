package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.image.MyImage;
import model.image.SimpleImage;
import model.image.operations.BrightenImageOperation;
import model.pixel.RGBAPixel;
import model.pixel.TransparentPixel;

import static org.junit.Assert.fail;

/**
 * A JUnit test class for the ImageProcessingModel interface.
 */
public class ImageProcessingModelTest {
  private ImageProcessingModel model;
  private MyImage testImage;

  @Before
  public void init() {
    model = new ImageProcessingModelImpl();

    ArrayList<TransparentPixel> row1
            = new ArrayList<TransparentPixel>(
                    Arrays.asList(new RGBAPixel(200, 100, 30),
                            new RGBAPixel(10, 0, 255)));
    ArrayList<TransparentPixel> row2
            = new ArrayList<TransparentPixel>(
            Arrays.asList(new RGBAPixel(30, 240, 210),
                    new RGBAPixel(0, 0, 0)));
    ArrayList<ArrayList<TransparentPixel>> imageList
            = new ArrayList<ArrayList<TransparentPixel>>(
                    Arrays.asList(row1, row2));

    testImage = new SimpleImage(imageList);
  }

  @Test
  public void testValidLoad() {
    model.loadImage(testImage, "image");
    try {
      model.getImageNamed("image");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testInValidLoad() {
    try {
      model.loadImage(null, "image");
      fail("Exception wasn't thrown");
    }
    catch (IllegalArgumentException e) {
      // exception thrown successfully
    }
  }

  @Test
  public void testValidPerformAndSaveAs() {
    try {
      model.loadImage(testImage, "image");
      model.performAndSaveAs("image", "image-bright",
              new BrightenImageOperation(10));
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testInvalidPerformAndSaveAs() {
    try {
      model.performAndSaveAs("image", "image-bright",
              new BrightenImageOperation(10));
      fail("Exception wasn't thrown");
    }
    catch (IllegalArgumentException e) {
      // exception successfully thrown
    }
  }

  @Test
  public void testGetImageNamed() {
    try {
      model.loadImage(testImage, "image");
      model.performAndSaveAs("image", "image-bright",
              new BrightenImageOperation(10));
      model.getImageNamed("image");
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