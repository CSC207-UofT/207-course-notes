package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
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

            submit.addActionListener(new ActionListener() {
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
