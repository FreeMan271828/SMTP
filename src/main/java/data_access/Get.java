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
    public static List<User>getUsers(Connection coon) throws SQLException {
        Statement stmt = coon.createStatement();
        String sql = "select * from user";
        return getUserBySql(stmt,sql);
    }

    /**
     * 根据Id获取User
     */
    public static User getUserById(String id,Connection coon) throws SQLException {
        Statement stmt =  coon.createStatement();
        String sql = "select * from user where id="+id;
        return  getUserBySql(stmt,sql).getFirst();
    }

    /**
     * 根据其余信息获取User
     */
    public static User getUserByEmail(String email,String password,Connection coon) throws SQLException {
        Statement stmt =  coon.createStatement();
        String sql = "select * from user where email='" + email + "' and password='" + password + "';";
        return getUserBySql(stmt,sql).getFirst();
    }

    /**
     * 基本方法
     * 根据sql查询语句返回对应的User
     */
    public static List<User> getUserBySql(Statement stmt, String sql) throws SQLException {
        List<User> users;
        try (ResultSet rs = stmt.executeQuery(sql)) {
            users = new ArrayList<>();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAuthorizationCode(rs.getString("authorizationCode"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        }
        return users;
    }
}
