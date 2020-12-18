package Model;

import java.io.Serializable;

public class Date implements Serializable {
    public int month;
    public int day;

    public Date(int month , int day) {
        this.month = month;
        this.day = day;
    }
}
