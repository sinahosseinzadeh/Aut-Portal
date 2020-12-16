import Model.User;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public abstract class Portal extends Frame{
    private static User user;
    static JMenuBar topMenu;
    private static JMenu info;
    private static JMenuItem role;
    private static JMenuItem logout;
    private static JMenu settings;
    private static JMenuItem changeUsername;
    private static JMenuItem changePassword;

    public Portal(User user) {
        super("پرتال");
        Portal.user = user;
        setAll();
    }


    @Override
    public void setSettings() {
        setLayout(new BorderLayout());
        setSize(800,500);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void initComponents() {
        topMenu = new JMenuBar();
        info = new JMenu("حساب کاربری");
        role = new JMenuItem("سطح دسترسی: "+ user.getPermission());
        info.add(role);
        logout = new JMenuItem("خروج از حساب کاربری"); info.add(logout);
        topMenu.add(info);
        settings = new JMenu("تنظیمات");
        changeUsername = new JMenuItem("تغییر نام کاربری"); settings.add(changeUsername);
        changePassword = new JMenuItem("تغییر رمز عبور"); settings.add(changePassword);
        topMenu.add(settings);
        Field[] fields = Portal.class.getDeclaredFields();
        for(Field f : fields) {
            try{
                JComponent component = (JComponent) f.get(Portal.class);
                component.setFont(Fonts.bTitr);
                component.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            } catch (Exception ignored) {
            }
        }
        info.setFont(Fonts.bTitrLarge);
        settings.setFont(Fonts.bTitrLarge);
    }

    @Override
    public void addComponents() {
        add(topMenu,BorderLayout.NORTH);

    }

    @Override
    public void addListeners() {
        Portal portal = this;
        logout.addActionListener(I->{
            portal.setVisible(false);
            MainPage.getInstance().setVisible(true);
        });
        changePassword.addActionListener(I->{
            String oldPassword = OptionPane.getString(null,"رمز عبور فعلی:","تغییر رمز",JOptionPane.INFORMATION_MESSAGE);
            if(!user.getPassword().equals(oldPassword)) {
                OptionPane.showDialog(null , "رمز وارد شده اشتباه میباشد." ,
                        "خطا" , JOptionPane.ERROR_MESSAGE);
                return;
            }
            String newPassword = OptionPane.getString(null,"رمز عبور جدید:","تغییر رمز",JOptionPane.INFORMATION_MESSAGE);
            if(newPassword.length() != 0)
            {
                user.setPassword(newPassword);
                DataBase.saveUsers();
            }
        });
        changeUsername.addActionListener(I->{
            String username = OptionPane.getString(null,"نام کاربری:","تغییر نام کاربری",JOptionPane.INFORMATION_MESSAGE);
            if(DataBase.hasUser(username))
            {
                OptionPane.showDialog(null, "این نام کاربری وجود دارد.",
                        "خطا", JOptionPane.ERROR_MESSAGE);
                return;
            }
            user.setUsername(username);
            DataBase.saveUsers();
        });

    }


}
