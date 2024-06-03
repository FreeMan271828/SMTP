package data_access;

import data_object.Email;
import data_object.User;

import java.sql.*;

/**
 * 数据层删除操作
 */
public class Drop {

    /**
     * @return 返回删除的用户数目
     * @param user 传入用户属性（不一定全面），根据存在的用户属性删除用户
     * 当用户id存在时，直接判断id。当用户id不存在时，判断用户姓名和密码
     * @param coon 数据库连接信息
     */
    public int dropUser(User user, Connection coon) throws SQLException {
        int count=0;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            if(String.valueOf(user.getId()).equals("")){
                String sql = "delete from user where name = ? and password = ?";
                stmt= coon.prepareStatement(sql);
                stmt.setString(1,user.getName());
                stmt.setString(2,user.getPassword());
                result = stmt.executeQuery();
                while(result.next()){
                    count++;
                }
                return count;
            }else{
                String sql = "delete from user where id = ?";
                stmt= coon.prepareStatement(sql);
                stmt.setString(1,user.getId());
                result = stmt.executeQuery();
                while(result.next()){
                    count++;
                }
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(stmt!=null){
                stmt.close();
            }
            if(result!=null){
                result.close();
            }
        }
        return 0;
    }

    /**
     * @return 返回删除的用户数目
     * @param email 传入用户属性（不一定全面），根据存在的用户属性删除用户
     *              当emailId存在时，直接判断emailId。否则判断subject和content
     * @param coon 数据库连接信息
     */
    public int dropEmail(Email email, Connection coon) throws SQLException{
        int count=0;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            if(String.valueOf(email.getId()).equals("")){
                String sql = "delete from email where subject = ? and content = ?";
                stmt= coon.prepareStatement(sql);
                stmt.setString(1,email.getSubject());
                stmt.setString(2,email.getContent());
                result = stmt.executeQuery();
                while(result.next()){
                    count++;
                }
                return count;
            }else{
                String sql = "delete from email where id = ?";
                stmt= coon.prepareStatement(sql);
                stmt.setString(1,email.getId());
                result = stmt.executeQuery();
                while(result.next()){
                    count++;
                }
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(stmt!=null){
                stmt.close();
            }
            if(result!=null){
                result.close();
            }
        }
        return 0;
    }
}
