package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** A minimal Swing example that opens an empty JFrame window. */
public class MainFrame {

  /**
   * Builds and displays the window.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          JFrame frame = new JFrame("Intro JFrame Example");
          frame.setMinimumSize(new java.awt.Dimension(300, 200));
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.pack();
          frame.setVisible(true);
        });
  }
}
