import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {
    public static Font bTitr;
    public static Font bTitrLarge;
    public static Font boldArial;

    /**
     * build all used fonts.
     */
    public static void buildFonts() {
        try {
            bTitr = Font.createFont(Font.TRUETYPE_FONT , new File("src\\Resources\\bTitr.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(bTitr);

            bTitrLarge = Font.createFont(Font.TRUETYPE_FONT , new File("src\\Resources\\bTitr.ttf")).deriveFont(25f);
            ge.registerFont(bTitrLarge);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        boldArial =new Font("Arial",Font.BOLD,20);

    }
}