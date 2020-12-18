package Model;

import java.io.Serializable;

public class Master extends User implements Serializable {
    public Master(String username , String password) {
        super(username,password,"استاد");
    }
}
