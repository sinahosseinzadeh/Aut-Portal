import javax.swing.*;
import java.awt.*;

public class OptionPane {
    public static void showDialog(Component parent , String message, String title, int mode)
    {
        JLabel label = new JLabel(message);
        label.setFont(Fonts.bTitr);
        JOptionPane.showMessageDialog(parent,label,title, mode);
    }
    public static String getString(Component parent , String message, String title, int mode)
    {
        JLabel label = new JLabel(message);
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label.setFont(Fonts.bTitr);
        return JOptionPane.showInputDialog(parent,label,title, mode);
    }
}
