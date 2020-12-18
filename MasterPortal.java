import Model.*;
import Model.Class;
import sun.plugin.javascript.JSContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.server.ExportException;

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
        setSize(1000,600);
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
        right = new JScrollPane();
        panel.add(left);
        panel.add(right);
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
            if(name == null || name.replace(" ","").length() == 0)
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
                    Class c = (Class)classList.getSelectedValue();
                    Student[] students = c.getStudentsArray();
                    JList studentList = new JList(students);
                    try{
                        right.getViewport().remove(0);
                    }
                    catch (Exception ignored){}
                    studentList.setFont(Fonts.boldArial);
                    right.getViewport().add(studentList);
                    studentList.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            if (evt.getClickCount() == 2) {
                                Student student = (Student)studentList.getSelectedValue();
                                try{
                                    int grade = Integer.parseInt(OptionPane.getString(null,"نمره دانشجو را وارد کنید:","ثبت نمره جدید", JOptionPane.INFORMATION_MESSAGE));
                                    if(grade >= 0 && grade <= 20)
                                    {
                                        c.setGrade(student,grade);
                                        Student[] students = c.getStudentsArray();
                                        JList studentList = new JList(students);
                                        try{
                                            right.getViewport().remove(0);
                                        }
                                        catch (Exception ignored){}
                                        studentList.setFont(Fonts.boldArial);
                                        right.getViewport().add(studentList);
                                    }
                                    else
                                    {
                                        OptionPane.showDialog(null, "نمره باید بین 0 تا 20 باشد",
                                                "خطا", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                catch (Exception e)
                                {
                                    OptionPane.showDialog(null, "ورودی باید عدد باشد",
                                            "خطا", JOptionPane.ERROR_MESSAGE);
                                }


                            }
                        }
                    });
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
