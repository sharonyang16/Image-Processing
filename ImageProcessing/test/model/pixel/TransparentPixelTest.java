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
}