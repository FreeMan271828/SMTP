package service;

import data_access.Get;
import data_object.User;

import java.sql.Connection;
import java.sql.SQLException;

public class LogIn {
    public static User LoginByEmail(String email, String password, Connection coon) throws SQLException {
        return Get.getUserByEmail(email,password,coon);
    }
}
