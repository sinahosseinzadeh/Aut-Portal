package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Class implements Serializable {
    private final int max;
    private final String name;
    private Master master;
    private HashMap<Student,Integer> grades;
    int unit;

    public Class(Master master,String name , int unit , int max) {
        grades = new HashMap<>();
        this.master = master;
        this.name = name;
        this.unit = unit;
        this.max = max;
    }

    @Override
    public String toString() {
        return name + " | Units: "  + unit + " | Number of Students: " + grades.entrySet().size()+"/"+max;

    }
}
