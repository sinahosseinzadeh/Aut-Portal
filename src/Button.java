import javax.swing.*;

public class Button extends JButton {
    /**
     * persian button with text
     * @param text
     */
    public Button(String text){
        super(text);
        setFont(Fonts.bTitr);
    }
}
