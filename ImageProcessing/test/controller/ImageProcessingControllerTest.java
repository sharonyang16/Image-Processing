package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.image.MyImage;
import model.pixel.Pixel;
import view.ImageProcessingTextView;
import view.ImageProcessingView;
import view.MockAppendable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for the ImageProcessingController interface.
 */
public class ImageProcessingControllerTest {
  private ImageProcessingController controller;
  private ImageProcessingView view;
  private ImageProcessingModel model;
  private Readable in;
  private Appendable out;

  // the initial conditions for the ImageProcessingController and it's parts
  @Before
  public void init() {
    out = new StringBuffer();
    in = new StringReader("");
    model = new ImageProcessingModelImpl();
    view = new ImageProcessingTextView(out);
    controller = new ImageProcessingControllerImpl(model, view, in);
  }

  // to test creating a valid ImageProcessingControllerImpl object
  @Test
  public void testCreateValid() {
    try {
      controller = new ImageProcessingControllerImpl(model, view, in);
      controller.execute();
    }
    catch (IllegalArgumentException e) {
      fail("Exception was thrown");
    }
  }

  // to test creating an invalid ImageProcessingControllerImpl object due to the
  // model being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateNullModel() {
    new ImageProcessingControllerImpl(null, view, in);
  }

  // to test creating an invalid ImageProcessingControllerImpl object due to the
  // view being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateNullView() {
    new ImageProcessingControllerImpl(model, null, in);
  }

  // to test creating an invalid ImageProcessingControllerImpl object due to the
  // Readable object being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateNullReadable() {
    new ImageProcessingControllerImpl(model, view, null);
  }

  // helper for tests that need set an input and then execute
  private void setInputs(String input) {
    in = new StringReader(input);

    controller = new ImageProcessingControllerImpl(model, view, in);

    controller.execute();
  }

  @Test
  public void testExecuteNoInputs() {
    setInputs("");

    String outString = out.toString();

    // the menu always gets printed
    assertTrue(outString.contains("Menu"));
    // this message is only printed if there's an input of a quit, this method quit
    // due to running out of inputs/having no inputs
    assertFalse(outString.contains("Thanks for using this program! Goodbye!"));
  }

  @Test
  public void testExecuteQuitImmediately() {
    setInputs("qUiT");

    String outString = out.toString();

    // the menu always gets printed
    assertTrue(outString.contains("Menu"));
    // when there's a quit, this message should be printed
    assertTrue(outString.contains("Thanks for using this program! Goodbye!"));
  }

  @Test
  public void testExecuteLoadPPM() {
    setInputs("load res/Landscape.ppm landscape");

    String outString = out.toString();

    // should print when there's a command is completed successfully
    assertTrue(outString.contains("Command successful!"));
    assertTrue(outString.contains("(used load on image)"));

    // the model should be able to return an image with the given name now
    try {
      model.getImageNamed("landscape");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testExecuteLoadJPG() {
    setInputs("load res/Landscape.jpg landscape");

    String outString = out.toString();

    // should print when there's a command is completed successfully
    assertTrue(outString.contains("Command successful!"));
    assertTrue(outString.contains("(used load on image)"));

    // the model should be able to return an image with the given name now
    try {
      model.getImageNamed("landscape");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testExecuteLoadPNG() {
    setInputs("load res/Landscape.png landscape");

    String outString = out.toString();

    // should print when there's a command is completed successfully
    assertTrue(outString.contains("Command successful!"));
    assertTrue(outString.contains("(used load on image)"));

    // the model should be able to return an image with the given name now
    try {
      model.getImageNamed("landscape");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testExecuteLoadBMP() {
    setInputs("load res/Landscape.bmp landscape");

    String outString = out.toString();

    // should print when there's a command is completed successfully
    assertTrue(outString.contains("Command successful!"));
    assertTrue(outString.contains("(used load on image)"));

    // the model should be able to return an image with the given name now
    try {
      model.getImageNamed("landscape");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  public void testExecuteLoadInvalid() {
    setInputs("load res/Landscape.txt landscape");

    String outString = out.toString();

    // renders this message as it's provided a txt file
    assertTrue(outString.contains("Command unsuccessful! Unsupported file type!"));

    setInputs("load abc.ppm landscape");

    outString = out.toString();

    // renders this message as it's an invalid file path
    assertTrue(outString.contains("Command unsuccessful! Could not find file; invalid file path."));
  }

  @Test
  public void testExecuteGreyscale() {
    setInputs("load res/Landscape.ppm landscape red-greyscale landscape red "
            + "green-greyscale landscape green blue-greyscale landscape blue "
            + "value-greyscale landscape value intensity-greyscale landscape intensity "
            + "luma-greyscale landscape luma "
            + "greyscale landscape greyscale ");

    String outString = out.toString();

    // should be false as all the inputs were valid
    assertFalse(outString.contains("Command unsuccessful!"));

    assertTrue(outString.contains("(used load on image)"));
    assertTrue(outString.contains("(used red-greyscale on image)"));
    assertTrue(outString.contains("(used green-greyscale on image)"));
    assertTrue(outString.contains("(used blue-greyscale on image)"));
    assertTrue(outString.contains("(used value-greyscale on image)"));
    assertTrue(outString.contains("(used intensity-greyscale on image)"));
    assertTrue(outString.contains("(used luma-greyscale on image)"));
    assertTrue(outString.contains("(used greyscale on image)"));


    // the model should be able to return these images since it was loaded properly
    try {
      model.getImageNamed("landscape");
      model.getImageNamed("red");
      model.getImageNamed("blue");
      model.getImageNamed("green");
      model.getImageNamed("value");
      model.getImageNamed("intensity");
      model.getImageNamed("luma");
      model.getImageNamed("greyscale");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }

    MyImage original = model.getImageNamed("landscape");
    MyImage redImage = model.getImageNamed("red");
    MyImage greenImage = model.getImageNamed("green");
    MyImage blueImage = model.getImageNamed("blue");
    MyImage valueImage = model.getImageNamed("value");
    MyImage intensityImage = model.getImageNamed("intensity");
    MyImage lumaImage = model.getImageNamed("luma");
    MyImage greyscaleImage = model.getImageNamed("greyscale");

    Pixel originalPixel;
    Pixel redImagePixel;
    Pixel greenImagePixel;
    Pixel blueImagePixel;
    Pixel valueImagePixel;
    Pixel intensityImagePixel;
    Pixel lumaImagePixel;
    Pixel greyscalePixel;

    int value;
    int intensity;
    int luma;

    // validates that the model properly modified the images it saved
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        originalPixel = original.getPixelAt(i, j);
        redImagePixel = redImage.getPixelAt(i, j);
        greenImagePixel = greenImage.getPixelAt(i, j);
        blueImagePixel = blueImage.getPixelAt(i, j);
        valueImagePixel = valueImage.getPixelAt(i, j);
        intensityImagePixel = intensityImage.getPixelAt(i, j);
        lumaImagePixel = lumaImage.getPixelAt(i, j);
        greyscalePixel = greyscaleImage.getPixelAt(i, j);

        value = Math.max(originalPixel.getGreen(),
                Math.max(originalPixel.getRed(), originalPixel.getBlue()));

        intensity = (originalPixel.getRed() + originalPixel.getGreen() + originalPixel.getBlue())
                / 3;

        luma = (int) ((0.2126 * originalPixel.getRed())
                + (0.7152 * originalPixel.getGreen())
                + (0.0722 * originalPixel.getBlue()));

        assertEquals(originalPixel.getRed(), redImagePixel.getBlue());
        assertEquals(originalPixel.getRed(), redImagePixel.getGreen());

        assertEquals(originalPixel.getGreen(), greenImagePixel.getBlue());
        assertEquals(originalPixel.getGreen(), greenImagePixel.getRed());

        assertEquals(originalPixel.getBlue(), blueImagePixel.getRed());
        assertEquals(originalPixel.getBlue(), blueImagePixel.getGreen());

        assertEquals(value, valueImagePixel.getRed());
        assertEquals(value, valueImagePixel.getGreen());
        assertEquals(value, valueImagePixel.getBlue());

        assertEquals(intensity, intensityImagePixel.getRed());
        assertEquals(intensity, intensityImagePixel.getGreen());
        assertEquals(intensity, intensityImagePixel.getBlue());

        assertEquals(luma, lumaImagePixel.getRed());
        assertEquals(luma, lumaImagePixel.getGreen());
        assertEquals(luma, lumaImagePixel.getBlue());

        assertEquals(luma, greyscalePixel.getRed());
        assertEquals(luma, greyscalePixel.getGreen());
        assertEquals(luma, greyscalePixel.getBlue());
      }
    }
  }

  @Test
  public void testExecuteSepia() {
    setInputs("load res/Landscape.ppm landscape sepia landscape sepia");

    String outString = out.toString();

    // should be false as all the inputs were valid
    assertFalse(outString.contains("Command unsuccessful!"));

    assertTrue(outString.contains("(used sepia on image)"));

    // the model should be able to return these images since it was loaded properly
    try {
      model.getImageNamed("landscape");
      model.getImageNamed("sepia");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }

    MyImage original = model.getImageNamed("landscape");
    MyImage sepiaImage = model.getImageNamed("sepia");

    Pixel originalPixel;
    Pixel sepiaPixel;

    int sepiaRed;
    int sepiaGreen;
    int sepiaBlue;

    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        originalPixel = original.getPixelAt(i, j);
        sepiaPixel = sepiaImage.getPixelAt(i, j);
        sepiaRed = clampHelper((int) ((0.393 * originalPixel.getRed())
                + (0.769 * originalPixel.getGreen())
                + (0.189 * originalPixel.getBlue())), 0);
        sepiaGreen = clampHelper((int) ((0.349 * originalPixel.getRed())
                + (0.686 * originalPixel.getGreen())
                + (0.168 * originalPixel.getBlue())), 0);
        sepiaBlue = clampHelper((int) ((0.272 * originalPixel.getRed())
                + (0.534 * originalPixel.getGreen())
                + (0.131 * originalPixel.getBlue())), 0);

        assertEquals(sepiaRed, sepiaPixel.getRed());
        assertEquals(sepiaGreen, sepiaPixel.getGreen());
        assertEquals(sepiaBlue, sepiaPixel.getBlue());
      }
    }
  }

  @Test
  public void testExecuteAdjustBright() {
    setInputs("load res/Landscape.ppm landscape brighten landscape brighter 10 "
            + "darken landscape darker 30");

    // the model should be able to return these images since it was loaded properly
    try {
      model.getImageNamed("landscape");
      model.getImageNamed("brighter");
      model.getImageNamed("darker");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }

    MyImage originalImage = model.getImageNamed("landscape");
    MyImage brighterImage = model.getImageNamed("brighter");
    MyImage darkerImage = model.getImageNamed("darker");

    Pixel originalImagePixel;
    Pixel brighterImagePixel;
    Pixel darkerImagePixel;

    int brightRed;
    int brightGreen;
    int brightBlue;

    int darkRed;
    int darkGreen;
    int darkBlue;

    // validates that the model properly modified the images it saved
    for (int i = 0; i < originalImage.getHeight(); i++) {
      for (int j = 0; j < originalImage.getWidth(); j++) {
        originalImagePixel = originalImage.getPixelAt(i, j);
        brighterImagePixel = brighterImage.getPixelAt(i, j);
        darkerImagePixel = darkerImage.getPixelAt(i, j);

        brightRed = clampHelper(originalImagePixel.getRed(), 10);
        brightGreen = clampHelper(originalImagePixel.getGreen(), 10);
        brightBlue = clampHelper(originalImagePixel.getBlue(), 10);

        darkRed = clampHelper(originalImagePixel.getRed(), -30);
        darkGreen = clampHelper(originalImagePixel.getGreen(), -30);
        darkBlue = clampHelper(originalImagePixel.getBlue(), -30);

        assertEquals(brightRed, brighterImagePixel.getRed());
        assertEquals(brightGreen, brighterImagePixel.getGreen());
        assertEquals(brightBlue, brighterImagePixel.getBlue());

        assertEquals(darkRed, darkerImagePixel.getRed());
        assertEquals(darkGreen, darkerImagePixel.getGreen());
        assertEquals(darkBlue, darkerImagePixel.getBlue());

      }
    }
  }

  // helps test brighten and darken operation testing clamp numbers
  private int clampHelper(int num1, int num2) {
    if (num1 + num2 < 0) {
      return 0;
    }
    else if (num1 + num2 > 255) {
      return 255;
    }
    else {
      return num1 + num2;
    }
  }

  @Test
  public void testExecuteSavePPM() {
    setInputs("load res/Landscape.ppm landscape luma-greyscale landscape red "
            + "save res/Landscape-luma.ppm luma-greyscale");

    String outString = out.toString();

    // should print when there's a command is completed successfully
    assertTrue(outString.contains("Command successful!"));
    assertTrue(outString.contains("(used save on image)"));

    // should be false as all the inputs were valid
    assertFalse(outString.contains("Command unsuccessful!"));
  }

  @Test
  public void testExecuteSaveJPG() {
    setInputs("load res/Landscape.ppm landscape luma-greyscale landscape red "
            + "save res/Landscape-luma.jpg luma-greyscale");

    String outString = out.toString();

    // should print when there's a command is completed successfully
    assertTrue(outString.contains("Command successful!"));
    assertTrue(outString.contains("(used save on image)"));

    // should be false as all the inputs were valid
    assertFalse(outString.contains("Command unsuccessful!"));
  }

  @Test
  public void testExecuteSavePNG() {
    setInputs("load res/Landscape.bmp landscape luma-greyscale landscape red "
            + "save res/Landscape-luma.png luma-greyscale");

    String outString = out.toString();

    // should print when there's a command is completed successfully
    assertTrue(outString.contains("Command successful!"));
    assertTrue(outString.contains("(used save on image)"));

    // should be false as all the inputs were valid
    assertFalse(outString.contains("Command unsuccessful!"));
  }

  @Test
  public void testExecuteSaveBMP() {
    setInputs("load res/Landscape.jpg landscape luma-greyscale landscape red "
            + "save res/Landscape-luma.bmp luma-greyscale");

    String outString = out.toString();

    // should print when there's a command is completed successfully
    assertTrue(outString.contains("Command successful!"));
    assertTrue(outString.contains("(used save on image)"));

    // should be false as all the inputs were valid
    assertFalse(outString.contains("Command unsuccessful!"));
  }

  @Test
  public void testExecuteImageCommandWithoutLoading() {
    setInputs("luma-greyscale landscape luma ");

    try {
      this.model.getImageNamed("luma");
      fail("No exception thrown");
    }
    catch (IllegalArgumentException e) {
      // passed
    }

    String outString = out.toString();

    // should not print successful as there's no images loaded in
    assertFalse(outString.contains("Command successful!"));

    assertTrue(outString.contains("Command unsuccessful! Image not found."));
  }

  @Test
  public void testExecuteAdjustBrightInvalidNumber() {
    setInputs("load res/Landscape.ppm landscape brighten landscape bright 10asd");
    String outString = out.toString();
    assertTrue(outString.contains("Command unsuccessful! Invalid number."));

  }

  @Test
  public void testExecuteNotEnoughInputs() {
    setInputs("load res/Landscape.ppm landscape darken landscape dark");
    String outString = out.toString();
    assertTrue(outString.contains("Command unsuccessful! Not enough inputs."));
  }

  @Test
  public void testExecuteFlip() {
    setInputs("load res/Landscape.ppm landscape flip-horizontally landscape hor-landscape "
            + "flip-vertically landscape vert-landscape");

    // should not throw an exception since they're all properly loaded into the model
    try {
      model.getImageNamed("landscape");
      model.getImageNamed("hor-landscape");
      model.getImageNamed("vert-landscape");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }

    String outString = out.toString();

    assertTrue(outString.contains("Command successful!"));
    assertFalse(outString.contains("Command unsuccessful!"));
  }

  @Test
  public void testExecuteThrowException() {
    try {
      view = new ImageProcessingTextView(new MockAppendable());
      controller = new ImageProcessingControllerImpl(model, view, in);
      controller.execute();
      fail("No exception thrown");
    }
    catch (IllegalStateException e) {
      // exception successfully thrown
    }
  }

  @Test
  public void testQuitInMiddleOfCommand() {
    setInputs("luma-greyscale quit image");

    String outString = out.toString();

    // doesn't print the quit message because "quit" was used as a input for the luma-greyscale
    // command
    assertFalse(outString.contains("Thanks for using this program! Goodbye!"));
    // program thinks that quit was the name of an image stored within the model
    assertTrue(outString.contains("Command unsuccessful! Image not found."));
  }

  @Test
  public void testQuitThirdInputCommand() {
    setInputs("load res/Landscape.ppm landscape luma-greyscale landscape quit");

    String outString = out.toString();

    // doesn't print the quit message because "quit" was used as a input for the luma-greyscale
    // command
    assertFalse(outString.contains("Thanks for using this program! Goodbye!"));

    try {
      // because quit was used as the third parameter in the luma greyscale operation, the image
      // was greyscaled is saved under the name quit and the program wasn't quit
      this.model.getImageNamed("quit");
    }
    catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }
}