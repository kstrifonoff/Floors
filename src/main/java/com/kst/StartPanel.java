package com.kst;

import javax.swing.*;
import java.awt.*;

class StartPanel extends JFrame {
    StartPanel(String title) {
        super(title);
        this.setSize(250, 100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton startButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");
        JComboBox<Integer> levelComboBox = new JComboBox<>(new Integer[]{3, 4, 5, 6, 7, 8, 9});

        this.getContentPane().setLayout(new GridBagLayout());

        startButton.addActionListener(e -> {
            this.setVisible(false);
            GamePanel gamePanel = new GamePanel((int)levelComboBox.getSelectedItem());
            gamePanel.pack();
            gamePanel.setLocationRelativeTo(null);
            gamePanel.setVisible(true);
        });
        exitButton.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(levelComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(startButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(exitButton, gbc);
    }
}
