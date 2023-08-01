package basilium.basiliumspring.domain.user;

public class SuperUser extends User{
    public SuperUser(String id, String password, String emailAddress, String phoneNumber, Grade userGrade) {
        super(id, password, emailAddress, phoneNumber, userGrade);
    }
}
