import com.sun.javafx.logging.JFRInputEvent;

import javax.swing.*;
import java.awt.*;

public abstract class Frame extends JFrame {
    private static Frame frame = null;

    /**
     * Frame with default settings
     * @param name name of frame
     */
    public Frame(String name)
    {
        super(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    //set all components , settings , listeners for frame
    public void setAll(){
        setSettings();
        initComponents();
        addComponents();
        addListeners();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * set frame settings
     */
    public abstract void  setSettings();

    /**
     * make instance of objects
     */
    public abstract void initComponents();

    /**
     * add components to frame
     */
    public abstract void addComponents();

    /**
     * add listeners to components
     */
    public abstract void addListeners();

    /**
     * add a component to x and y in frame without layout
     * @param jComponent adding component
     * @param x x location
     * @param y y location
     */
    public void add(JComponent jComponent, int x, int y)
    {
        add(jComponent);
        jComponent.setLocation(x,y);
    }
}
