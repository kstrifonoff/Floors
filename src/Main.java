import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        StartPanel app = new StartPanel("Floors");
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}
