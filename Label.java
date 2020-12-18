import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    /**
     * label with persian font
     * @param text showing text
     */
    public Label(String text){
        super(text);
        setFont(Fonts.bTitr);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    }

}
