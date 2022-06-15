package model.image;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.image.operations.BlurImageOperation;
import model.image.operations.FilterImageOperation;
import model.image.operations.GreyscaleImageOperation;
import model.image.operations.ImageOperation;
import model.pixel.RGBAPixel;
import model.pixel.TransparentPixel;

import static org.junit.Assert.assertEquals;

public class FilterImageOpTest {


  private int[] components1;
  private int[] components2;
  private int[] components3;
  private int[] components4;
  private int[] components5;
  private int[] components6;
  private int[] components7;
  private int[] components8;
  private int[] components9;
  private TransparentPixel pixel1;
  private TransparentPixel pixel2;
  private TransparentPixel pixel3;
  private TransparentPixel pixel4;
  private TransparentPixel pixel5;
  private TransparentPixel pixel6;
  private TransparentPixel pixel7;
  private TransparentPixel pixel8;
  private TransparentPixel pixel9;

  private ArrayList<TransparentPixel> subImage1;
  private ArrayList<TransparentPixel> subImage2;
  private ArrayList<TransparentPixel> subImage3;
  private ArrayList<ArrayList<TransparentPixel>> image;

  private MyImage simpleImage;



  @Before
  public void init() {

    this.components1 = new int[3];
    components1[0] = 200;
    components1[1] = 100;
    components1[2] = 40;
    this.pixel1 = new RGBAPixel(200, 100, 40);

    this.components2 = new int[3];
    components2[0] = 100;
    components2[1] = 40;
    components2[2] = 200;
    this.pixel2 = new RGBAPixel(100, 40, 200);

    this.components3 = new int[3];
    components3[0] = 40;
    components3[1] = 200;
    components3[2] = 100;
    this.pixel3 = new RGBAPixel(40, 200, 100);

    this.components4 = new int[3];
    components4[0] = 50;
    components4[1] = 20;
    components4[2] = 100;
    this.pixel4 = new RGBAPixel(50, 20, 100);

    this.components5 = new int[3];
    components5[0] = 20;
    components5[1] = 100;
    components5[2] = 50;
    this.pixel5 = new RGBAPixel(20, 100, 50);

    this.components6 = new int[3];
    components6[0] = 100;
    components6[1] = 50;
    components6[2] = 20;
    this.pixel6 = new RGBAPixel(100, 50, 20);

    this.components7 = new int[3];
    components7[0] = 150;
    components7[1] = 90;
    components7[2] = 250;
    this.pixel7 = new RGBAPixel(150, 90, 250);

    this.components8 = new int[3];
    components8[0] = 90;
    components8[1] = 250;
    components8[2] = 150;
    this.pixel8 = new RGBAPixel(90, 250, 150);

    this.components9 = new int[3];
    components9[0] = 250;
    components9[1] = 150;
    components9[2] = 90;
    this.pixel9 = new RGBAPixel(250, 150, 90);

    this.subImage1 = new ArrayList<>(Arrays.asList(pixel1, pixel2, pixel3));

    this.subImage2 = new ArrayList<>(Arrays.asList(pixel4, pixel5, pixel6));

    this.subImage3 = new ArrayList<>(Arrays.asList(pixel7, pixel8, pixel9));

    this.image = new ArrayList<ArrayList<TransparentPixel>>(Arrays.asList(subImage1, subImage2, subImage3));

    this.simpleImage = new SimpleImage(image);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialization() {
    ImageOperation op = new FilterImageOperation(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialization2() {
    ImageOperation op = new FilterImageOperation(new double[][]{
            {0.393, 0.769}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationEvenDimensions() {
    ImageOperation op = new FilterImageOperation(new double[][]{
            {0.393, 0.769},
            {0.349, 0.686}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialization3() {
    ImageOperation op = new FilterImageOperation(new double[][]{
            {0.393},
            {0.349}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNotSquare() {
    ImageOperation op = new FilterImageOperation(new double[][]{
            {0.393, 0.769},
            {0.349, 0.686},
            {0.272, 0.534, 0.023}});
  }

  @Test
  public void testBlurOp() {
    this.simpleImage.accept(new BlurImageOperation());

    assertEquals(70, this.simpleImage.getPixelAt(0, 0).getRed());
    assertEquals(38, this.simpleImage.getPixelAt(0, 0).getGreen());
    assertEquals(50, this.simpleImage.getPixelAt(0, 0).getBlue());

    assertEquals(66, this.simpleImage.getPixelAt(0, 1).getRed());
    assertEquals(64, this.simpleImage.getPixelAt(0, 1).getGreen());
    assertEquals(81, this.simpleImage.getPixelAt(0, 1).getBlue());

    assertEquals(36, this.simpleImage.getPixelAt(0, 2).getRed());
    assertEquals(67, this.simpleImage.getPixelAt(0, 2).getGreen());
    assertEquals(55, this.simpleImage.getPixelAt(0, 2).getBlue());

    assertEquals(70, this.simpleImage.getPixelAt(1, 0).getRed());
    assertEquals(59, this.simpleImage.getPixelAt(1, 0).getGreen());
    assertEquals(89, this.simpleImage.getPixelAt(1, 0).getBlue());

    assertEquals(56, this.simpleImage.getPixelAt(2, 0).getRed());
    assertEquals(62, this.simpleImage.getPixelAt(2, 0).getGreen());
    assertEquals(96, this.simpleImage.getPixelAt(2, 0).getBlue());

    assertEquals(87, this.simpleImage.getPixelAt(1, 1).getRed());
    assertEquals(103, this.simpleImage.getPixelAt(1, 1).getGreen());
    assertEquals(101, this.simpleImage.getPixelAt(1, 1).getBlue());

    assertEquals(75, this.simpleImage.getPixelAt(1, 2).getRed());
    assertEquals(86, this.simpleImage.getPixelAt(1, 2).getGreen());
    assertEquals(56, this.simpleImage.getPixelAt(1, 2).getBlue());

    assertEquals(84, this.simpleImage.getPixelAt(2, 1).getRed());
    assertEquals(109, this.simpleImage.getPixelAt(2, 1).getGreen());
    assertEquals(93, this.simpleImage.getPixelAt(2, 1).getBlue());

    assertEquals(87, this.simpleImage.getPixelAt(2, 2).getRed());
    assertEquals(81, this.simpleImage.getPixelAt(2, 2).getGreen());
    assertEquals(46, this.simpleImage.getPixelAt(2, 2).getBlue());


  }

  @Test
  public void testGreyscaleImageOp() {
    this.simpleImage.accept(new GreyscaleImageOperation());

    assertEquals(187, this.simpleImage.getPixelAt(0, 0).getRed());
    assertEquals(95, this.simpleImage.getPixelAt(0, 0).getGreen());
    assertEquals(118, this.simpleImage.getPixelAt(0, 0).getBlue());

    assertEquals(149, this.simpleImage.getPixelAt(0, 1).getRed());
    assertEquals(143, this.simpleImage.getPixelAt(0, 1).getGreen());
    assertEquals(217, this.simpleImage.getPixelAt(0, 1).getBlue());

    assertEquals(125, this.simpleImage.getPixelAt(0, 2).getRed());
    assertEquals(208, this.simpleImage.getPixelAt(0, 2).getGreen());
    assertEquals(138, this.simpleImage.getPixelAt(0, 2).getBlue());

    assertEquals(255, this.simpleImage.getPixelAt(1, 0).getRed());
    assertEquals(178, this.simpleImage.getPixelAt(1, 0).getGreen());
    assertEquals(255, this.simpleImage.getPixelAt(1, 0).getBlue());

    assertEquals(150, this.simpleImage.getPixelAt(2, 0).getRed());
    assertEquals(103, this.simpleImage.getPixelAt(2, 0).getGreen());
    assertEquals(255, this.simpleImage.getPixelAt(2, 0).getBlue());

    assertEquals(255, this.simpleImage.getPixelAt(1, 1).getRed());
    assertEquals(255, this.simpleImage.getPixelAt(1, 1).getGreen());
    assertEquals(255, this.simpleImage.getPixelAt(1, 1).getBlue());

    assertEquals(255, this.simpleImage.getPixelAt(1, 2).getRed());
    assertEquals(255, this.simpleImage.getPixelAt(1, 2).getGreen());
    assertEquals(235, this.simpleImage.getPixelAt(1, 2).getBlue());

    assertEquals(146, this.simpleImage.getPixelAt(2, 1).getRed());
    assertEquals(255, this.simpleImage.getPixelAt(2, 1).getGreen());
    assertEquals(225, this.simpleImage.getPixelAt(2, 1).getBlue());

    assertEquals(255, this.simpleImage.getPixelAt(2, 2).getRed());
    assertEquals(217, this.simpleImage.getPixelAt(2, 2).getGreen());
    assertEquals(121, this.simpleImage.getPixelAt(2, 2).getBlue());
  }



}
