import Model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Admin portal
 */
public class AdminPortal extends Portal{
    private JList usersList;
    private JList foodLists;

    private JTabbedPane tabs;
    private JButton minusButton;
    private JButton plusButton;

    public AdminPortal(Admin admin) {
        super(admin);
        addAdminComponents();
        addAdminListeners();
        setVisible(true);
    }

    private void addAdminListeners() {
        plusButton.addActionListener(I->{
            if(tabs.getSelectedIndex() == 0)
            {
                String username = OptionPane.getString(null,"نام کاربری:","ثبت نام کاربر جدید",JOptionPane.INFORMATION_MESSAGE);
                if(DataBase.hasUser(username))
                {
                    OptionPane.showDialog(null, "این نام کاربری وجود دارد.",
                            "خطا", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(username.replace(" ","").length() == 0)
                {
                    return;
                }
                String password = OptionPane.getString(null,"رمز عبور:","ثبت نام کاربر جدید",JOptionPane.INFORMATION_MESSAGE);
                String[] options = { "دانشجو" , "استاد"};
                int role = JOptionPane.showOptionDialog(null,"اضافه کردن به عنوان:","ثبت نام کاربر جدید" , JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,null);
                if(role == 0)
                    DataBase.addUser(new Student(username,password));
                else
                    DataBase.addUser(new Master(username,password));
                refreshList();
            }
            else
            {
                String name = OptionPane.getString(null,"نام غذا:","اضافه کردن غذا",JOptionPane.INFORMATION_MESSAGE);
                try{
                    int price = Integer.parseInt(OptionPane.getString(null,"قیمت غذا:","اضافه کردن غذا", JOptionPane.INFORMATION_MESSAGE));
                    int month = Integer.parseInt(OptionPane.getString(null,"برای ماه:","اضافه کردن غذا", JOptionPane.INFORMATION_MESSAGE));
                    int day = Integer.parseInt(OptionPane.getString(null,"برای روز:","اضافه کردن غذا", JOptionPane.INFORMATION_MESSAGE));
                    DataBase.addFood(new Food(name,month,day,price));
                    refreshList();
                }catch (Exception e)
                {
                    OptionPane.showDialog(null, "ورودی باید عدد باشد",
                            "خطا", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        minusButton.addActionListener(I->{
            if(tabs.getSelectedIndex() == 0)
            {
                User selectedUser = (User)usersList.getSelectedValue() ;
                if(selectedUser.getClass() != Admin.class)
                {
                    DataBase.removeUser(selectedUser);
                    refreshList();
                }
            }
            else
            {
                Food selectedFood = (Food)foodLists.getSelectedValue() ;
                DataBase.removeFood(selectedFood);
                refreshList();
            }
        });
    }

    public void addAdminComponents() {
        //CENTER
        tabs = new JTabbedPane();
        usersList = new JList(DataBase.getUsers().toArray());
        JScrollPane scrollUsers = new JScrollPane(usersList);
        add(tabs,BorderLayout.CENTER);
        tabs.add("کاربران" , scrollUsers);

        foodLists = new JList(DataBase.getFoods().toArray());
        JScrollPane scrollFoods = new JScrollPane(foodLists);
        add(tabs,BorderLayout.CENTER);
        tabs.add("غذاها" , scrollFoods);

        tabs.setFont(Fonts.bTitr);
        tabs.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        usersList.setFont(Fonts.boldArial);
        foodLists.setFont(Fonts.boldArial);


        //RIGHT
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right,BoxLayout.Y_AXIS));
        minusButton = new JButton("-");
        minusButton.setFont(Fonts.bTitrLarge);
        plusButton = new JButton("+");
        plusButton.setFont(Fonts.bTitrLarge);
        right.add(Box.createRigidArea(new Dimension(10, 30)));
        right.add(plusButton);
        right.add(minusButton);
        add(right,BorderLayout.EAST);
    }

    public void refreshList(){
        usersList = new JList(DataBase.getUsers().toArray());
        JScrollPane scrollUsers = new JScrollPane(usersList);
        tabs.setComponentAt(0,scrollUsers);
        tabs.setVisible(true);
        usersList.setFont(Fonts.boldArial);

        foodLists = new JList(DataBase.getFoods().toArray());
        JScrollPane scrollFoods = new JScrollPane(foodLists);
        tabs.setComponentAt(1,scrollFoods);
        tabs.setVisible(true);
        foodLists.setFont(Fonts.boldArial);
    }
}
