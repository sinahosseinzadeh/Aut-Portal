import Model.Admin;
import Model.Master;
import Model.Student;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MainPage extends Frame {
    private static MainPage instance = null;
    private static Button login;
    private static Button register;
    private static JTextField username;
    private static JPasswordField password;
    private static Image logo;
    private static Label usernameLabel;
    private static Label passwordLabel;
    private static RadioButton student;
    private static RadioButton master;
    private static RadioButton admin;
    private static ButtonGroup radioGroup;

    public MainPage() {
        super("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAll();
    }

    public static MainPage getInstance() {
        if(instance == null)
            instance = new MainPage();
        return instance;
    }


    @Override
    public void setSettings() {
        setSize(500,600);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setLayout(null);
    }

    @Override
    public void initComponents() {
        usernameLabel = new Label("نام کاربری");
        passwordLabel = new Label("رمز عبور");
        username = new JTextField();
        password = new JPasswordField();
        usernameLabel.setLabelFor(username);
        logo = new Image("logo.png");
        login = new Button("ورود");
        register = new Button("ثبت نام");
        student = new RadioButton("دانشجو");
        master = new RadioButton("استاد");
        admin = new RadioButton("ادمین");
        radioGroup = new ButtonGroup();
    }

    @Override
    public void addComponents() {
        radioGroup.add(admin);
        radioGroup.add(student);
        radioGroup.add(master);

        add(student,350,325);
        student.setSize(100,100);

        add(master,215,325);
        master.setSize(100,100);

        add(admin,60,325);
        admin.setSize(100,100);

        add(usernameLabel,350,160);
        usernameLabel.setSize(100,50);

        add(username,50,200);
        username.setSize(400,50);

        add(passwordLabel,355,250);
        passwordLabel.setSize(100,50);

        add(password,50,290);
        password.setSize(400,50);

        add(logo,20,10);
        logo.setSize(451,112);

        add(register,270,425);
        register.setSize(200,100);

        add(login,30,425);
        login.setSize(200,100);
    }

    @Override
    public void addListeners() {
        login.addActionListener(I->{
            switch (DataBase.checkPassword(username.getText(), password.getText()))
            {
                case DataBase.SUCCESS:
                    User user = DataBase.getUser(username.getText());
                    assert user != null;
                    setVisible(false);
                    if(user.getClass() == Student.class)
                        new StudentPortal((Student) user);
                    else if(user.getClass() == Admin.class)
                        new AdminPortal((Admin) user);
                    else if(user.getClass() == Master.class)
                        new MasterPortal((Master) user);
                    break;
                case DataBase.USER_NOT_FOUND:
                    OptionPane.showDialog(null, "کاربر پیدا نشد!",
                            "خطا", JOptionPane.ERROR_MESSAGE);
                    break;
                case DataBase.PASSWORD_IS_WRONG:
                    OptionPane.showDialog(null, "رمز عبور اشتباه میباشد.",
                            "خطا", JOptionPane.ERROR_MESSAGE);
            }
        });

        register.addActionListener(I->{
            if(DataBase.hasUser(username.getText()))
            {
                OptionPane.showDialog(null, "این نام کاربری وجود دارد.",
                        "خطا", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(student.isSelected())
                DataBase.addUser(new Student(username.getText(),password.getText()));
            else if(master.isSelected())
                DataBase.addUser(new Master(username.getText(),password.getText()));
            else if(admin.isSelected())
            {
                if(DataBase.hasAdmin())
                {
                    OptionPane.showDialog(null, "اجازه ثبت نام ادمین نمیباشد.",
                            "خطا", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else
                    DataBase.addUser(new Admin(username.getText(),password.getText()));
            }
            else
            {
                OptionPane.showDialog(null, "یکی از موارد دانشجو , ادمین یا استاد را انتخاب کنید.",
                        "خطا", JOptionPane.ERROR_MESSAGE);
                return;
            }

            OptionPane.showDialog(null, "ثبت نام با موفقیت انجام شد.",
                    "ثبت نام تکمیل شد", JOptionPane.INFORMATION_MESSAGE);

        });
    }
}
