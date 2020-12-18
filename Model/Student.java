package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable {
    private int money;
    private ArrayList<Class> classes;
    private Integer tempScore = null;
    public Student(String username , String password) {
        super(username,password,"دانشجو");
        classes = new ArrayList<>();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Class> getClasses() {
        for(Class c : classes)
        {
            c.setTempScore(this);
        }
        return classes;
    }

    public void setTempScore(Integer value) {
        tempScore = value;
    }

    @Override
    public String toString() {
        if(tempScore!= null)
            return getUsername() + " (" + tempScore + ")";
        return super.toString();
    }
}
