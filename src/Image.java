import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Image extends JLabel {
    /**
     * image with JLabel
     * @param name name of file in resources folder
     */
    public Image(String name) {
        String imageName =  "src\\resources\\" + name;
        setVisible(true);
        try {
            setIcon( new ImageIcon(ImageIO.read( new File(imageName) ) ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
