package basilium.basiliumspring.user;

public class NormalUser extends User{
    private String name;
    private Long age;
    private String address;

    public NormalUser(String id, String password, String emailAddress, String phoneNumber, Grade userGrade, String name, Long age, String address) {
        super(id, password, emailAddress, phoneNumber, userGrade);
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
