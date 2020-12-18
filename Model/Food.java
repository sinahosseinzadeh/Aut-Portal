package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Food implements Serializable {
    private String name;
    private int price;
    private Date date;
    private ArrayList<Student> students;

    public Food(String name , int month , int day , int price) {
        this.price = price;
        this.name = name;
        this.date = new Date(month,day);
        students = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name + "(" + price + "$) (" + date.month + "/" + date.day+")";
    }

    public int getPrice() {
        return price;
    }

    public boolean reservedBy(Student student) {
        return (students.contains(student));
    }

    public void reserve(Student student) {
        students.add(student);
    }
}