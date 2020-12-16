import Model.*;
import Model.Class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * portal for masters
 */
public class MasterPortal extends Portal{
    private JScrollPane right;
    private JScrollPane left;
    private JList classList;
    private Master master;

    public MasterPortal(Master master) {
        super(master);
        setLayout(new BorderLayout());
        addMasterComponents();
        this.master = master;
    }

    private void addMasterComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        add(panel,BorderLayout.CENTER);

        classList = new JList(DataBase.getClasses().toArray());
        classList.setFont(Fonts.boldArial);
        left = new JScrollPane(classList);
        panel.add(left);

        JButton minusButton = new JButton("-");
         minusButton.setFont(Fonts.bTitrLarge);
        JButton plusButton = new JButton("+");
         plusButton.setFont(Fonts.bTitrLarge);
        JPanel leftButtons = new JPanel();
        leftButtons.add(plusButton);
        leftButtons.add(minusButton);
        leftButtons.setLayout(new BoxLayout(leftButtons,BoxLayout.Y_AXIS));
        add(leftButtons,BorderLayout.WEST);

        plusButton.addActionListener(I->{
            String name = OptionPane.getString(null,"نام درس:","ساخت کلاس جدید",JOptionPane.INFORMATION_MESSAGE);
            if(name.replace(" ","").length() == 0)
            {
                return;
            }
            try{
                int max = Integer.parseInt(OptionPane.getString(null,"ظرفیت کلاس:","ساخت کلاس جدید", JOptionPane.INFORMATION_MESSAGE));
                int unit = Integer.parseInt(OptionPane.getString(null,"تعداد واحد های درس:","ساخت کلاس جدید", JOptionPane.INFORMATION_MESSAGE));
                DataBase.addClass(new Class(master,name,unit,max));
                refreshList();
            }catch (Exception e)
            {
                e.printStackTrace();
                OptionPane.showDialog(null, "ورودی باید عدد باشد",
                        "خطا", JOptionPane.ERROR_MESSAGE);
            }

        });

        minusButton.addActionListener(I->{
            Class selectedClass = (Class)classList.getSelectedValue() ;
            DataBase.removeClass(selectedClass);
            refreshList();
        });

        classList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {

                }
            }
        });
    }

    private void refreshList() {
        left.getViewport().remove(classList);
        classList = new JList(DataBase.getClasses().toArray());
        left.getViewport().add(classList);
        classList.setFont(Fonts.boldArial);
    }
}
