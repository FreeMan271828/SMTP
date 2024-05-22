package data_access;

import data_object.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Get {
    /**
     * 获取所有的角色
     */
    public List<User>getUsers(Connection coon) throws SQLException {
        Statement stmt = coon.createStatement();
        String sql = "select * from user";
        ResultSet rs = stmt.executeQuery(sql);
        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            users.add(user);
        }
        return users;
    }
}
