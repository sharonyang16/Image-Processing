package model.pixel;

import org.junit.Before;
import org.junit.Test;

import model.pixel.operations.ColorTransformationPixelOperation;
import model.pixel.operations.GreenGreyscalePixelOperation;
import model.pixel.operations.GreyscalePixelOperation;
import model.pixel.operations.PixelOperation;
import model.pixel.operations.SepiaPixelOperation;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ColorTransformationPixelOperation class.
 */
public class ColorTransformationPixelOpTest {
  private Pixel pixel1;
  private Pixel pixel2;
  private Pixel pixel3;

  @Before
  public void init() {
    this.pixel1 = new RGBPixel(200, 100, 40);
    this.pixel2 = new RGBPixel(0, 0, 0);
    this.pixel3 = new RGBPixel(255, 255, 255);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialization() {
    PixelOperation op = new ColorTransformationPixelOperation(new double[][]{
            {0.393, 0.769},
            {0.349, 0.686}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialization2() {
    PixelOperation op = new ColorTransformationPixelOperation(new double[][]{
            {0.393, 0.769},
            {0.349, 0.686},
            {0.272, 0.534}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialization3() {
    PixelOperation op = new ColorTransformationPixelOperation(new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686}});
  }

  @Test
  public void testSepiaPixelOp() {
    this.pixel1.accept(new SepiaPixelOperation());
    assertEquals(163, this.pixel1.getRed());
    assertEquals(145, this.pixel1.getGreen());
    assertEquals(113, this.pixel1.getBlue());

    this.pixel2.accept(new SepiaPixelOperation());
    assertEquals(0, this.pixel2.getRed());
    assertEquals(0, this.pixel2.getGreen());
    assertEquals(0, this.pixel2.getBlue());

    this.pixel3.accept(new SepiaPixelOperation());
    assertEquals(255, this.pixel3.getRed());
    assertEquals(255, this.pixel3.getGreen());
    assertEquals(238, this.pixel3.getBlue());

  }

  @Test
  public void testGreyscalePixelOp() {
    this.pixel1.accept(new GreyscalePixelOperation());
    assertEquals(116, this.pixel1.getRed());
    assertEquals(116, this.pixel1.getGreen());
    assertEquals(116, this.pixel1.getBlue());

    this.pixel2.accept(new GreyscalePixelOperation());
    assertEquals(0, this.pixel2.getRed());
    assertEquals(0, this.pixel2.getGreen());
    assertEquals(0, this.pixel2.getBlue());

    this.pixel3.accept(new GreenGreyscalePixelOperation());
    assertEquals(255, this.pixel3.getRed());
    assertEquals(255, this.pixel3.getGreen());
    assertEquals(255, this.pixel3.getBlue());
  }
}
