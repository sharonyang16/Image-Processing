package model.pixel;

import org.junit.Before;
import org.junit.Test;

import model.pixel.operations.BlueGreyscalePixelOperation;
import model.pixel.operations.BrightenPixelOperation;
import model.pixel.operations.DarkenPixelOperation;
import model.pixel.operations.GreenGreyscalePixelOperation;
import model.pixel.operations.IntensityGreyscalePixelOperation;
import model.pixel.operations.LumaGreyscalePixelOperation;
import model.pixel.operations.RedGreyscalePixelOperation;
import model.pixel.operations.ValueGreyscalePixelOperation;

import static org.junit.Assert.assertEquals;

/**
 * A JUnite test class for the Pixel interface.
 */
public class PixelTest {
  private Pixel simplePixel1;

  @Before
  public void init() {

    int[] components = new int[3];
    components[0] = 200;
    components[1] = 100;
    components[2] = 40;

    this.simplePixel1 = new SimplePixel(components[0], components[1], components[2]);
  }

  @Test
  public void testValidInitialization() {
    assertEquals(200, this.simplePixel1.getRed());
    assertEquals(100, this.simplePixel1.getGreen());
    assertEquals(40, this.simplePixel1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNegativeRed() {

    int[] components = new int[3];
    components[0] = -1;
    components[1] = 100;
    components[2] = 40;

    this.simplePixel1 = new SimplePixel(components[0], components[1], components[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNegativeGreen() {

    int[] components = new int[3];
    components[0] = 200;
    components[1] = -1;
    components[2] = 40;

    this.simplePixel1 = new SimplePixel(components[0], components[1], components[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNegativeBlue() {

    int[] components = new int[3];
    components[0] = 200;
    components[1] = 100;
    components[2] = -1;

    this.simplePixel1 = new SimplePixel(components[0], components[1], components[2]);
  }

  //red component is greater than 255.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationRed() {

    int[] components = new int[3];
    components[0] = 300;
    components[1] = 100;
    components[2] = 40;

    this.simplePixel1 = new SimplePixel(components[0], components[1], components[2]);
  }

  //green component is greater than 255.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationGreen() {

    int[] components = new int[3];
    components[0] = 200;
    components[1] = 300;
    components[2] = 40;

    this.simplePixel1 = new SimplePixel(components[0], components[1], components[2]);
  }

  //blue compoenent is greater than 255.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationBlue() {

    int[] components = new int[3];
    components[0] = 200;
    components[1] = 100;
    components[2] = 300;

    this.simplePixel1 = new SimplePixel(components[0], components[1], components[2]);
  }

  @Test
  public void testAcceptBlueGreyscaleOp() {

    int[] components = new int[3];
    components[0] = 40;
    components[1] = 40;
    components[2] = 40;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);
    this.simplePixel1.accept(new BlueGreyscalePixelOperation());
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test
  public void testAcceptGreenGreyscaleOp() {

    int[] components = new int[3];
    components[0] = 100;
    components[1] = 100;
    components[2] = 100;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);

    this.simplePixel1.accept(new GreenGreyscalePixelOperation());

    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test
  public void testAcceptRedGreyscaleOp() {

    int[] components = new int[3];
    components[0] = 200;
    components[1] = 200;
    components[2] = 200;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);

    this.simplePixel1.accept(new RedGreyscalePixelOperation());

    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test
  public void testAcceptBrightenPixelOp() {

    int[] components = new int[3];
    components[0] = 210;
    components[1] = 110;
    components[2] = 50;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);

    this.simplePixel1.accept(new BrightenPixelOperation(10));

    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAcceptBrightenPixelOpNegativeValue() {

    this.simplePixel1.accept(new BrightenPixelOperation(-1));
  }

  @Test
  public void testAcceptBrightenPixelOpClampToMax() {

    int[] components = new int[3];
    components[0] = 255;
    components[1] = 255;
    components[2] = 255;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);
    this.simplePixel1.accept(new BrightenPixelOperation(500));

    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());

  }

  @Test
  public void testAcceptDarkenPixelOp() {

    int[] components = new int[3];
    components[0] = 190;
    components[1] = 90;
    components[2] = 30;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);

    this.simplePixel1.accept(new DarkenPixelOperation(10));

    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAcceptDarkenPixelOpNegativeValue() {

    this.simplePixel1.accept(new BrightenPixelOperation(-1));

  }

  @Test
  public void testAcceptDarkenPixelOpClampToMin() {

    int[] components = new int[3];
    components[0] = 0;
    components[1] = 0;
    components[2] = 0;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);

    this.simplePixel1.accept(new DarkenPixelOperation(500));
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test
  public void testAcceptIntensityGreyScaleOp() {

    int[] components = new int[3];
    components[0] = 113;
    components[1] = 113;
    components[2] = 113;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);
    this.simplePixel1.accept(new IntensityGreyscalePixelOperation());
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());

  }

  @Test
  public void testAcceptLumaGreyScaleOp() {

    int[] components = new int[3];
    components[0] = 116;
    components[1] = 116;
    components[2] = 116;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);

    this.simplePixel1.accept(new LumaGreyscalePixelOperation());
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());

  }

  @Test
  public void testAcceptValueGreyScaleOpRed() {

    int[] components = new int[3];
    components[0] = 200;
    components[1] = 200;
    components[2] = 200;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);
    this.simplePixel1.accept(new ValueGreyscalePixelOperation());
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test
  public void testAcceptValueGreyScaleOpGreen() {

    int[] components = new int[3];
    components[0] = 10;
    components[1] = 100;
    components[2] = 40;

    this.simplePixel1 = new SimplePixel(components[0], components[1], components[2]);

    int[] components2 = new int[3];
    components2[0] = 100;
    components2[1] = 100;
    components2[2] = 100;

    Pixel simplePixel2 = new SimplePixel(components2[0], components2[1], components2[2]);

    this.simplePixel1.accept(new ValueGreyscalePixelOperation());
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());

  }

  @Test
  public void testAcceptValueGreyScaleOpBlue() {

    int[] components = new int[3];
    components[0] = 10;
    components[1] = 100;
    components[2] = 150;

    this.simplePixel1 = new SimplePixel(components[0], components[1], components[2]);

    int[] components2 = new int[3];
    components2[0] = 150;
    components2[1] = 150;
    components2[2] = 150;

    Pixel simplePixel2 = new SimplePixel(components2[0], components2[1], components2[2]);

    this.simplePixel1.accept(new ValueGreyscalePixelOperation());
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());

  }

  @Test
  public void testGetRed() {
    assertEquals(200, this.simplePixel1.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(100, this.simplePixel1.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(40, this.simplePixel1.getBlue());
  }

  @Test
  public void testGreyscaleWith() {

    int[] components = new int[3];
    components[0] = 10;
    components[1] = 10;
    components[2] = 10;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);
    this.simplePixel1.greyscaleWith(10);
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreyscaleWithNegativeValue() {
    this.simplePixel1.greyscaleWith(-1);
  }

  //value is greater than 255,
  @Test(expected = IllegalArgumentException.class)
  public void testGreyscaleWithMoreThanMaxValue() {
    this.simplePixel1.greyscaleWith(300);
  }

  @Test
  public void testAdjustBrightnessBrighten() {

    int[] components = new int[3];
    components[0] = 210;
    components[1] = 110;
    components[2] = 50;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);
    this.simplePixel1.adjustBrightness(10);
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test
  public void testAdjustBrightnessClampToMin() {
    int[] components = new int[3];
    components[0] = 0;
    components[1] = 0;
    components[2] = 0;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);
    this.simplePixel1.adjustBrightness(-500);
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test
  public void testAdjustBrightnessClampToMax() {
    int[] components = new int[3];
    components[0] = 255;
    components[1] = 255;
    components[2] = 255;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);

    this.simplePixel1.adjustBrightness(500);
    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }

  @Test
  public void testGetCopy() {
    int[] components = new int[3];
    components[0] = 200;
    components[1] = 100;
    components[2] = 40;

    Pixel simplePixel2 = new SimplePixel(components[0], components[1], components[2]);

    assertEquals(simplePixel2.getRed(), simplePixel1.getRed());
    assertEquals(simplePixel2.getGreen(), simplePixel1.getGreen());
    assertEquals(simplePixel2.getBlue(), simplePixel1.getBlue());
  }
}