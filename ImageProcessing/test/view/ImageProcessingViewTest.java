package view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for the ImageProcessingView interface.
 */
public class ImageProcessingViewTest {
  private ImageProcessingView view1;
  private Appendable out;

  @Before
  public void init() {
    this.out = new StringBuilder();
    this.view1 = new ImageProcessingTextView(out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialization() {
    this.out = null;
    this.view1 = new ImageProcessingTextView(out);
  }

  @Test
  public void testRenderMessage() {
    try {
      this.view1.renderMessage("hi");
    }
    catch (IOException e) {
      fail("Exception was thrown");
    }

    assertEquals("hi\n", out.toString());
  }

  @Test
  public void testInvalidRenderMessage() {

    this.view1 = new ImageProcessingTextView(new MockAppendable());

    try {
      this.view1.renderMessage("hi");
      fail("No exception was thrown");
    }
    catch (IOException e) {
      // exception thrown successfully
    }
  }
}