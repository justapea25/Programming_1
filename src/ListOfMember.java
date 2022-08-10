import java.io.*;
import java.util.ArrayList;

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
        String path = "member.txt";
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
        String path = "member.txt";

        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f);
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
}
