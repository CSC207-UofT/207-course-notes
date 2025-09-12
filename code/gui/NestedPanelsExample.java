package gui;

import javax.swing.*;

public class NestedPanelsExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JPanel firstNamePanel = new JPanel();
            firstNamePanel.add(new JLabel("First Name:"));
            firstNamePanel.add(new JTextField(10));

            JPanel lastNamePanel = new JPanel();
            lastNamePanel.add(new JLabel("Last Name:"));
            lastNamePanel.add(new JTextField(10));

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(new JButton("Submit"));
            buttonPanel.add(new JButton("Cancel"));

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
