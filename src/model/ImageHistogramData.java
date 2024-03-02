package model;

public interface ImageHistogramData {
  int getNumRedAtValue(int value) throws IllegalArgumentException;
  int getNumGreenAtValue(int value) throws IllegalArgumentException;
  int getNumBlueAtValue(int value) throws IllegalArgumentException;
  int getNumIntensityAtValue(int value) throws IllegalArgumentException;
}
