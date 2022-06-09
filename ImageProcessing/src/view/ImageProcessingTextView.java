package view;

import java.io.IOException;

import model.ImageProcessingModel;

/**
 * This class represents a text based view for a image processing application.
 */
public class ImageProcessingTextView implements ImageProcessingView {
  private Appendable out;

  /**
   * Creates a text based view with the given Appendable object.
   *
   * @param out the Appendable object
   * @throws IllegalArgumentException if the Appendable object is null
   */
  public ImageProcessingTextView(Appendable out)
          throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("Appendable object cannot be null!");
    }
    this.out = out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    out.append(message + "\n");
  }
}

