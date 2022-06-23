package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

import model.CurrentImageProcessingModel;
import model.ImageProcessingModel;
import model.image.MyImage;
import model.pixel.TransparentPixel;
import view.ImageProcessingGUIView;
import view.ImageProcessingTextView;

public class ImageProcessingFeaturesControllerImpl implements ImageProcessingFeaturesController {
  private ImageProcessingController delegateController;
  private ImageProcessingGUIView view;
  private ImageProcessingModel model;
  private StringReader inputs;

  public ImageProcessingFeaturesControllerImpl
          (ImageProcessingGUIView view, CurrentImageProcessingModel model)
          throws IllegalArgumentException {
    if (view == null || model == null) {
      throw new IllegalArgumentException("View or model is null!");
    }
    this.view = view;
    this.model = model;
    this.inputs = new StringReader("");
    this.delegateController = new ImageProcessingControllerImpl(model,
            new ImageProcessingTextView(new StringWriter()), inputs);

    this.view.getLoadButton().addActionListener(e -> {
      try {
        this.view.buttonAction(e);
        MyImage loadedImage = getMyImage(this.view.getImageCaptionLabel().getText());
        this.model.loadImage(loadedImage, "image");
        this.view.changeCurrentImage(getBufferedImage(this.model.getCurrentImage()));
        this.view.refresh();
        try {
          this.view.renderMessage("Image loaded in successfully!");
        }
        catch (IOException excep) {

        }
      }
      catch (IllegalArgumentException ex) {
        try {
          this.view.renderMessage("Image could not be loaded in.");
        }
        catch (IOException excep) {

        }
      }
    });

    this.view.getSaveButton().addActionListener(e -> {
      try {
        this.view.buttonAction(e);
        MyImage loadedImage = this.model.getCurrentImage();
        String filePath = this.view.getImageCaptionLabel().getText();
        this.writeImage(loadedImage, filePath);
      }
      catch (IllegalArgumentException ex) {
        try {
          this.view.renderMessage("Image could not be saved successfully");
        }
        catch (IOException excep) {

        }
      }
    });

    this.view.getEnterButton().addActionListener(e -> {
      this.view.buttonAction(e);
      String[] input = this.view.getCommandTextLabel().getText().split(" ");
      if (input.length > 0 && input.length <= 2
              && !Arrays.asList(input).contains("load")
              && !Arrays.asList(input).contains("save")) {
        if (input.length == 1) {
          this.inputs = new StringReader(input[0] + " image image ");
        }
        else {
          this.inputs = new StringReader(input[0] + " image image " + input[1] + " ");
        }
        this.delegateController = new ImageProcessingControllerImpl(model, view, this.inputs);
        this.delegateController.execute();
        this.view.changeCurrentImage(getBufferedImage(this.model.getCurrentImage()));
        this.view.refresh();
      }
      else {
        try {
          this.view.renderMessage
                  ("Could not " + this.view.getCommandTextLabel().getText()
                            + " to current image.");
        }
        catch (IOException ioException) {

        }

      }
    });
  }

  @Override
  public BufferedImage getBufferedImage(MyImage image) {
    BufferedImage buffImage
            = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

    // builds the BufferedImage pixel by pixel
    for (int i = 0; i < image.getHeight(); i = i + 1) {
      for (int j = 0; j < image.getWidth(); j = j + 1) {
        TransparentPixel curPixel = image.getPixelAt(i, j);
        Color pixelColor
                = new Color(curPixel.getRed(), curPixel.getGreen(),
                curPixel.getBlue(), curPixel.getAlpha());

        buffImage.setRGB(j, i, pixelColor.getRGB());
      }
    }
    return buffImage;
  }

  @Override
  public void execute() {
    this.delegateController.execute();
  }

  @Override
  public MyImage getMyImage(String filePath) throws IllegalArgumentException {
    return this.delegateController.getMyImage(filePath);
  }

  @Override
  public void writeImage(MyImage image, String filePath) throws IllegalArgumentException {
    this.delegateController.writeImage(image, filePath);
  }
}
