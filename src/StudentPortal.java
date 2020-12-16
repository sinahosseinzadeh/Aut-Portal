import Model.Food;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class StudentPortal extends Portal{
    Student student;
    public StudentPortal(Student student) {
        super(student);
        this.student = student;
        addStudentComponents();
        setVisible(true);
    }

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

        addMoney.addActionListener(I->{
            new Shaparak(student,money);
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
