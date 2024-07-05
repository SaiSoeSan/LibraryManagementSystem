public class Member extends Person {

    private String memberId;

    public Member(String firstName, String lastName, Address address, String phoneNumber,String memberId) {
        super(firstName, lastName, address, phoneNumber);
        this.memberId = memberId;
    }
}
