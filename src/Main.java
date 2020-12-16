import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /**
     * app starts from here.
     * @param args do nothing
     */
    public static void main(String[] args) {
        Fonts.buildFonts();
        DataBase.load();
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        new MainPage().setVisible(true);
    }
}
