package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import model.ImageHistogramData;
import model.ImageHistogramDataImpl;

public class ImageHistogramPanel extends JPanel {
  private ImageHistogramData data;

  public ImageHistogramPanel(BufferedImage image) {
    super();
    data = new ImageHistogramDataImpl(image);
    setLayout(new OverlayLayout(this));


    JPanel redGraphPanel = new JPanel();
    redGraphPanel.setOpaque(false);
    add(redGraphPanel, JComponent.LEFT_ALIGNMENT);
    redGraphPanel.setLayout(new GridBagLayout());

    JPanel greenGraphPanel = new JPanel();
    greenGraphPanel.setOpaque(false);
    add(greenGraphPanel, JComponent.LEFT_ALIGNMENT);
    greenGraphPanel.setLayout(new GridBagLayout());

    JPanel blueGraphPanel = new JPanel();
    blueGraphPanel.setOpaque(false);
    add(blueGraphPanel, JComponent.LEFT_ALIGNMENT);
    blueGraphPanel.setLayout(new GridBagLayout());

    JPanel intensityGraphPanel = new JPanel();
    intensityGraphPanel.setOpaque(false);
    add(intensityGraphPanel, JComponent.LEFT_ALIGNMENT);
    intensityGraphPanel.setLayout(new GridBagLayout());

    JPanel redBar;
    JPanel greenBar;
    JPanel blueBar;
    JPanel intensityBar;

    int barWidth = 3;

    for(int i = 100; i < 190; i = i + 1) {
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.anchor = GridBagConstraints.SOUTH;
      gbc.weighty = 1;

      redBar = new JPanel();
      redBar.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
      redBar.setPreferredSize(new Dimension(barWidth, data.getNumRedAtValue(i) / 2));
      redBar.setBackground(new Color(255, 0, 0, 64));
      redGraphPanel.add(redBar, gbc);

      greenBar = new JPanel();
      greenBar.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
      greenBar.setPreferredSize(new Dimension(barWidth, data.getNumGreenAtValue(i) / 2));
      greenBar.setBackground(new Color(0, 255, 0, 64));
      greenGraphPanel.add(greenBar, gbc);

      blueBar = new JPanel();
      blueBar.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
      blueBar.setPreferredSize(new Dimension(barWidth, data.getNumBlueAtValue(i) / 2));
      blueBar.setBackground(new Color(0, 0, 255, 64));
      blueGraphPanel.add(blueBar, gbc);

      intensityBar = new JPanel();
      intensityBar.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
      intensityBar.setPreferredSize(new Dimension(barWidth, data.getNumIntensityAtValue(i) / 2));
      intensityBar.setBackground(new Color(100, 100, 100, 64));
      intensityGraphPanel.add(intensityBar, gbc);
    }

  }
}
