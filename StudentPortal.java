import Model.Food;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class StudentPortal extends Portal{
    Student student;

    /**
     * constructor for new portal frame for a student
     * @param student who login
     */
    public StudentPortal(Student student) {
        super(student);
        this.student = student;
        addStudentComponents();
        setVisible(true);
    }

    /**
     * Add additional components for student portal and set settings
     */
    private void addStudentComponents() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(Fonts.bTitr);
        tabs.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JPanel food = new JPanel();
        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        moneyPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        food.setLayout(new BorderLayout());
        Label money = new Label("موجودی: " + student.getMoney());
        food.add(moneyPanel,BorderLayout.NORTH);
        moneyPanel.add(money);
        Button addMoney = new Button("افزایش موجودی");
        addMoney.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        moneyPanel.add(addMoney);
        JList foods = new JList(DataBase.getFoods().toArray());;
        JScrollPane foodScroll = new JScrollPane(foods);
        food.add(foodScroll,BorderLayout.CENTER);
        tabs.add(food,"رزرو غذا");
        foods.setFont(Fonts.boldArial);
        add(tabs);


        JPanel classes = new JPanel();
        classes.setLayout(new GridLayout(2,1));
        JList classLists = new JList(DataBase.getClasses().toArray());
        classLists.setFont(Fonts.boldArial);
        JScrollPane scrollClasses = new JScrollPane(classLists);
        classes.add(scrollClasses);

        JList myClasses = new JList(student.getClasses().toArray());
        myClasses.setFont(Fonts.boldArial);
        JScrollPane scrollMyClasses = new JScrollPane(myClasses);

        classes.add(scrollMyClasses);
        tabs.add(classes,"کلاس ها");

        addMoney.addActionListener(I->{
            new Shaparak(student,money);
        });
        classLists.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Model.Class c = (Model.Class) classLists.getSelectedValue();
                    if(student.getClasses().contains(c))
                    {
                        OptionPane.showDialog(null, "این واحد قبلا انتخاب شده", "خطا", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(c.isFull())
                    {
                        OptionPane.showDialog(null, "کلاس انتخاب شده پر میباشد", "خطا", JOptionPane.ERROR_MESSAGE);

                    }
                    else
                    {
                        OptionPane.showDialog(null, "درس با موفقیت انتخاب شد", "انتخاب واحد", JOptionPane.INFORMATION_MESSAGE);
                        student.getClasses().add(c);
                        c.addStudent(student);
                        scrollMyClasses.getViewport().remove(myClasses);
                        JList newList = new JList(student.getClasses().toArray());
                        scrollMyClasses.getViewport().add(newList);
                        newList.setFont(Fonts.boldArial);
                        DataBase.saveUsers();
                    }

                }
            }
        });
        foods.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Food food = (Food) foods.getSelectedValue();
                    if(food.reservedBy(student))
                    {
                        OptionPane.showDialog(null, "غذا رزرو شده است", "خطا", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(student.getMoney() >= food.getPrice())
                    {
                        OptionPane.showDialog(null, "غذا با موفقیت رزرو شد", "رزرو غذا", JOptionPane.INFORMATION_MESSAGE);
                        student.setMoney(student.getMoney() - food.getPrice());
                        money.setText("موجودی: " + student.getMoney());
                        food.reserve(student);
                    }
                    else
                    {
                        OptionPane.showDialog(null, "موجودی کافی نیست",
                                "خطا", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });

    }

}
