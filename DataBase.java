import Model.*;
import Model.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class DataBase {
    public static final int SUCCESS = 0;
    public static final int USER_NOT_FOUND = 1;
    public static final int PASSWORD_IS_WRONG = 2;
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Food> foods = new ArrayList<>();
    private static ArrayList<Class> classes = new ArrayList<>();

    /**
     * Load all files
     */
    public static void load()
    {
        load("users.db");
        load("foods.db");
        load("classes.db");
    }

    /**
     * Load certain file
     * @param fileName file name
     */
    private static void load(String fileName) {
        File f = new File(fileName);
        if(f.exists() && !f.isDirectory()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);
                if(fileName.equals("users.db"))
                    users = (ArrayList<User>) objectInput.readObject();
                else if(fileName.equals("foods.db"))
                    foods = (ArrayList<Food>) objectInput.readObject();
                else if(fileName.equals("classes.db"))
                    classes = (ArrayList<Class>) objectInput.readObject();
                objectInput.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * add a new user to database
     * @param user adding user
     */
    public static void addUser(User user)
    {
        users.add(user);
        saveUsers();
    }

    /**
     * save users to "users.db" file
     */
    public static void saveUsers() {
        try{
            FileOutputStream fileOut = new FileOutputStream("users.db");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(users);
            objectOut.close();
        }catch (Exception ignored){}
    }

    /**
     * save foods to "food.db" file
     */
    public static void saveFoods() {
        try{
            FileOutputStream fileOut = new FileOutputStream("foods.db");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(foods);
            objectOut.close();
        }catch (Exception ignored){}
    }

    /**
     * save classes to "classes.db" file
     */
    public static void saveClasses() {
        try{
            FileOutputStream fileOut = new FileOutputStream("classes.db");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(classes);
            objectOut.close();
        }catch (Exception ignored){}
    }

    /**
     * check password is correct or not for login
     * @param username username for check
     * @param password password for check
     * @return password is match or not
     */
    public static int checkPassword(String username,String password)
    {
        for(User user : users)
        {
            if(user.getUsername().toLowerCase().equals(username.toLowerCase()))
            {
                if(user.getPassword().equals(password))
                    return SUCCESS;
                else
                    return PASSWORD_IS_WRONG;
            }
        }
        return USER_NOT_FOUND;
    }

    /**
     * get a user by it's username
     * @param username username
     * @return user
     */
    public static User getUser(String username)
    {
        for(User user : users)
        {
            if(user.getUsername().toLowerCase().equals(username.toLowerCase()))
            {
                return user;
            }
        }
        return null;
    }

    /**
     * return if users has an admin
     * @return
     */
    public static boolean hasAdmin() {
        for (User user : users)
        {
            if(user.getClass() == Admin.class)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * return if user exists or not
     * @param username searching user
     * @return exists?
     */
    public static boolean hasUser(String username) {
        for (User user : users) {
            if(user.getUsername().toLowerCase().equals(username.toLowerCase()))
                return true;
        }
        return false;
    }

    /**
     * get all users in database
     * @return all users
     */
    public static ArrayList<User> getUsers() {
        for(User user : users)
        {
            if(user.getClass() == Student.class)
            {
                Student student = (Student) user;
                student.setTempScore(null);
            }
        }
        return users;
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static ArrayList<Food> getFoods() {
        return foods;
    }

    public static void addFood(Food food)
    {
        foods.add(food);
        saveFoods();
    }

    public static void removeFood(Food food) {
        foods.remove(food);
        saveFoods();
    }

    public static ArrayList<Class> getClasses() {
        for(Class c : classes)
            c.setTempScore(null);
        return classes;
    }

    public static void removeClass(Class c) {
        classes.remove(c);
        saveClasses();
    }

    public static void addClass(Class c) {
        classes.add(c);
        saveClasses();
    }
}
