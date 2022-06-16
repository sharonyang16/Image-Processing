package model.pixel;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
  public void testCreateRGBAInvalidRed() {
    new RGBAPixel(23423, 100, 200, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBAInvalidGreen() {
    new RGBAPixel(0, 400, 110, 23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBAInvalidBlue() {
    new RGBAPixel(90, 123, -23, 150);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRGBAInvalidAlpha() {
    new RGBAPixel(70, 220, 255, -255);
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
  public void testGetAlpha() {
    assertEquals(200, pixel1.getAlpha());
    assertEquals(255, pixel2.getAlpha());

    pixel1.setAlpha(220);

    assertEquals(220, pixel1.getAlpha());
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
  public void testGetCopy() {
    TransparentPixel pixelCopy = pixel2.getCopy();

    assertEquals(pixel2.getRed(), pixelCopy.getRed());
    assertEquals(pixel2.getGreen(), pixelCopy.getGreen());
    assertEquals(pixel2.getBlue(), pixelCopy.getBlue());
    assertEquals(pixel2.getAlpha(), pixelCopy.getAlpha());
  }
}