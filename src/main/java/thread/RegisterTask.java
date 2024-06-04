package thread;

import data_object.User;
import service.RegisterService;

import java.sql.Connection;
import java.util.Scanner;

public class RegisterTask {

    private static final Scanner scanner = new Scanner(System.in);

    public static void register(Connection coon){
        User user = new User();
        System.out.println("请输入姓名");
        user.setName(scanner.next());
        System.out.println("请输入邮箱");
        user.setEmail(scanner.next());
        System.out.println("请输入密码");
        user.setPassword(scanner.next());
        System.out.println("请输授权码");
        user.setAuthorizationCode(scanner.next());
        RegisterService registerService = new RegisterService(user, coon);
        try {
            registerService.register();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
