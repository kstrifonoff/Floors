package com.kst;

import javax.swing.*;
import java.awt.*;

class PlayGrid {
    private int[][] elems;
    private int[][] conditions;     //[left, top, right, bottom]

    private int level;

    PlayGrid(int n, boolean isAuto) {
        this.level = n;
        this.elems = new int[n][n];
        this.conditions = new int[4][n];
        if (isAuto) {
            createGrid();
            setupConditions();
        }
    }

    private void createGrid() {
        int len = this.elems.length;
        //Начальный квадрат
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                this.elems[i][j] = (i + j) % len + 1;
            }
        }

        int numRand = 10;
        for (int i = 0; i < numRand; ++i) {
            changeColumns((int) (Math.random() * 10) % (len - 1), (int) (Math.random() * 10) % (len - 1));
            changeRows((int) (Math.random() * 10) % (len - 1), (int) (Math.random() * 10) % (len - 1));
        }
    }

    private void setupConditions() {
        int len = this.elems.length;
        int[][] tmpArr;

        tmpArr = calculate(len, true);
        System.arraycopy(tmpArr[0], 0, this.conditions[0], 0, len);
        System.arraycopy(tmpArr[1], 0, this.conditions[2], 0, len);

        tmpArr = calculate(len, false);
        System.arraycopy(tmpArr[0], 0, this.conditions[1], 0, len);
        System.arraycopy(tmpArr[1], 0, this.conditions[3], 0, len);
    }

    private int[][] calculate(int len, boolean isSide) {
        int bufMax;
        int[] tmpArrStraight = new int[len];
        int[] tmpArrReverse = new int[len];
        boolean check;

        for (int i = 0; i < len; ++i) {
            tmpArrStraight[i] = 1;
            tmpArrReverse[i] = 1;

            bufMax = isSide ? this.elems[i][0] : this.elems[0][i];
            for (int j = 1; j < len; ++j) {
                check = isSide ? (this.elems[i][j] > bufMax) : (this.elems[j][i] > bufMax);
                if (check) {
                    ++tmpArrStraight[i];
                    bufMax = isSide ? this.elems[i][j] : this.elems[j][i];
                }
                if (bufMax == len)
                    break;
            }

            bufMax = isSide ? this.elems[i][len - 1] : this.elems[len - 1][i];
            for (int j = len - 1 - 1; j >= 0; --j) {
                check = isSide ? (this.elems[i][j] > bufMax) : (this.elems[j][i] > bufMax);
                if (check) {
                    ++tmpArrReverse[i];
                    bufMax = isSide ? this.elems[i][j] : this.elems[j][i];
                }
                if (bufMax == len)
                    break;
            }
        }
        return new int[][]{tmpArrStraight, tmpArrReverse};
    }

    private void changeRows(int k, int l) {
        for (int i = 0; i < this.elems.length; ++i) {
            this.elems[k][i] += this.elems[l][i] - (this.elems[l][i] = this.elems[k][i]);
        }
    }

    private void changeColumns(int k, int l) {
        for (int i = 0; i < this.elems.length; ++i) {
            this.elems[i][k] += this.elems[i][l] - (this.elems[i][l] = this.elems[i][k]);
        }
    }

    int[][] getElems() {
        return elems;
    }

    void setElemsValue(int i, int j, int value) {
        this.elems[i][j] = value;
    }

    int[][] getConditions() {
        return conditions;
    }

    int getLevel() {
        return level;
    }

    boolean isEqual(PlayGrid otherGrid) {
        int len = this.level;
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < len; ++j) {
                if (this.elems[i][j] != otherGrid.elems[i][j])
                    return false;
            }
        }
        return true;
    }

    void showGrid() {
        int len = this.level;
        JFrame frame = new JFrame("Answer");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new GridLayout(len, len, 1, 1));
        for(int i=0; i<len; ++i) {
            for(int j=0; j<len; ++j) {
                JLabel label = new JLabel(Integer.toString(this.elems[i][j]));
                label.setOpaque(true);
                label.setBackground(Color.GRAY);
                label.setFont(new Font("Verdana", Font.BOLD, 25));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                label.setPreferredSize(new Dimension(50, 50));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                frame.add(label);
            }
        }

        frame.pack();
        frame.setVisible(true);
    }
}
