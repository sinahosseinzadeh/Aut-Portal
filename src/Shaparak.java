import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Shaparak extends Frame{

    private Label label;
    private Student student;
    private Button accept;
    private Button cancel;
    private Label price;
    private JSpinner priceText;
    private Label card;
    private JTextField cardText;
    private Label password;
    private JTextField passwordText;

    public Shaparak(Student student,Label label) {
        super("افزایش موجودی");
        this.student = student;
        this.label = label;
        setAll();
    }

    @Override
    public void setSettings() {
        setLayout(new GridLayout(4,2));
        setSize(500,350);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    @Override
    public void initComponents() {
        accept = new Button("پرداخت");
        cancel = new Button("لغو");
        price = new Label(" مبلغ");
        priceText = new JSpinner();
        card = new Label(" شماره کارت");
        cardText = new JTextField();
        password = new Label(" رمز کارت");
        passwordText = new JTextField();
    }

    @Override
    public void addComponents() {
        add(priceText);
        add(price);

        add(cardText);
        add(card);

        add(passwordText);
        add(password);

        add(cancel);
        add(accept);
    }

    @Override
    public void addListeners() {
        Frame frame = this;
        cancel.addActionListener(I->{
            frame.setVisible(false);
        });
        accept.addActionListener(I->{
            if((Integer)priceText.getValue() == 0)
            {
                OptionPane.showDialog(null, "مبلغ را وارد کنید",
                        "خطا", JOptionPane.ERROR_MESSAGE);

            }
            try{
                Integer.parseInt(cardText.getText());
                Integer.parseInt(cardText.getText());
            }
            catch (Exception e)
            {
                OptionPane.showDialog(null, "شماره کارت یا رمز نامعتبر میباشد",
                        "خطا", JOptionPane.ERROR_MESSAGE);
                return;
            }
            student.setMoney(student.getMoney() + (Integer)priceText.getValue());
            label.setText("موجودی: " + student.getMoney());
            frame.setVisible(false);
        });

    }
}
