package basilium.basiliumspring.domain.user;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User {

    @Id
    private String id;
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private Grade userGrade;

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Grade getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(Grade userGrade) {
        this.userGrade = userGrade;
    }
}
