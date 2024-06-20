package service;

import data_access.Get;
import data_object.Email;
import data_object.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final User user;

    private final Connection connection;

    public UserService(User user,Connection connection) {
        this.user = user;
        this.connection = connection;
    }

    public User getUserById() throws SQLException {
        return Get.getUserById(user.getId(),connection);
    }

    public List<Email> getEmails() throws SQLException {
        return Get.GetSendEmails(user,connection);
    }
}
