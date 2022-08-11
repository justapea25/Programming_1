package Account;

import java.io.Serializable;
import java.util.Scanner;

public class Admin implements Serializable {
    private String username;
    private String password;
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
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
    public static Admin adminLogin() throws Exception{
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter passowrd: ");
            String password = scanner.nextLine();
            ListOfAdmin listOfAdmin = new ListOfAdmin();
            listOfAdmin.readAdmins();
            for (Admin admin : listOfAdmin.getAdminList()){
                if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())){
                    return admin;
                }
            }
            System.out.println("Invalid username/password. Please try again.");
        }

    }
}
