package model.pixel;

import org.junit.Before;
import org.junit.Test;

import model.pixel.operations.BlueGreyscalePixelOperation;
import model.pixel.operations.BrightenPixelOperation;
import model.pixel.operations.ColorTransformationPixelOperation;
import model.pixel.operations.DarkenPixelOperation;
import model.pixel.operations.GreenGreyscalePixelOperation;
import model.pixel.operations.GreyscalePixelOperation;
import model.pixel.operations.IntensityGreyscalePixelOperation;
import model.pixel.operations.LumaGreyscalePixelOperation;
import model.pixel.operations.PixelOperation;
import model.pixel.operations.RedGreyscalePixelOperation;
import model.pixel.operations.SepiaPixelOperation;
import model.pixel.operations.ValueGreyscalePixelOperation;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Pixel interface.
 */
public class PixelTest {
  private Pixel pixel1;
  private Pixel pixel2;
  private Pixel pixel3;

  @Before
  public void init() {
    this.pixel1 = new RGBPixel(200, 100, 40);
    this.pixel2 = new RGBPixel(0, 0, 0);
    this.pixel3 = new RGBPixel(255, 255, 255);
  }

  @Test
  public void testValidInitialization() {
    this.pixel1 = new RGBPixel(200, 100, 40);
    assertEquals(200, this.pixel1.getRed());
    assertEquals(100, this.pixel1.getGreen());
    assertEquals(40, this.pixel1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNegativeRed() {
    this.pixel1 = new RGBPixel(-1, 100, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNegativeGreen() {
    this.pixel1 = new RGBPixel(200, -1, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNegativeBlue() {
    this.pixel1 = new RGBPixel(200, 100, -1);
  }

  //red component is greater than 255.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationRed() {
    this.pixel1 = new RGBPixel(300, 100, 40);
  }

  //green component is greater than 255.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationGreen() {
    this.pixel1 = new RGBPixel(200, 300, 40);
  }

  //blue component is greater than 255.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationBlue() {
    this.pixel1 = new RGBPixel(200, 100, 400);
  }

  @Test
  public void testAcceptBlueGreyscaleOp() {
    this.pixel2 = new RGBPixel(40, 40, 40);
    this.pixel1.accept(new BlueGreyscalePixelOperation());

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testAcceptGreenGreyscaleOp() {
    this.pixel2 = new RGBPixel(100, 100, 100);
    this.pixel1.accept(new GreenGreyscalePixelOperation());

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testAcceptRedGreyscaleOp() {
    this.pixel2 = new RGBPixel(200, 200, 200);
    this.pixel1.accept(new RedGreyscalePixelOperation());

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testAcceptBrightenPixelOp() {
    this.pixel2 = new RGBPixel(210, 110, 50);
    this.pixel1.accept(new BrightenPixelOperation(10));

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAcceptBrightenPixelOpNegativeValue() {
    this.pixel1.accept(new BrightenPixelOperation(-1));
  }

  @Test
  public void testAcceptBrightenPixelOpClampToMax() {
    this.pixel2 = new RGBPixel(255, 255, 255);
    this.pixel1.accept(new BrightenPixelOperation(500));

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testAcceptDarkenPixelOp() {
    this.pixel2 = new RGBPixel(190, 90, 30);
    this.pixel1.accept(new DarkenPixelOperation(10));

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAcceptDarkenPixelOpNegativeValue() {
    this.pixel1.accept(new DarkenPixelOperation(-1));
  }

  @Test
  public void testAcceptDarkenPixelOpClampToMin() {
    this.pixel2 = new RGBPixel(0, 0, 0);
    this.pixel1.accept(new DarkenPixelOperation(500));

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testAcceptIntensityGreyScaleOp() {
    this.pixel2 = new RGBPixel(113, 113, 113);
    this.pixel1.accept(new IntensityGreyscalePixelOperation());

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());

  }

  @Test
  public void testAcceptLumaGreyScaleOp() {
    this.pixel2 = new RGBPixel(116, 116, 116);
    this.pixel1.accept(new LumaGreyscalePixelOperation());

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());

  }

  @Test
  public void testAcceptValueGreyScaleOpRed() {
    this.pixel2 = new RGBPixel(200, 200, 200);
    this.pixel1.accept(new ValueGreyscalePixelOperation());

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testAcceptValueGreyScaleOpGreen() {
    this.pixel1 = new RGBPixel(10, 100, 40);
    this.pixel2 = new RGBPixel(100, 100, 100);
    this.pixel1.accept(new ValueGreyscalePixelOperation());

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());

  }

  @Test
  public void testAcceptValueGreyScaleOpBlue() {
    this.pixel1 = new RGBPixel(10, 100, 150);
    this.pixel2 = new RGBPixel(150, 150, 150);
    this.pixel1.accept(new ValueGreyscalePixelOperation());

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testGetRed() {
    assertEquals(200, this.pixel1.getRed());

    this.pixel1.setRed(25);
    assertEquals(25, this.pixel1.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(100, this.pixel1.getGreen());

    this.pixel1.setGreen(200);
    assertEquals(200, this.pixel1.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(40, this.pixel1.getBlue());

    this.pixel1.setBlue(50);
    assertEquals(50, this.pixel1.getBlue());
  }

  @Test
  public void testGreyscaleWith() {
    this.pixel2 = new RGBPixel(10, 10, 10);
    this.pixel1.greyscaleWith(10);

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreyscaleWithNegativeValue() {
    this.pixel1.greyscaleWith(-1);
  }

  //value is greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testGreyscaleWithMoreThanMaxValue() {
    this.pixel1.greyscaleWith(300);
  }

  @Test
  public void testAdjustBrightnessBrighten() {
    this.pixel2 = new RGBPixel(210, 110, 50);
    this.pixel1.adjustBrightness(10);
    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testAdjustBrightnessClampToMin() {
    this.pixel2 = new RGBPixel(0, 0, 0);
    this.pixel1.adjustBrightness(-500);

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testAdjustBrightnessClampToMax() {
    this.pixel2 = new RGBPixel(255, 255, 255);
    this.pixel1.adjustBrightness(500);

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testGetCopy() {
    this.pixel2 = new RGBPixel(200, 100, 40);

    assertEquals(pixel2.getRed(), pixel1.getRed());
    assertEquals(pixel2.getGreen(), pixel1.getGreen());
    assertEquals(pixel2.getBlue(), pixel1.getBlue());
  }

  @Test
  public void testSetRed() {
    assertEquals(200, this.pixel1.getRed());

    this.pixel1.setRed(100);
    assertEquals(100, this.pixel1.getRed());

    this.pixel1.setRed(1000);
    assertEquals(255, this.pixel1.getRed());

    this.pixel1.setRed(-23);
    assertEquals(0, this.pixel1.getRed());
  }

  @Test
  public void testSetGreen() {
    assertEquals(100, this.pixel1.getGreen());

    this.pixel1.setGreen(179);
    assertEquals(179, this.pixel1.getGreen());

    this.pixel1.setGreen(260);
    assertEquals(255, this.pixel1.getGreen());

    this.pixel1.setGreen(-3000);
    assertEquals(0, this.pixel1.getGreen());
  }

  @Test
  public void testSetBlue() {
    assertEquals(40, this.pixel1.getBlue());

    this.pixel1.setBlue(65);
    assertEquals(65, this.pixel1.getBlue());

    this.pixel1.setBlue(700);
    assertEquals(255, this.pixel1.getBlue());

    this.pixel1.setBlue(-1);
    assertEquals(0, this.pixel1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorTransformationInitialization() {
    PixelOperation op = new ColorTransformationPixelOperation(new double[][]{
            {0.393, 0.769},
            {0.349, 0.686}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorTransformationInitialization2() {
    PixelOperation op = new ColorTransformationPixelOperation(new double[][]{
            {0.393, 0.769},
            {0.349, 0.686},
            {0.272, 0.534}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorTransformationInitialization3() {
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