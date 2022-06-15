package model.image;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import model.image.operations.BlueGreyscaleImageOperation;
import model.image.operations.BrightenImageOperation;
import model.image.operations.DarkenImageOperation;
import model.image.operations.FlipHorizontallyImageOperation;
import model.image.operations.FlipVerticallyImageOperation;
import model.image.operations.GreenGreyscaleImageOperation;
import model.image.operations.IntensityGreyscaleImageOperation;
import model.image.operations.LumaGreyscaleImageOperation;
import model.image.operations.RedGreyscaleImageOperation;
import model.image.operations.ValueGreyscaleImageOperation;
import model.pixel.TransparentPixel;
import model.pixel.RGBAPixel;


import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the MyImage interface.
 */
public class MyImageTest {
  private int[] components5;
  private int[] components6;
  private int[] components7;
  private int[] components8;
  private TransparentPixel pixel1;
  private TransparentPixel pixel2;
  private TransparentPixel pixel3;
  private TransparentPixel pixel4;

  private TransparentPixel pixel5;
  private TransparentPixel pixel6;
  private TransparentPixel pixel7;
  private TransparentPixel pixel8;
  private MyImage simpleImage1;
  private ArrayList<TransparentPixel> subImage;
  private ArrayList<ArrayList<TransparentPixel>> image;

  private MyImage simpleImage2;
  private ArrayList<TransparentPixel> subImage2;
  private ArrayList<TransparentPixel> subImage3;
  private ArrayList<ArrayList<TransparentPixel>> image2;

  @Before
  public void init() {
    int[] components1;
    int[] components2;
    int[] components3;
    int[] components4;
    
    components1 = new int[3];
    components1[0] = 200;
    components1[1] = 100;
    components1[2] = 40;

    this.pixel1 = new RGBAPixel(components1[0], components1[1], components1[2]);

    components2 = new int[3];
    components2[0] = 100;
    components2[1] = 40;
    components2[2] = 200;

    this.pixel2 = new RGBAPixel(components2[0], components2[1], components2[2]);

    components3 = new int[3];
    components3[0] = 40;
    components3[1] = 200;
    components3[2] = 100;

    this.pixel3 = new RGBAPixel(components3[0], components3[1], components3[2]);

    components4 = new int[3];
    components4[0] = 3;
    components4[1] = 29;
    components4[2] = 44;

    this.pixel4 = new RGBAPixel(components4[0], components4[1], components4[2]);

    this.subImage = new ArrayList<TransparentPixel>();

    subImage.add(this.pixel1);
    subImage.add(this.pixel2);
    ArrayList<TransparentPixel> secondRow = new ArrayList<>();
    secondRow.add(this.pixel3);
    secondRow.add(this.pixel4);

    this.image = new ArrayList<ArrayList<TransparentPixel>>();

    image.add(subImage);
    image.add(secondRow);

    this.simpleImage1 = new SimpleImage(image);

    this.components5 = new int[3];
    components5[0] = 30;
    components5[1] = 2;
    components5[2] = 40;

    this.pixel5 = new RGBAPixel(components5[0], components5[1], components5[2]);

    this.components6 = new int[3];
    components6[0] = 90;
    components6[1] = 250;
    components6[2] = 167;

    this.pixel6 = new RGBAPixel(components6[0], components6[1], components6[2]);

    this.components7 = new int[3];
    components7[0] = 214;
    components7[1] = 99;
    components7[2] = 86;

    this.pixel7 = new RGBAPixel(components7[0], components7[1], components7[2]);

    this.components8 = new int[3];
    components8[0] = 9;
    components8[1] = 54;
    components8[2] = 113;

    this.pixel8 = new RGBAPixel(components8[0], components8[1], components8[2]);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNullImage() {

    this.image = null;

    this.simpleImage1 = new SimpleImage(image);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNoHeight() {

    this.image = new ArrayList<ArrayList<TransparentPixel>>();

    this.simpleImage1 = new SimpleImage(image);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationNoWidth() {

    this.subImage = new ArrayList<TransparentPixel>();

    this.image = new ArrayList<ArrayList<TransparentPixel>>();

    image.add(subImage);

    this.simpleImage1 = new SimpleImage(image);

  }

  // helper to check if the images are the same after an operation has been completed
  private void testSameAfterOp(MyImage image1, MyImage image2) {
    for (int i = 0; i < image1.getHeight(); i++) {
      for (int j = 0; j < image1.getWidth(); j++) {
        assertEquals(image2.getPixelAt(i, j).getRed(), image1.getPixelAt(i, j).getRed());
        assertEquals(image2.getPixelAt(i, j).getGreen(), image1.getPixelAt(i, j).getGreen());
        assertEquals(image2.getPixelAt(i, j).getBlue(), image1.getPixelAt(i, j).getBlue());
      }
    }
  }

  @Test
  public void testAcceptBlueGreyscaleImageOp() {

    this.components5 = new int[3];
    components5[0] = 40;
    components5[1] = 40;
    components5[2] = 40;

    this.pixel5 = new RGBAPixel(components5[0], components5[1], components5[2]);

    this.components6 = new int[3];
    components6[0] = 200;
    components6[1] = 200;
    components6[2] = 200;

    this.pixel6 = new RGBAPixel(components6[0], components6[1], components6[2]);

    this.components7 = new int[3];
    components7[0] = 100;
    components7[1] = 100;
    components7[2] = 100;

    this.pixel7 = new RGBAPixel(components7[0], components7[1], components7[2]);

    this.components8 = new int[3];
    components8[0] = 44;
    components8[1] = 44;
    components8[2] = 44;

    this.pixel8 = new RGBAPixel(components8[0], components8[1], components8[2]);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new BlueGreyscaleImageOperation());

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);
  }

  @Test
  public void testAcceptRedGreyscaleImageOp() {

    this.components5 = new int[]{200, 200, 200};

    this.pixel5 = new RGBAPixel(components5[0], components5[1], components5[2]);

    this.components6 = new int[]{100, 100, 100};

    this.pixel6 = new RGBAPixel(components6[0], components6[1], components6[2]);

    this.components7 = new int[]{40, 40, 40};

    this.pixel7 = new RGBAPixel(components7[0], components7[1], components7[2]);

    this.components8 = new int[]{3, 3, 3};

    this.pixel8 = new RGBAPixel(components8[0], components8[1], components8[2]);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new RedGreyscaleImageOperation());

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);
  }

  @Test
  public void testAcceptGreenGreyscaleImageOp() {

    this.components5 = new int[]{100, 100, 100};

    this.pixel5 = new RGBAPixel(components5[0], components5[1], components5[2]);

    this.components6 = new int[]{40, 40, 40};

    this.pixel6 = new RGBAPixel(components6[0], components6[1], components6[2]);

    this.components7 = new int[]{200, 200, 200};

    this.pixel7 = new RGBAPixel(components7[0], components7[1], components7[2]);

    this.components8 = new int[]{29, 29, 29};

    this.pixel8 = new RGBAPixel(components8[0], components8[1], components8[2]);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new GreenGreyscaleImageOperation());

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);
  }

  @Test
  public void testAcceptIntensityGreyscaleImageOp() {

    this.components5 = new int[]{113, 113, 113};

    this.pixel5 = new RGBAPixel(components5[0], components5[1], components5[2]);

    this.components6 = new int[]{113, 113, 113};

    this.pixel6 = new RGBAPixel(components6[0], components6[1], components6[2]);

    int[] components7 = new int[]{113, 113, 113};

    this.pixel7 = new RGBAPixel(components7[0], components7[1], components7[2]);

    int[] components8 = new int[]{25, 25, 25};

    this.pixel8 = new RGBAPixel(components8[0], components8[1], components8[2]);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new IntensityGreyscaleImageOperation());

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);
  }

  @Test
  public void testAcceptLumaGreyscaleImageOp() {

    this.components5 = new int[]{116, 116, 116};

    this.pixel5 = new RGBAPixel(components5[0], components5[1], components5[2]);

    this.components6 = new int[]{64, 64, 64};

    this.pixel6 = new RGBAPixel(components6[0], components6[1], components6[2]);

    this.components7 = new int[]{158, 158, 158};

    this.pixel7 = new RGBAPixel(components7[0], components7[1], components7[2]);

    this.components8 = new int[]{24, 24, 24};

    this.pixel8 = new RGBAPixel(components8[0], components8[1], components8[2]);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new LumaGreyscaleImageOperation());

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);

  }

  @Test
  public void testAcceptValueGreyscaleImageOp() {

    this.components5 = new int[]{200, 200, 200};

    this.pixel5 = new RGBAPixel(components5[0], components5[1], components5[2]);

    this.components6 = new int[]{200, 200, 200};

    this.pixel6 = new RGBAPixel(components6[0], components6[1], components6[2]);

    this.components7 = new int[]{200, 200, 200};

    this.pixel7 = new RGBAPixel(components7[0], components7[1], components7[2]);

    this.components8 = new int[]{44, 44, 44};

    this.pixel8 = new RGBAPixel(components8[0], components8[1], components8[2]);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new ValueGreyscaleImageOperation());

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);

  }

  @Test
  public void testAcceptBrightenImageOp() {

    this.components5 = new int[]{210, 110, 50};

    this.pixel5 = new RGBAPixel(components5[0], components5[1], components5[2]);

    this.components6 = new int[]{110, 50, 210};

    this.pixel6 = new RGBAPixel(components6[0], components6[1], components6[2]);

    this.components7 = new int[]{50, 210, 110};

    this.pixel7 = new RGBAPixel(components7[0], components7[1], components7[2]);

    this.components8 = new int[]{13, 39, 54};

    this.pixel8 = new RGBAPixel(components8[0], components8[1], components8[2]);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new BrightenImageOperation(10));

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);

  }

  @Test
  public void testAcceptBrightenImageOpClampToMax() {

    this.pixel5 = new RGBAPixel(255, 255, 255);

    this.pixel6 = new RGBAPixel(255, 255, 255);

    this.pixel7 = new RGBAPixel(255, 255, 255);

    this.pixel8 = new RGBAPixel(255, 255, 255);
    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new BrightenImageOperation(500));

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);

  }

  @Test
  public void testAcceptDarkenImageOp() {

    this.pixel5 = new RGBAPixel(190, 90, 30);

    this.pixel6 = new RGBAPixel(90, 30, 190);

    this.pixel7 = new RGBAPixel(30, 190, 90);

    this.pixel8 = new RGBAPixel(0, 19, 34);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new DarkenImageOperation(10));

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);

  }

  @Test
  public void testAcceptDarkenImageClampToMin() {

    this.pixel5 = new RGBAPixel(0, 0, 0);

    this.pixel6 = new RGBAPixel(0, 0, 0);

    this.pixel7 = new RGBAPixel(0, 0, 0);

    this.pixel8 = new RGBAPixel(0, 0, 0);

    this.subImage2 = new ArrayList<TransparentPixel>();

    subImage2.add(this.pixel5);
    subImage2.add(this.pixel6);

    this.subImage3 = new ArrayList<TransparentPixel>();

    subImage3.add(this.pixel7);
    subImage3.add(this.pixel8);

    this.image2 = new ArrayList<ArrayList<TransparentPixel>>();

    image2.add(subImage2);
    image2.add(subImage3);

    this.simpleImage2 = new SimpleImage(image2);

    this.simpleImage1.accept(new DarkenImageOperation(500));

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage2);

  }

  @Test
  public void testAcceptFlipHorizontallySquare() {

    ArrayList<TransparentPixel> subImage4 = new ArrayList<TransparentPixel>();

    subImage4.add(this.pixel2);
    subImage4.add(this.pixel1);

    ArrayList<TransparentPixel> subImage5 = new ArrayList<TransparentPixel>();

    subImage5.add(this.pixel4);
    subImage5.add(this.pixel3);

    ArrayList<ArrayList<TransparentPixel>> image3 = new ArrayList<ArrayList<TransparentPixel>>();

    image3.add(subImage4);
    image3.add(subImage5);

    MyImage simpleImage3 = new SimpleImage(image3);

    this.simpleImage1.accept(new FlipHorizontallyImageOperation());

    assertEquals(2, simpleImage2.getHeight());
    testSameAfterOp(simpleImage1, simpleImage3);
  }

  @Test
  public void testAcceptFlipHorizontallyRectangle() {

    //---------Below is the original image

    ArrayList<TransparentPixel> subImage4 = new ArrayList<TransparentPixel>();

    subImage4.add(this.pixel1);
    subImage4.add(this.pixel2);
    subImage4.add(this.pixel3);
    subImage4.add(this.pixel4);

    ArrayList<TransparentPixel> subImage5 = new ArrayList<TransparentPixel>();

    subImage5.add(this.pixel5);
    subImage5.add(this.pixel6);
    subImage5.add(this.pixel7);
    subImage5.add(this.pixel8);

    ArrayList<ArrayList<TransparentPixel>> image3 = new ArrayList<ArrayList<TransparentPixel>>();

    image3.add(subImage4);
    image3.add(subImage5);

    MyImage simpleImage3 = new SimpleImage(image3);

    //---------Below is the flipped image

    ArrayList<TransparentPixel> subImage6 = new ArrayList<TransparentPixel>();

    subImage6.add(this.pixel5);
    subImage6.add(this.pixel6);
    subImage6.add(this.pixel7);
    subImage6.add(this.pixel8);

    ArrayList<TransparentPixel> subImage7 = new ArrayList<TransparentPixel>();

    subImage7.add(this.pixel1);
    subImage7.add(this.pixel2);
    subImage7.add(this.pixel3);
    subImage7.add(this.pixel4);

    ArrayList<ArrayList<TransparentPixel>> image4 = new ArrayList<ArrayList<TransparentPixel>>();

    image4.add(subImage4);
    image4.add(subImage5);

    MyImage simpleImage4 = new SimpleImage(image4);

    simpleImage3.accept(new FlipHorizontallyImageOperation());

    assertEquals(2, simpleImage4.getHeight());
    testSameAfterOp(simpleImage4, simpleImage3);

  }

  @Test
  public void testAcceptFlipVerticallySquare() {

    ArrayList<TransparentPixel> subImage4 = new ArrayList<TransparentPixel>();

    subImage4.add(this.pixel3);
    subImage4.add(this.pixel4);

    ArrayList<TransparentPixel> subImage5 = new ArrayList<TransparentPixel>();

    subImage5.add(this.pixel1);
    subImage5.add(this.pixel2);

    ArrayList<ArrayList<TransparentPixel>> image3 = new ArrayList<ArrayList<TransparentPixel>>();

    image3.add(subImage4);
    image3.add(subImage5);

    MyImage simpleImage3 = new SimpleImage(image3);

    this.simpleImage1.accept(new FlipVerticallyImageOperation());

    assertEquals(2, simpleImage3.getHeight());
    testSameAfterOp(simpleImage1, simpleImage3);

  }

  @Test
  public void testAcceptFlipVerticallyRectangle() {

    //---------Below is the original image

    ArrayList<TransparentPixel> subImage4 = new ArrayList<TransparentPixel>();

    subImage4.add(this.pixel1);
    subImage4.add(this.pixel2);
    subImage4.add(this.pixel3);
    subImage4.add(this.pixel4);

    ArrayList<TransparentPixel> subImage5 = new ArrayList<TransparentPixel>();

    subImage5.add(this.pixel5);
    subImage5.add(this.pixel6);
    subImage5.add(this.pixel7);
    subImage5.add(this.pixel8);

    ArrayList<ArrayList<TransparentPixel>> image3 = new ArrayList<ArrayList<TransparentPixel>>();

    image3.add(subImage4);
    image3.add(subImage5);

    MyImage simpleImage3 = new SimpleImage(image3);

    //---------Below is the flipped image

    ArrayList<TransparentPixel> subImage6 = new ArrayList<TransparentPixel>();

    subImage6.add(this.pixel4);
    subImage6.add(this.pixel3);
    subImage6.add(this.pixel2);
    subImage6.add(this.pixel1);

    ArrayList<TransparentPixel> subImage7 = new ArrayList<TransparentPixel>();

    subImage7.add(this.pixel8);
    subImage7.add(this.pixel7);
    subImage7.add(this.pixel6);
    subImage7.add(this.pixel5);

    ArrayList<ArrayList<TransparentPixel>> image4 = new ArrayList<ArrayList<TransparentPixel>>();

    image4.add(subImage4);
    image4.add(subImage5);

    MyImage simpleImage4 = new SimpleImage(image4);

    this.simpleImage1.accept(new FlipVerticallyImageOperation());

    assertEquals(2, simpleImage4.getHeight());
    testSameAfterOp(simpleImage3, simpleImage4);

  }


  @Test
  public void testGetHeight() {
    assertEquals(2, this.simpleImage1.getHeight());
  }

  @Test
  public void testGetWidth() {
    assertEquals(2, this.simpleImage1.getWidth());
  }

  @Test
  public void testGetTransparentPixelAt1() {
    assertEquals(this.pixel1.getRed(),
            this.simpleImage1.getPixelAt(0, 0).getRed());
    assertEquals(this.pixel1.getGreen(),
            this.simpleImage1.getPixelAt(0, 0).getGreen());
    assertEquals(this.pixel1.getBlue(),
            this.simpleImage1.getPixelAt(0, 0).getBlue());
  }

  @Test
  public void testGetTransparentPixelAt2() {
    assertEquals(this.pixel2.getRed(),
            this.simpleImage1.getPixelAt(0, 1).getRed());
    assertEquals(this.pixel2.getGreen(),
            this.simpleImage1.getPixelAt(0, 1).getGreen());
    assertEquals(this.pixel2.getBlue(),
            this.simpleImage1.getPixelAt(0, 1).getBlue());
  }

  @Test
  public void testGetTransparentPixelAt3() {
    assertEquals(this.pixel3.getRed(),
            this.simpleImage1.getPixelAt(1, 0).getRed());
    assertEquals(this.pixel3.getGreen(),
            this.simpleImage1.getPixelAt(1, 0).getGreen());
    assertEquals(this.pixel3.getBlue(),
            this.simpleImage1.getPixelAt(1, 0).getBlue());
  }

  @Test
  public void testGetTransparentPixelAt4() {
    assertEquals(this.pixel4.getRed(),
            this.simpleImage1.getPixelAt(1, 1).getRed());
    assertEquals(this.pixel4.getGreen(),
            this.simpleImage1.getPixelAt(1, 1).getGreen());
    assertEquals(this.pixel4.getBlue(),
            this.simpleImage1.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testFlipHorizontally() {
    ArrayList<TransparentPixel> subImage4 = new ArrayList<TransparentPixel>();

    subImage4.add(this.pixel2);
    subImage4.add(this.pixel1);

    ArrayList<TransparentPixel> subImage5 = new ArrayList<TransparentPixel>();

    subImage5.add(this.pixel4);
    subImage5.add(this.pixel3);

    ArrayList<ArrayList<TransparentPixel>> image3 = new ArrayList<ArrayList<TransparentPixel>>();

    image3.add(subImage4);
    image3.add(subImage5);

    MyImage simpleImage3 = new SimpleImage(image3);

    this.simpleImage1.flipHorizontally();

    assertEquals(2, simpleImage3.getHeight());
    testSameAfterOp(simpleImage1, simpleImage3);

  }

  @Test
  public void testFlipVertically() {
    ArrayList<TransparentPixel> subImage4 = new ArrayList<TransparentPixel>();

    subImage4.add(this.pixel3);
    subImage4.add(this.pixel4);

    ArrayList<TransparentPixel> subImage5 = new ArrayList<TransparentPixel>();

    subImage5.add(this.pixel1);
    subImage5.add(this.pixel2);

    ArrayList<ArrayList<TransparentPixel>> image3 = new ArrayList<ArrayList<TransparentPixel>>();

    image3.add(subImage4);
    image3.add(subImage5);

    MyImage simpleImage3 = new SimpleImage(image3);

    this.simpleImage1.flipVertically();

    assertEquals(2, simpleImage3.getHeight());
    testSameAfterOp(simpleImage1, simpleImage3);

  }

  @Test
  public void testGetCopy() {
    int[] components1 = new int[3];
    components1[0] = 200;
    components1[1] = 100;
    components1[2] = 40;

    TransparentPixel pixel9 = new RGBAPixel(200, 100, 40);

    int[] components2 = new int[3];
    components2[0] = 100;
    components2[1] = 40;
    components2[2] = 200;

    TransparentPixel pixel10 = new RGBAPixel(components2[0], components2[1], components2[2]);

    int[] components3 = new int[3];
    components3[0] = 40;
    components3[1] = 200;
    components3[2] = 100;

    TransparentPixel pixel11 = new RGBAPixel(components3[0], components3[1], components3[2]);

    int[] components4 = new int[3];
    components4[0] = 3;
    components4[1] = 29;
    components4[2] = 44;

    TransparentPixel pixel12 = new RGBAPixel(components4[0], components4[1], components4[2]);

    ArrayList<TransparentPixel> subImage = new ArrayList<TransparentPixel>();

    subImage.add(pixel9);
    subImage.add(pixel10);

    ArrayList<TransparentPixel> subImage2 = new ArrayList<TransparentPixel>();
    subImage2.add(pixel11);
    subImage2.add(pixel12);

    ArrayList<ArrayList<TransparentPixel>> image = new ArrayList<ArrayList<TransparentPixel>>();

    image.add(subImage);
    image.add(subImage2);

    MyImage simpleImage3 = new SimpleImage(image);

    assertEquals(2, simpleImage3.getHeight());
    testSameAfterOp(simpleImage3, simpleImage1.getCopy());
  }

}