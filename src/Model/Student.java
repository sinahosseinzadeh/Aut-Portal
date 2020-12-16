package Model;

import java.io.Serializable;

public class Student extends User implements Serializable {
    private int money;
    public Student(String username , String password) {
        super(username,password,"دانشجو");
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
