package models;

public class adminDashboard extends user {
    public adminDashboard(String role, String password) {
        super(role, password);
    }

    @Override
    public void accessDashboard(){
        System.out.println("Welcome: " + getRole());

        String menu = "";
    }

}
