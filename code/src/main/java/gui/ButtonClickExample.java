package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/** A small Swing example: labelled text fields and a Submit button that shows a greeting dialog. */
public class ButtonClickExample {

  /**
   * Builds and displays the window.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          JPanel firstNamePanel = new JPanel();
          JTextField firstNameField = new JTextField(10);
          firstNamePanel.add(new JLabel("First Name:"));
          firstNamePanel.add(firstNameField);

          JPanel lastNamePanel = new JPanel();
          JTextField lastNameField = new JTextField(10);
          lastNamePanel.add(new JLabel("Last Name:"));
          lastNamePanel.add(lastNameField);

          JPanel buttonPanel = new JPanel();
          JButton submit = new JButton("Submit");
          buttonPanel.add(submit);
          buttonPanel.add(new JButton("Cancel"));

          submit.addActionListener(
              new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  String firstName = firstNameField.getText();
                  String lastName = lastNameField.getText();
                  JOptionPane.showMessageDialog(null, "Hello " + firstName + " " + lastName);
                }
              });

          JPanel mainPanel = new JPanel();
          mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
          mainPanel.add(firstNamePanel);
          mainPanel.add(lastNamePanel);
          mainPanel.add(buttonPanel);

          JFrame frame = new JFrame("Nested Panels Example");
          frame.setContentPane(mainPanel);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.pack();
          frame.setVisible(true);
        });
  }
}
