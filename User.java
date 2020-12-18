package Model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String permission;
    public User(String username , String password , String permission) {
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public String toString() {
        return username + " ("+permission+")";
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user.username.equals(this.username);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;

    }

    public void setUsername(String username) {
        this.username = username;
    }
}
