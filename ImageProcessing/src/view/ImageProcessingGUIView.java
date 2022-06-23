package view;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;

public interface ImageProcessingGUIView extends ImageProcessingView {
  void refresh();

  void changeCurrentImage(BufferedImage image);

  JButton getLoadButton();

  JButton getSaveButton();

  JButton getEnterButton();

  JLabel getCommandTextLabel();

  JLabel getImageCaptionLabel();

  void buttonAction(ActionEvent e);
}
