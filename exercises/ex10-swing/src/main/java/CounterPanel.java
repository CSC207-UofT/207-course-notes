import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Exercise (Chapter 4: GUIs with Swing) — handling button clicks.
 *
 * This panel shows a label and a button. Your task is to make clicking the
 * button increment a counter and update the label to {@code "Count: N"}. You do
 * this by adding an <em>action listener</em> to the button, as described in
 * Chapter 4.3. Edit only this file.
 *
 * You can run {@code main} to see the window and click the button yourself, and
 * the tests click the button for you (with {@code button.doClick()}) and check
 * the label.
 *
 * Relevant reading: 4.3 Handling button clicks.
 */
public class CounterPanel extends JPanel {

  private int count = 0;
  private final JButton button = new JButton("Click me");
  private final JLabel label = new JLabel("Count: 0");

  /** Builds the panel and wires up the button. */
  public CounterPanel() {
    add(label);
    add(button);
    // TODO: add an action listener to `button` (see Chapter 4.3:
    //       button.addActionListener(...)). When the button is clicked, its
    //       actionPerformed should increment `count` and then call
    //       label.setText("Count: " + count) so the label shows the new total.
  }

  /**
   * Returns the button, so tests (and you) can click it.
   *
   * @return the button
   */
  public JButton getButton() {
    return button;
  }

  /**
   * Returns the label that displays the count.
   *
   * @return the label
   */
  public JLabel getLabel() {
    return label;
  }

  /** Shows the panel in a window so you can click the button yourself. */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          JFrame frame = new JFrame("Counter");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setContentPane(new CounterPanel());
          frame.pack();
          frame.setVisible(true);
        });
  }
}
