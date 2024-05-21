package data_object;

/**
 * 连接数据库的对象
 */
public class DataBaseConn {
    //数据源、数据库地址
    private String url;
    //登录用户
    private String name;
    //登录密码
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
