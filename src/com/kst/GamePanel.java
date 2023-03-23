package com.kst;

import javax.swing.*;
import java.awt.*;

class GamePanel extends JFrame {
    private PlayGrid compGrid;
    private PlayGrid userGrid;

    GamePanel(int level) {
        super("Floors. Level: " + level);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        addButtons(level, gbc);
        addMenu();

        this.compGrid = new PlayGrid(level, true);
        this.userGrid = new PlayGrid(level, false);

        setGameConditions(compGrid, gbc);
        setGameField(level, gbc);
    }

    private void setGameField(int level, GridBagConstraints gbc) {
        UserNumberButton userNumberButton;
        for (int i = 1; i < 1 + level + 1 - 1; ++i) {
            for (int j = 1; j < 1 + level + 1 - 1; ++j) {
                gbc.gridx = j;
                gbc.gridy = i;
                userNumberButton = new UserNumberButton(userGrid, i - 1, j - 1);
                this.add(userNumberButton, gbc);
            }
        }
    }

    private void setGameConditions(PlayGrid pg, GridBagConstraints gbc) {
        int[][] conditions = pg.getConditions();
        int len = pg.getElems().length;

        JLabel label;

        for (int i = 0; i < len; i++) {
            label = new JLabel(Integer.toString(conditions[0][i]));
            setLabelConditionsOption(label);
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            this.add(label, gbc);

            label = new JLabel(Integer.toString(conditions[1][i]));
            setLabelConditionsOption(label);
            gbc.gridx = i + 1;
            gbc.gridy = 0;
            this.add(label, gbc);

            label = new JLabel(Integer.toString(conditions[2][i]));
            setLabelConditionsOption(label);
            gbc.gridx = 1 + len + 1 - 1;
            gbc.gridy = i + 1;
            this.add(label, gbc);

            label = new JLabel(Integer.toString(conditions[3][i]));
            setLabelConditionsOption(label);
            gbc.gridx = i + 1;
            gbc.gridy = 1 + len + 1 - 1;
            this.add(label, gbc);
        }
    }

    private void setLabelConditionsOption(JLabel label) {
        label.setOpaque(true);
        label.setBackground(Color.GRAY);
        label.setFont(new Font("Verdana", Font.BOLD, 25));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label.setPreferredSize(new Dimension(50, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void addButtons(int level, GridBagConstraints gbc) {
        JButton checkButton = new JButton("Check");
        checkButton.setPreferredSize(new Dimension(130, 50));
        checkButton.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setResizable(false);
            frame.setUndecorated(true);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setSize(150, 100);

            JButton button = new JButton();
            String text = (this.userGrid.isEqual(this.compGrid)) ? "OK" : "Wrong!";
            button.setText(text);
            button.setFont(new Font("Verdana", Font.PLAIN, 20));
            button.addActionListener(ee -> frame.dispose());

            frame.getContentPane().setLayout(new BorderLayout());
            frame.add(button, BorderLayout.CENTER);

            frame.setVisible(true);
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(checkButton, gbc);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(130, 50));
        newGameButton.addActionListener(e -> startNewGame());
        gbc.gridx = 1 + level + 1 - 1;
        gbc.gridy = 0;
        this.add(newGameButton, gbc);

        JButton answerButton = new JButton("Show Answer");
        answerButton.setPreferredSize(new Dimension(130, 50));
        answerButton.addActionListener(e -> compGrid.showGrid());
        gbc.gridx = 0;
        gbc.gridy = 1 + level + 1 - 1;
        this.add(answerButton, gbc);

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(130, 50));
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridx = 1 + level + 1 - 1;
        gbc.gridy = 1 + level + 1 - 1;
        this.add(exitButton, gbc);
    }

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> startNewGame());

        JMenuItem clearItem = new JMenuItem("Clear All");
        clearItem.addActionListener(e -> {
            for(Component c : this.getContentPane().getComponents()) {
                if(c instanceof UserNumberButton) {
                    ((UserNumberButton) c).setValue(0, ((UserNumberButton) c).getGridX(), ((UserNumberButton) c).getGridY());
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newGameItem);
        fileMenu.add(clearItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

    private void startNewGame() {
        this.setVisible(false);
        StartPanel app = new StartPanel("Floors");
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }

}
