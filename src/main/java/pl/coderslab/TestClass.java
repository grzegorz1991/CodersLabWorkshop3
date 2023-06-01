package pl.coderslab;

import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

public class TestClass {
    public static void main(String[] args) {
        System.out.println("Creating new user");
        UserDao userDao = new UserDao();
        userDao.addUser(new User(1, "email@mail.com", "UserName", "pass"));
    }
}
