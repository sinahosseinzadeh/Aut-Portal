package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Class implements Serializable {
    private final int max;
    private final String name;
    private Master master;
    private HashMap<Student,Integer> grades;
    int unit;
    private Integer tempScore = null;

    public Class(Master master,String name , int unit , int max) {
        grades = new HashMap<>();
        this.master = master;
        this.name = name;
        this.unit = unit;
        this.max = max;
    }

    @Override
    public String toString() {
        if(tempScore == null)
            return name + " | Units: "  + unit + " | Number of Students: " + grades.entrySet().size()+"/"+max;
        else
            return name + " | Score: "  + tempScore + " | Number of Students: " + grades.entrySet().size()+"/"+max;

    }

    public void addStudent(Student student) {
        grades.put(student,0);
    }

    public Student[] getStudentsArray() {
        for(Map.Entry<Student,Integer> entry : grades.entrySet())
        {
            entry.getKey().setTempScore(entry.getValue());
        }
        return grades.keySet().toArray(new Student[0]);
    }

    public boolean isFull() {
        return grades.entrySet().size() == max;
    }

    public void setGrade(Student student , int grade) {
        grades.replace(student,grade);
    }

    public void setTempScore(Student student) {
        tempScore = grades.get(student);
    }
}
