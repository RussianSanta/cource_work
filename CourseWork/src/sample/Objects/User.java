package sample.Objects;

public class User {
    private String name;
    private String login;
    private String password;
    private String accessProfile;

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAccessProfile() {
        return accessProfile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccessProfile(String accessProfile) {
        this.accessProfile = accessProfile;
    }
}
