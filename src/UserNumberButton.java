import javax.swing.*;

class UserNumberButton extends JButton {

    void setValue(int value, int x, int y) {
        String text = (value == 0) ? "*" : Integer.toString(value);
        this.setText(text);
        grid.setElemsValue(x, y, value);
    }
    private int gridX;
    private int gridY;
    private PlayGrid grid;

    int getGridX() {
        return gridX;
    }

    int getGridY() {
        return gridY;
    }

    UserNumberButton(PlayGrid grid, int x, int y) {
        super("*");
        this.grid = grid;
        this.gridX = x;
        this.gridY = y;
        this.addActionListener(e -> {
            ChoicePanel choicePanel = new ChoicePanel(this, grid.getLevel(), this.gridX, this.gridY);
            choicePanel.setLocationRelativeTo(null);
            choicePanel.setVisible(true);
        });
    }
}
