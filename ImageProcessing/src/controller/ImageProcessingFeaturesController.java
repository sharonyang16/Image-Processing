package controller;

import java.awt.image.BufferedImage;

import model.image.MyImage;

public interface ImageProcessingFeaturesController extends ImageProcessingController {

  BufferedImage getBufferedImage(MyImage image);
}
