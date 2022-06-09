package view;

import java.io.IOException;

import model.ImageProcessingModel;

public class ImageProcessingViewImpl implements ImageProcessingView {
  private ImageProcessingModel model;
  private Appendable out;

  public ImageProcessingViewImpl(ImageProcessingModel model, Appendable out)
          throws IllegalArgumentException {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Model and Appendable object cannot be null!");
    }
    this.model = model;
    this.out = out;
  }

  public void renderMessage(String message) throws IOException {
    out.append(message + "\n");
  }
}

