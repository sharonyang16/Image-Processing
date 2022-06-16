package model.image;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.image.operations.BlurImageOperation;
import model.image.operations.FilterImageOperation;
import model.image.operations.GreyscaleImageOperation;
import model.image.operations.ImageOperation;
import model.image.operations.SepiaImageOperation;
import model.image.operations.SharpenImageOperation;
import model.pixel.RGBAPixel;
import model.pixel.TransparentPixel;

import static org.junit.Assert.assertEquals;

public class FilterAndColorTransformationImageOpTest {
  private TransparentPixel pixel1;
  private TransparentPixel pixel2;
  private TransparentPixel pixel3;
  private TransparentPixel pixel4;
  private TransparentPixel pixel5;
  private TransparentPixel pixel6;
  private TransparentPixel pixel7;
  private TransparentPixel pixel8;
  private TransparentPixel pixel9;
  private TransparentPixel pixel10;
  private TransparentPixel pixel11;
  private TransparentPixel pixel12;
  private TransparentPixel pixel13;
  private TransparentPixel pixel14;
  private TransparentPixel pixel15;
  private TransparentPixel pixel16;
  private TransparentPixel pixel17;
  private TransparentPixel pixel18;
  private TransparentPixel pixel19;
  private TransparentPixel pixel20;
  private TransparentPixel pixel21;
  private TransparentPixel pixel22;
  private TransparentPixel pixel23;
  private TransparentPixel pixel24;
  private TransparentPixel pixel25;

  private ArrayList<TransparentPixel> subImage1;
  private ArrayList<TransparentPixel> subImage2;
  private ArrayList<TransparentPixel> subImage3;
  private ArrayList<TransparentPixel> subImage4;
  private ArrayList<TransparentPixel> subImage5;
  private ArrayList<TransparentPixel> subImage6;
  private ArrayList<TransparentPixel> subImage7;
  private ArrayList<TransparentPixel> subImage8;

  private ArrayList<ArrayList<TransparentPixel>> image;
  private ArrayList<ArrayList<TransparentPixel>> image2;

  private MyImage simpleImage;
  private MyImage simpleImage2;


  @Before
  public void init() {

    this.pixel1 = new RGBAPixel(200, 100, 40);

    this.pixel2 = new RGBAPixel(100, 40, 200);

    this.pixel3 = new RGBAPixel(40, 200, 100);

    this.pixel4 = new RGBAPixel(50, 20, 100);

    this.pixel5 = new RGBAPixel(20, 100, 50);

    this.pixel6 = new RGBAPixel(100, 50, 20);

    this.pixel7 = new RGBAPixel(150, 90, 250);

    this.pixel8 = new RGBAPixel(90, 250, 150);

    this.pixel9 = new RGBAPixel(250, 150, 90);

    this.pixel10 = new RGBAPixel(70, 80, 90);

    this.pixel11 = new RGBAPixel(90, 70, 80);

    this.pixel12 = new RGBAPixel(80, 90, 70);

    this.pixel13 = new RGBAPixel(110, 89, 43);

    this.pixel14 = new RGBAPixel(89, 43, 110);

    this.pixel15 = new RGBAPixel(43, 110, 89);

    this.pixel16 = new RGBAPixel(205, 1, 50);

    this.pixel17 = new RGBAPixel(1, 50, 205);

    this.pixel18 = new RGBAPixel(50, 205, 1);

    this.pixel19 = new RGBAPixel(19, 20, 85);

    this.pixel20 = new RGBAPixel(20, 85, 19);

    this.pixel21 = new RGBAPixel(85, 19, 20);

    this.pixel22 = new RGBAPixel(55, 158, 240);

    this.pixel23 = new RGBAPixel(158, 240, 55);

    this.pixel24 = new RGBAPixel(240, 55, 158);

    this.pixel25 = new RGBAPixel(99, 7, 13);

    this.subImage1 = new ArrayList<>(Arrays.asList(pixel1, pixel2, pixel3));

    this.subImage2 = new ArrayList<>(Arrays.asList(pixel4, pixel5, pixel6));

    this.subImage3 = new ArrayList<>(Arrays.asList(pixel7, pixel8, pixel9));

    this.subImage4 = new ArrayList<>(Arrays.asList(pixel1, pixel2, pixel3, pixel4, pixel5));

    this.subImage5 = new ArrayList<>(Arrays.asList(pixel6, pixel7, pixel8, pixel9, pixel10));

    this.subImage6 = new ArrayList<>(Arrays.asList(pixel11, pixel12, pixel13, pixel14, pixel15));

    this.subImage7 = new ArrayList<>(Arrays.asList(pixel16, pixel17, pixel18, pixel19, pixel20));

    this.subImage8 = new ArrayList<>(Arrays.asList(pixel21, pixel22, pixel23, pixel24, pixel25));

    this.image = new ArrayList<ArrayList<TransparentPixel>>
            (Arrays.asList(subImage1, subImage2, subImage3));

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>
            (Arrays.asList(subImage4, subImage5, subImage6, subImage7, subImage8));

    this.simpleImage = new SimpleImage(image);

    this.simpleImage2 = new SimpleImage(image2);

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

    assertEquals(116, this.simpleImage.getPixelAt(0, 0).getRed());
    assertEquals(116, this.simpleImage.getPixelAt(0, 0).getGreen());
    assertEquals(116, this.simpleImage.getPixelAt(0, 0).getBlue());

    assertEquals(64, this.simpleImage.getPixelAt(0, 1).getRed());
    assertEquals(64, this.simpleImage.getPixelAt(0, 1).getGreen());
    assertEquals(64, this.simpleImage.getPixelAt(0, 1).getBlue());

    assertEquals(158, this.simpleImage.getPixelAt(0, 2).getRed());
    assertEquals(158, this.simpleImage.getPixelAt(0, 2).getGreen());
    assertEquals(158, this.simpleImage.getPixelAt(0, 2).getBlue());

    assertEquals(32, this.simpleImage.getPixelAt(1, 0).getRed());
    assertEquals(32, this.simpleImage.getPixelAt(1, 0).getGreen());
    assertEquals(32, this.simpleImage.getPixelAt(1, 0).getBlue());

    assertEquals(114, this.simpleImage.getPixelAt(2, 0).getRed());
    assertEquals(114, this.simpleImage.getPixelAt(2, 0).getGreen());
    assertEquals(114, this.simpleImage.getPixelAt(2, 0).getBlue());

    assertEquals(79, this.simpleImage.getPixelAt(1, 1).getRed());
    assertEquals(79, this.simpleImage.getPixelAt(1, 1).getGreen());
    assertEquals(79, this.simpleImage.getPixelAt(1, 1).getBlue());

    assertEquals(58, this.simpleImage.getPixelAt(1, 2).getRed());
    assertEquals(58, this.simpleImage.getPixelAt(1, 2).getGreen());
    assertEquals(58, this.simpleImage.getPixelAt(1, 2).getBlue());

    assertEquals(208, this.simpleImage.getPixelAt(2, 1).getRed());
    assertEquals(208, this.simpleImage.getPixelAt(2, 1).getGreen());
    assertEquals(208, this.simpleImage.getPixelAt(2, 1).getBlue());

    assertEquals(166, this.simpleImage.getPixelAt(2, 2).getRed());
    assertEquals(166, this.simpleImage.getPixelAt(2, 2).getGreen());
    assertEquals(166, this.simpleImage.getPixelAt(2, 2).getBlue());
  }

  @Test
  public void testSepiaImageOp() {
    this.simpleImage.accept(new SepiaImageOperation());

    assertEquals(163, this.simpleImage.getPixelAt(0, 0).getRed());
    assertEquals(145, this.simpleImage.getPixelAt(0, 0).getGreen());
    assertEquals(113, this.simpleImage.getPixelAt(0, 0).getBlue());

    assertEquals(107, this.simpleImage.getPixelAt(0, 1).getRed());
    assertEquals(95, this.simpleImage.getPixelAt(0, 1).getGreen());
    assertEquals(74, this.simpleImage.getPixelAt(0, 1).getBlue());

    assertEquals(188, this.simpleImage.getPixelAt(0, 2).getRed());
    assertEquals(167, this.simpleImage.getPixelAt(0, 2).getGreen());
    assertEquals(130, this.simpleImage.getPixelAt(0, 2).getBlue());

    assertEquals(53, this.simpleImage.getPixelAt(1, 0).getRed());
    assertEquals(47, this.simpleImage.getPixelAt(1, 0).getGreen());
    assertEquals(37, this.simpleImage.getPixelAt(1, 0).getBlue());

    assertEquals(175, this.simpleImage.getPixelAt(2, 0).getRed());
    assertEquals(156, this.simpleImage.getPixelAt(2, 0).getGreen());
    assertEquals(121, this.simpleImage.getPixelAt(2, 0).getBlue());

    assertEquals(94, this.simpleImage.getPixelAt(1, 1).getRed());
    assertEquals(83, this.simpleImage.getPixelAt(1, 1).getGreen());
    assertEquals(65, this.simpleImage.getPixelAt(1, 1).getBlue());

    assertEquals(81, this.simpleImage.getPixelAt(1, 2).getRed());
    assertEquals(72, this.simpleImage.getPixelAt(1, 2).getGreen());
    assertEquals(56, this.simpleImage.getPixelAt(1, 2).getBlue());

    assertEquals(255, this.simpleImage.getPixelAt(2, 1).getRed());
    assertEquals(228, this.simpleImage.getPixelAt(2, 1).getGreen());
    assertEquals(177, this.simpleImage.getPixelAt(2, 1).getBlue());

    assertEquals(230, this.simpleImage.getPixelAt(2, 2).getRed());
    assertEquals(205, this.simpleImage.getPixelAt(2, 2).getGreen());
    assertEquals(159, this.simpleImage.getPixelAt(2, 2).getBlue());


  }

  @Test
  public void testSharpenImageOp() {
    this.simpleImage2.accept(new SharpenImageOperation());

    assertEquals(236, this.simpleImage2.getPixelAt(0, 0).getRed());
    assertEquals(57, this.simpleImage2.getPixelAt(0,0).getGreen());
    assertEquals(102, this.simpleImage2.getPixelAt(0, 0).getBlue());

    assertEquals(161, this.simpleImage2.getPixelAt(0, 1).getRed());
    assertEquals(154, this.simpleImage2.getPixelAt(0,1).getGreen());
    assertEquals(255, this.simpleImage2.getPixelAt(0, 1).getBlue());

    assertEquals(99, this.simpleImage2.getPixelAt(0, 2).getRed());
    assertEquals(246, this.simpleImage2.getPixelAt(0,2).getGreen());
    assertEquals(223, this.simpleImage2.getPixelAt(0, 2).getBlue());

    assertEquals(96, this.simpleImage2.getPixelAt(0, 3).getRed());
    assertEquals(157, this.simpleImage2.getPixelAt(0,3).getGreen());
    assertEquals(124, this.simpleImage2.getPixelAt(0, 3).getBlue());

    assertEquals(66, this.simpleImage2.getPixelAt(0, 4).getRed());
    assertEquals(76, this.simpleImage2.getPixelAt(0,4).getGreen());
    assertEquals(58, this.simpleImage2.getPixelAt(0, 4).getBlue());

    assertEquals(193, this.simpleImage2.getPixelAt(1, 0).getRed());
    assertEquals(48, this.simpleImage2.getPixelAt(1,0).getGreen());
    assertEquals(111, this.simpleImage2.getPixelAt(1, 0).getBlue());

    assertEquals(113, this.simpleImage2.getPixelAt(2, 0).getRed());
    assertEquals(0, this.simpleImage2.getPixelAt(2,0).getGreen());
    assertEquals(122, this.simpleImage2.getPixelAt(2, 0).getBlue());

    assertEquals(200, this.simpleImage2.getPixelAt(3, 0).getRed());
    assertEquals(0, this.simpleImage2.getPixelAt(3,0).getGreen());
    assertEquals(138, this.simpleImage2.getPixelAt(3, 0).getBlue());

    assertEquals(89, this.simpleImage2.getPixelAt(4, 0).getRed());
    assertEquals(0, this.simpleImage2.getPixelAt(4,0).getGreen());
    assertEquals(112, this.simpleImage2.getPixelAt(4, 0).getBlue());

    assertEquals(255, this.simpleImage2.getPixelAt(1, 1).getRed());
    assertEquals(251, this.simpleImage2.getPixelAt(1,1).getGreen());
    assertEquals(255, this.simpleImage2.getPixelAt(1, 1).getBlue());

    assertEquals(205, this.simpleImage2.getPixelAt(1, 2).getRed());
    assertEquals(255, this.simpleImage2.getPixelAt(1,2).getGreen());
    assertEquals(255, this.simpleImage2.getPixelAt(1, 2).getBlue());

    assertEquals(255, this.simpleImage2.getPixelAt(1, 3).getRed());
    assertEquals(255, this.simpleImage2.getPixelAt(1,3).getGreen());
    assertEquals(169, this.simpleImage2.getPixelAt(1, 3).getBlue());

    assertEquals(141, this.simpleImage2.getPixelAt(1, 4).getRed());
    assertEquals(79, this.simpleImage2.getPixelAt(1,4).getGreen());
    assertEquals(150, this.simpleImage2.getPixelAt(1, 4).getBlue());

    assertEquals(118, this.simpleImage2.getPixelAt(2, 1).getRed());
    assertEquals(160, this.simpleImage2.getPixelAt(2,1).getGreen());
    assertEquals(120, this.simpleImage2.getPixelAt(2, 1).getBlue());

    assertEquals(95, this.simpleImage2.getPixelAt(2, 2).getRed());
    assertEquals(146, this.simpleImage2.getPixelAt(2,2).getGreen());
    assertEquals(117, this.simpleImage2.getPixelAt(2, 2).getBlue());

    assertEquals(127, this.simpleImage2.getPixelAt(2, 3).getRed());
    assertEquals(159, this.simpleImage2.getPixelAt(2,3).getGreen());
    assertEquals(71, this.simpleImage2.getPixelAt(2, 3).getBlue());

    assertEquals(47, this.simpleImage2.getPixelAt(2, 4).getRed());
    assertEquals(58, this.simpleImage2.getPixelAt(2,4).getGreen());
    assertEquals(103, this.simpleImage2.getPixelAt(2, 4).getBlue());

    assertEquals(92, this.simpleImage2.getPixelAt(3, 1).getRed());
    assertEquals(185, this.simpleImage2.getPixelAt(3,1).getGreen());
    assertEquals(236, this.simpleImage2.getPixelAt(3, 1).getBlue());

    assertEquals(87, this.simpleImage2.getPixelAt(3, 2).getRed());
    assertEquals(255, this.simpleImage2.getPixelAt(3,2).getGreen());
    assertEquals(133, this.simpleImage2.getPixelAt(3, 2).getBlue());

    assertEquals(134, this.simpleImage2.getPixelAt(3, 3).getRed());
    assertEquals(120, this.simpleImage2.getPixelAt(3,3).getGreen());
    assertEquals(70, this.simpleImage2.getPixelAt(3, 3).getBlue());

    assertEquals(51, this.simpleImage2.getPixelAt(3, 4).getRed());
    assertEquals(17, this.simpleImage2.getPixelAt(3,4).getGreen());
    assertEquals(79, this.simpleImage2.getPixelAt(3, 4).getBlue());

    assertEquals(101, this.simpleImage2.getPixelAt(4, 1).getRed());
    assertEquals(240, this.simpleImage2.getPixelAt(4,1).getGreen());
    assertEquals(254, this.simpleImage2.getPixelAt(4, 1).getBlue());

    assertEquals(146, this.simpleImage2.getPixelAt(4, 2).getRed());
    assertEquals(255, this.simpleImage2.getPixelAt(4,2).getGreen());
    assertEquals(165, this.simpleImage2.getPixelAt(4, 2).getBlue());

    assertEquals(255, this.simpleImage2.getPixelAt(4, 3).getRed());
    assertEquals(126, this.simpleImage2.getPixelAt(4,3).getGreen());
    assertEquals(106, this.simpleImage2.getPixelAt(4, 3).getBlue());

    assertEquals(112, this.simpleImage2.getPixelAt(4, 4).getRed());
    assertEquals(0, this.simpleImage2.getPixelAt(4,4).getGreen());
    assertEquals(41, this.simpleImage2.getPixelAt(4, 4).getBlue());

  }

}
