package gui;

import javax.swing.*;

public class LabelAndButtonExample {
    public static void main(String[] args) {
        JLabel label = new JLabel("Hello, Swing!");
        JButton button = new JButton("Click Me");

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(button);

        JFrame frame = new JFrame("Simple Swing App");
        frame.setSize(300, 200);
        frame.setContentPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
