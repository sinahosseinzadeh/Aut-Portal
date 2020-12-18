import javax.swing.*;
import java.awt.*;

public class OptionPane {
    /**
     * a optionpane with big btitr font
     * @param parent parent frame
     * @param message showing message
     * @param title showing title
     * @param mode mode of joptionpane
     */
    public static void showDialog(Component parent , String message, String title, int mode)
    {
        JLabel label = new JLabel(message);
        label.setFont(Fonts.bTitr);
        JOptionPane.showMessageDialog(parent,label,title, mode);
    }

    /**
     * get a string with persian optionpane
     * @param parent parent frame
     * @param message showing message
     * @param title showing title
     * @param mode mode of joptionpane
     * @return
     */
    public static String getString(Component parent , String message, String title, int mode)
    {
        JLabel label = new JLabel(message);
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label.setFont(Fonts.bTitr);
        return JOptionPane.showInputDialog(parent,label,title, mode);
    }
}
