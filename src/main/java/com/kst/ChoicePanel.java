package com.kst;

import javax.swing.*;
import java.awt.*;

class ChoicePanel extends JFrame {
    private int value = 0;

    ChoicePanel(UserNumberButton parent, int maxValue, int x, int y) {
        super();

        this.getContentPane().setLayout(new GridLayout(4, 3, 2, 2));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(150, 200);

        JButton button;
        for (int i = 0; i < 9; ++i) {
            button = new JButton(Integer.toString(i + 1));
            button.setFont(new Font("Verdana", Font.BOLD, 15));
            button.addActionListener(e -> {
                this.value = Integer.parseInt(((JButton) (e.getSource())).getText());
                parent.setValue(this.value, x, y);
                this.dispose();
            });
            this.add(button);
            if (i >= maxValue) {
                button.setEnabled(false);
            }
        }

        button = new JButton("X");
        button.setFont(new Font("Verdana", Font.BOLD, 13));
        button.addActionListener(e -> {
            this.value = 0;
            parent.setValue(0, x ,y);
            this.dispose();
        });
        this.add(button);
    }
}
