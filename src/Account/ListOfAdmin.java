package Account;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfAdmin {
    private ArrayList<Admin> adminList;
    public ListOfAdmin() {
        this.adminList = new ArrayList<>();
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }
    public void setAdminList(ArrayList<Admin> listOfAdmin) {
        this.adminList = listOfAdmin;
    }
    //Reads all admin objects from admin.txt and stores them in an arraylist
    public void readAdmins() {
        String path = "src/files/admin.txt";
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\t+");
                String adminUsername = data[0];
                String adminPassword = data[1];
                Admin admin = new Admin(adminUsername, adminPassword);
                this.adminList.add(admin);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //Takes in user input and validates username/password
    public Admin adminLogin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            for (Admin admin : this.getAdminList()){
                if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())){
                    return admin;
                }
            }
            System.out.println("Invalid username/password. Please try again.");
        }
    }
}
