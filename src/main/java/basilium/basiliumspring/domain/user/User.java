package basilium.basiliumspring.domain.user;

public class User {
    private String id;
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private Grade userGrade;

    public User(String id, String password, String emailAddress, String phoneNumber, Grade userGrade) {
        this.id = id;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.userGrade = userGrade;
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
