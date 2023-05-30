package xyz.lhweb.furns.bean;

/**
 * 用户表
 *
 * @author 罗汉
 * @date 2023/05/30
 */
public class User {
    private Integer id; // id
    private String username; // 用户名
    private String password; // 密码
    private String email; // 邮箱
    private String hobbys; // 兴趣爱好
    public User() {
    }

    public User(Integer id, String username, String password, String email, String hobbys) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.hobbys = hobbys;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobbys() {
        return hobbys;
    }

    public void setHobbys(String hobbys) {
        this.hobbys = hobbys;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", hobbys='" + hobbys + '\'' +
                '}';
    }

}
