package Account;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfMember {
    private ArrayList<Member> memberList;
    public ListOfMember() {
        this.memberList = new ArrayList<Member>();
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
    public void readMembers() throws Exception{
        this.memberList.clear();
        String path = "src/files/member.txt";
        try {
            FileReader fr = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String data[] = line.split("\\t+");
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
    public void writeMemberToFile() throws IOException, ClassNotFoundException {
        String path = "src/files/member.txt";

        try {
            FileWriter fw = new FileWriter(new File(path));
            BufferedWriter bw = new BufferedWriter(fw);
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
    public void viewAllMembers(){
        for (Member member : this.memberList){
            member.viewMember();
        }
    }
    public Member register() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter passowrd: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        String id = "M" + (this.getMemberList().size()+1);
        Member member = new Member(id, username, password, name, address);
        this.addMemberToList(member);
        this.writeMemberToFile();
        return member;
    }
    public Member memberLogin() throws Exception{
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
