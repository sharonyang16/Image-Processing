package model.pixel;


import org.junit.Before;
import org.junit.Test;

import model.pixel.operations.BlueGreyscalePixelOperation;
import model.pixel.operations.RedGreyscalePixelOperation;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the TransparentPixel interface.
 */
public class TransparentPixelTest {
  private TransparentPixel pixel1;
  private TransparentPixel pixel2;

  @Before
  public void init() {
    this.pixel1 = new RGBAPixel(40, 20, 170, 200);
    this.pixel2 = new RGBAPixel(70, 230, 90);
  }

  @Test
  public void testCreateValidRGBAPixel() {
    this.pixel1 = new RGBAPixel(40, 20, 170, 200);
    assertEquals(40, pixel1.getRed());
    assertEquals(20, pixel1.getGreen());
    assertEquals(170, pixel1.getBlue());
    assertEquals(200, pixel1.getAlpha());

    this.pixel2 = new RGBAPixel(70, 230, 90);
    assertEquals(70, pixel2.getRed());
    assertEquals(230, pixel2.getGreen());
    assertEquals(90, pixel2.getBlue());
    assertEquals(255, pixel2.getAlpha());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBANegRed() {
    new RGBAPixel(-30, 100, 200, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBAInvalidRed() {
    new RGBAPixel(23423, 100, 200, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBANegGreen() {
    new RGBAPixel(0, -400, 110, 23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBAInvalidGreen() {
    new RGBAPixel(0, 400, 110, 23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBANegBlue() {
    new RGBAPixel(90, 123, -23, 150);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBAInvalidBlue() {
    new RGBAPixel(90, 123, 256, 150);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBANegAlpha() {
    new RGBAPixel(70, 220, 255, -255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBAInvalidAlpha() {
    new RGBAPixel(70, 220, 255, 290);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBInvalidRed() {
    new RGBAPixel(23423, 100, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBInvalidGreen() {
    new RGBAPixel(0, 400, 110);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBInvalidBlue() {
    new RGBAPixel(90, 123, -23);
  }

  @Test
  public void testGetRed() {
    assertEquals(40, pixel1.getRed());
    assertEquals(70, pixel2.getRed());

    pixel1.setRed(220);

    assertEquals(220, pixel1.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(20, pixel1.getGreen());
    assertEquals(230, pixel2.getGreen());

    pixel1.setGreen(10);

    assertEquals(10, pixel1.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(170, pixel1.getBlue());
    assertEquals(90, pixel2.getBlue());

    pixel1.setBlue(10);

    assertEquals(10, pixel1.getBlue());
  }

  @Test
  public void testGetAlpha() {
    assertEquals(200, pixel1.getAlpha());
    assertEquals(255, pixel2.getAlpha());

    pixel1.setAlpha(220);

    assertEquals(220, pixel1.getAlpha());
  }

  @Test
  public void testSetRed() {
    assertEquals(40, pixel1.getRed());

    pixel1.setRed(70);
    assertEquals(70, pixel1.getRed());

    pixel1.setRed(-20);
    assertEquals(0, pixel1.getRed());

    pixel1.setRed(300);
    assertEquals(255, pixel1.getRed());
  }

  @Test
  public void testSetGreen() {
    assertEquals(20, pixel1.getGreen());

    pixel1.setGreen(231);
    assertEquals(231, pixel1.getGreen());

    pixel1.setGreen(-10);
    assertEquals(0, pixel1.getGreen());

    pixel1.setGreen(400);
    assertEquals(255, pixel1.getGreen());
  }

  @Test
  public void testSetBlue() {
    assertEquals(170, pixel1.getBlue());

    pixel1.setBlue(231);
    assertEquals(231, pixel1.getBlue());

    pixel1.setBlue(-35);
    assertEquals(0, pixel1.getBlue());

    pixel1.setBlue(700);
    assertEquals(255, pixel1.getBlue());
  }

  @Test
  public void testSetAlpha() {
    assertEquals(200, pixel1.getAlpha());

    pixel1.setAlpha(110);
    assertEquals(110, pixel1.getAlpha());

    pixel1.setAlpha(-2000);
    assertEquals(0, pixel1.getAlpha());

    pixel1.setAlpha(10000);
    assertEquals(255, pixel1.getAlpha());
  }

  @Test
  public void testGreyscaleWith() {
    this.pixel1.greyscaleWith(10);

    assertEquals(10, pixel1.getRed());
    assertEquals(10, pixel1.getGreen());
    assertEquals(10, pixel1.getBlue());
    assertEquals(200, pixel1.getAlpha());
  }

  @Test
  public void testAdjustBrightenessBrighten() {
    this.pixel1.adjustBrightness(50);

    assertEquals(90, pixel1.getRed());
    assertEquals(70, pixel1.getGreen());
    assertEquals(220, pixel1.getBlue());
    assertEquals(200, pixel1.getAlpha());
  }

  @Test
  public void testAdjustBrightenessDarken() {
    this.pixel1.adjustBrightness(-30);

    assertEquals(10, pixel1.getRed());
    assertEquals(0, pixel1.getGreen());
    assertEquals(140, pixel1.getBlue());
    assertEquals(200, pixel1.getAlpha());
  }

  @Test
  public void testAdjustBrightenessClampToMin() {
    this.pixel1.adjustBrightness(-5000);

    assertEquals(0, pixel1.getRed());
    assertEquals(0, pixel1.getGreen());
    assertEquals(0, pixel1.getBlue());
    assertEquals(200, pixel1.getAlpha());
  }

  @Test
  public void testAdjustBrightenessClampToMax() {
    this.pixel1.adjustBrightness(5000);

    assertEquals(255, pixel1.getRed());
    assertEquals(255, pixel1.getGreen());
    assertEquals(255, pixel1.getBlue());
    assertEquals(200, pixel1.getAlpha());
  }

  @Test
  public void testAcceptRedGreyscale() {
    this.pixel1.accept(new RedGreyscalePixelOperation());

    assertEquals(40, pixel1.getRed());
    assertEquals(40, pixel1.getGreen());
    assertEquals(40, pixel1.getBlue());
    assertEquals(200, pixel1.getAlpha());
  }

  @Test
  public void testAcceptBlueGreyscale() {
    this.pixel1.accept(new BlueGreyscalePixelOperation());

    assertEquals(170, pixel1.getRed());
    assertEquals(170, pixel1.getGreen());
    assertEquals(170, pixel1.getBlue());
    assertEquals(200, pixel1.getAlpha());
  }

  @Test
  public void testGetCopy() {
    TransparentPixel pixelCopy = pixel2.getCopy();

    assertEquals(pixel2.getRed(), pixelCopy.getRed());
    assertEquals(pixel2.getGreen(), pixelCopy.getGreen());
    assertEquals(pixel2.getBlue(), pixelCopy.getBlue());
    assertEquals(pixel2.getAlpha(), pixelCopy.getAlpha());
  }
}