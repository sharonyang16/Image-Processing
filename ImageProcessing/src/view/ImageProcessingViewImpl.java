package view;

import java.io.IOException;

import model.ImageProcessingModel;

public class ImageProcessingViewImpl {
  private ImageProcessingModel model;
  private Appendable out;

  public ImageProcessingViewImpl(ImageProcessingModel model, Appendable out)
          throws IllegalArgumentException {
    // exception
    this.model = model;
    this.out = out;
  }

  public void renderMessage(String message) throws IOException {
    out.append(message);
  }
}

