package view;

import java.io.IOException;

/**
 * Mock class for an appendable that only throws an IOException.
 */
public class MockAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Transmission failed");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Transmission failed");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Transmission failed");
  }
}
