import java.util.ArrayList;

public class ListOfMember {
    private ArrayList<Member> listOfMember;
    public ListOfMember() {
        this.listOfMember = new ArrayList<Member>();
    }

    public ArrayList<Member> getListOfMember() {
        return listOfMember;
    }
    public void setListOfMember(ArrayList<Member> listOfMember) {
        this.listOfMember = listOfMember;
    }
    public void addMemberToList(Member member) {
        listOfMember.add(member);
    }
    public void readMembers() {

    }
}
