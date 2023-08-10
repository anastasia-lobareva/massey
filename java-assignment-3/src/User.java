public class User {
    private String role;
    private String userName;
    private String password;

    public User(String role, String userName, String password) {
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
