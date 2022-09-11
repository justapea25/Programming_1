package Account;

import Functions.ValidateInput;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfMember {
    private ArrayList<Member> memberList;
    public ListOfMember() {
        this.memberList = new ArrayList<>();
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }
    public void setMemberList(ArrayList<Member> listOfMember) {
        this.memberList = listOfMember;
    }
    public void addMemberToList(Member member) {
        memberList.add(member);
    }

    // Reads all member objects from member.txt and stores them in an arraylist
    public void readMembers() throws Exception{
        this.memberList.clear();
        String path = "src/files/member.txt";
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\t+");
                String memberID = data[0];
                String memberUsername = data[1];
                String memberPassword = data[2];
                String memberName = data[3];
                String memberAddress = data[4];
                Member m = new Member(memberID, memberUsername, memberPassword, memberName, memberAddress);
                this.memberList.add(m);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Write the member arraylist to member.txt
    public void writeMemberToFile() {
        String path = "src/files/member.txt";

        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            //Separates information with tabs
            for(Member member : memberList) {
                bw.write(member.getId() + "\t");
                bw.write(member.getUsername() + "\t");
                bw.write(member.getPassword() + "\t");
                bw.write(member.getName() + "\t");
                bw.write(member.getAddress() + "\t");
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Prints out details about all existing members
    public void viewAllMembers() throws Exception {
        for (Member member : this.memberList){
            member.viewMember();
        }
    }

    // Takes in user input and create a new member
    public Member register() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int count;
        String username;
        while (true) {
            count = 0;
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            for (Member member : memberList) {
                if (username.equals(member.getUsername()))
                    count ++;
            }
            if (count == 0) break;
            System.out.println("Username already exists!");
        }
        System.out.print("Enter password: ");
        String password = ValidateInput.inputPatternCheck(".{6,}", "Password must be at least 6 characters");
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        String id;
        // Generates a unique member ID
        if (memberList.size() < 1) {
            id = "M1";
        } else {
            String lastID = this.memberList.get(this.memberList.size() - 1).getId();
            id = "M" + (Integer.parseInt(lastID.substring(1)) + 1);
        }
        Member member = new Member(id, username, password, name, address);
        this.addMemberToList(member);
        return member;
    }

    // Takes in user input and validates username/password
    public Member memberLogin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            for (Member member : this.getMemberList()) {
                if (username.equals(member.getUsername()) && password.equals(member.getPassword())) {
                    return member;
                }
            }
            System.out.println("Invalid username/password. Please try again.");
        }
    }
}
