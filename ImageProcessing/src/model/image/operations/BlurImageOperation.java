package model.image.operations;

public class BlurImageOperation extends FilterImageOperation {
  public BlurImageOperation() {
    super(new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}});
  }
}
