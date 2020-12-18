import javax.swing.*;

public class RadioButton extends JRadioButton {
    /**
     * Create a jradio with bTitr font
     * @param text
     */
    public RadioButton(String text){
        super(text);
        setFont(Fonts.bTitr);
    }
}
