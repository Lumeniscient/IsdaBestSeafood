package models;

abstract public class user {
    private String role;
    private String password;

    user(String role, String password){
        this.role = role;
        this.password = password;
    }

    public String getRole(){
        return role;
    }

    public String getPassword(){
        return password;
    }

    abstract void accessDashboard();

}
