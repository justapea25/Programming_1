package Account;

import java.io.*;
import java.util.ArrayList;

public class ListOfAdmin {
    private ArrayList<Admin> adminList;
    public ListOfAdmin() {
        this.adminList = new ArrayList<Admin>();
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }
    public void setAdminList(ArrayList<Admin> listOfAdmin) {
        this.adminList = adminList;
    }
    public void readAdmins() throws Exception{
        String path = "src/files/admin.txt";
        try {
            FileReader fr = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String data[] = line.split("\\t+");
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
    public static void main(String[] args) {


    }
}
