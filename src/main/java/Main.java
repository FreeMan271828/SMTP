import data_access.Conn;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/test";
        String username="root";
        String password="";
        Connection connection = Conn.getConnection(url,username,password);
        System.out.println("Hello world!");
    }
}