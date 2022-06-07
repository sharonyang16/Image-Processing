package model.image;

import java.util.ArrayList;

import model.image.operations.ImageOperation;
import model.pixel.Pixel;

public class SimpleImage implements Image {
  private ArrayList<ArrayList<Pixel>> image;

  public SimpleImage(ArrayList<ArrayList<Pixel>> image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    if (image.size() == 0) {
      throw new IllegalArgumentException();
    }
    if (image.get(0).size() == 0) {
      throw new IllegalArgumentException();
    }
    this.image = image;
  }

  @Override
  public void accept(ImageOperation o) {

  }

  @Override
  public int getHeight() {
    return this.image.size();
  }

  @Override
  public int getWidth() {
    return this.image.get(0).size();
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return this.image.get(row).get(col);
  }

  @Override
  public void flipHorizontally() {
    for (int i = 0; i < this.getHeight(); i = i + 1) {
      for (int j = 0; j < this.getWidth() / 2; j = j + 1) {
        Pixel front = this.getPixelAt(i, j);
        Pixel back = this.getPixelAt(i, this.getWidth() - 1 - j);
        this.image.get(i).set(j, back);
        this.image.get(i).set(this.getWidth() - 1 - j, front);
      }
    }
  }

  @Override
  public void flipVertically() {
    for (int i = 0; i < this.getHeight() / 2; i = i + 1) {
      for (int j = 0; j < this.getWidth(); j = j + 1) {
        Pixel front = this.getPixelAt(i, j);
        Pixel back = this.getPixelAt(this.getHeight() - 1 - i, j);
        this.image.get(i).set(j, back);
        this.image.get(this.getHeight() - 1 - i).set(j, front);
      }
    }
  }
}
