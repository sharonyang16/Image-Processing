package view;

import java.io.IOException;

/**
 * This interface represents the view of an image processing application.
 */
public interface ImageProcessingView {
  /**
   * Renders the given message.
   * @param message the message
   * @throws IOException if it is unable to render the message
   */
  void renderMessage(String message) throws IOException;
}
