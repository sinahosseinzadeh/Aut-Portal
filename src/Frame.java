import com.sun.javafx.logging.JFRInputEvent;

import javax.swing.*;
import java.awt.*;

public abstract class Frame extends JFrame {
    private static Frame frame = null;
    public Frame(String name)
    {
        super(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void setAll(){
        setSettings();
        initComponents();
        addComponents();
        addListeners();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    public abstract void  setSettings();
    public abstract void initComponents();
    public abstract void addComponents();
    public abstract void addListeners();

    public void add(JComponent jComponent, int x, int y)
    {
        add(jComponent);
        jComponent.setLocation(x,y);
    }
}
