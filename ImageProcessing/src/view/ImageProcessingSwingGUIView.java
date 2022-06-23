package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageProcessingSwingGUIView extends JFrame implements ImageProcessingGUIView {

  private JPanel mainPanel;
  private JTextField userInputField;
  private JButton  userInputEnterButton;
  private JButton fileLoadButton;
  private JButton fileSaveButton;
  private JLabel messageLabel;
  private JPanel imagePanel;
  private JLabel imageLabel;
  private JLabel imageCaptionLabel;

  private JPanel histogramPanel;
  private ImageHistogramPanel imageHistogram;

  public ImageProcessingSwingGUIView() {
    super();
    setTitle("Image Processing Application");
    setSize(1400, 600);

    this.mainPanel = new JPanel();
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
    this.mainPanel.setLayout(new FlowLayout());

    JPanel menuPanel = new JPanel();
    menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
    this.mainPanel.add(menuPanel);

    JTextArea menuText = new JTextArea(14, 20);
    menuText.setFont(new Font("Arial", Font.PLAIN, 15));
    menuText.setEditable(false);
    JScrollPane menuScroll = new JScrollPane(menuText);
    menuPanel.add(menuScroll);
    menuScroll.setBorder(BorderFactory.createTitledBorder("Image Operations"));
    menuText.append("red-greyscale\ngreen-greyscale\nblue-greyscale\n");
    menuText.append("value-greyscale\nintensity-greyscale\nluma-greyscale\n");
    menuText.append("flip-horizontally\nflip-vertically\n");
    menuText.append("brighten\ndarken\n");
    menuText.append("blur\nsharpen\ngreyscale\nsepia");

    JPanel userInputPanel = new JPanel();
    userInputPanel.setLayout(new FlowLayout());
    menuPanel.add(userInputPanel);

    this.userInputField = new JTextField(25);
    userInputPanel.add(userInputField);
    // menuPanel.add(userInputField);
    this.userInputField.setEditable(true);
    this.userInputField.setBorder(BorderFactory.createTitledBorder("Enter Command Here"));

    this.userInputEnterButton = new JButton("Enter");
    userInputPanel.add(userInputEnterButton);
    userInputEnterButton.setFont(new Font("Arial", Font.BOLD, 12));
    userInputEnterButton.setPreferredSize(new Dimension(70, 35));
    userInputEnterButton.setActionCommand("enter");


    JPanel messagePanel = new JPanel();
    messagePanel.setSize(new Dimension(300, 25));
    this.messageLabel = new JLabel("Load an image to get started!");
    this.messageLabel.setFont(new Font("Arial", Font.PLAIN, 10));
    this.messageLabel.setVerticalTextPosition(SwingConstants.CENTER);
    messagePanel.add(messageLabel);
    menuPanel.add(messagePanel);

    // panel for load button
    JPanel fileLoadPanel = new JPanel();
    menuPanel.add(fileLoadPanel);
    this.fileLoadButton = new JButton("Load an image");
    fileLoadButton.setPreferredSize(new Dimension(300, 25));
    fileLoadButton.setActionCommand("load");
    fileLoadPanel.add(fileLoadButton);

    // panel for save button
    JPanel fileSavePanel = new JPanel();
    menuPanel.add(fileSavePanel);
    this.fileSaveButton = new JButton("Save current image");
    fileSaveButton.setPreferredSize(new Dimension(300, 25));
    fileSaveButton.setActionCommand("save");
    fileSavePanel.add(fileSaveButton);

    JPanel imageAndCaptionPanel = new JPanel();
    imageAndCaptionPanel.setLayout(new BoxLayout(imageAndCaptionPanel, BoxLayout.Y_AXIS));
    this.mainPanel.add(imageAndCaptionPanel);

    // panel for displaying image
    this.imagePanel = new JPanel();
    this.imagePanel.setBorder(BorderFactory.createTitledBorder("Current Image"));
    imageAndCaptionPanel.add(imagePanel);
    this.imageLabel = new JLabel();
    imagePanel.setPreferredSize(new Dimension(500, 500));

    JPanel imageCaptionPanel = new JPanel();
    imageCaptionPanel.setSize(new Dimension(500, 25));
    this.imageCaptionLabel = new JLabel("You'll see the file path of the current image here!");
    this.imageCaptionLabel.setFont(new Font("Arial", Font.PLAIN, 10));
    this.imageCaptionLabel.setVerticalTextPosition(SwingConstants.CENTER);
    imageCaptionPanel.add(this.imageCaptionLabel);
    imageAndCaptionPanel.add(imageCaptionPanel);

    this.histogramPanel = new JPanel();
    this.histogramPanel.setLayout(new GridBagLayout());
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram for Current Image"));
    histogramPanel.setPreferredSize(new Dimension(400, 500));
    this.mainPanel.add(histogramPanel);


    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void refresh() {
    this.revalidate();
  }

  @Override
  public void changeCurrentImage(BufferedImage image) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.weighty = 1;

    this.imagePanel.removeAll();
    this.imageLabel = new JLabel(new ImageIcon(image));
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(
            new Dimension(imagePanel.getWidth() - 40,
                    imagePanel.getHeight() - 40));
    this.imagePanel.add(imageScrollPane);

    this.histogramPanel.removeAll();

    this.imageHistogram = new ImageHistogramPanel(image);
    JScrollPane histogramScrollPane = new JScrollPane(imageHistogram);
    histogramScrollPane.setPreferredSize(
            new Dimension(histogramPanel.getWidth() - 40,
                    histogramPanel.getHeight() - 40));
    this.histogramPanel.add(histogramScrollPane, gbc);
  }

  @Override
  public JButton getLoadButton() {
    return this.fileLoadButton;
  }

  @Override
  public JButton getSaveButton() {
    return this.fileSaveButton;
  }

  @Override
  public JButton getEnterButton() {
    return this.userInputEnterButton;
  }

  @Override
  public JLabel getCommandTextLabel() {
    return this.messageLabel;
  }

  @Override
  public JLabel getImageCaptionLabel() {
    return this.imageCaptionLabel;
  }

  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  @Override
  public void buttonAction(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "load": {
        JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PPM, JPG, PNG, & BMP Images",
                "ppm", "jpg", "png", "bmp");
        fileChooser.setFileFilter(filter);
        int retvalue = fileChooser.showOpenDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          this.imageCaptionLabel.setText(file.getAbsolutePath());
        }
      }
        break;
      case "save": {
        JFileChooser fileChooser = new JFileChooser(".");
        int retvalue = fileChooser.showSaveDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          this.imageCaptionLabel.setText(file.getAbsolutePath());
        }
      }
        break;
      case "enter":
        if (userInputField.getText().equals("brighten") || userInputField.getText().equals("darken")) {
          String value = JOptionPane.showInputDialog(
                  "How much should the image be " + userInputField.getText() + " by?");
          renderMessage(userInputField.getText() + " " + value);
        }
        else {
          renderMessage(userInputField.getText());
        }

        userInputField.setText("");
        break;
      default:
        break;
    }
  }
}
