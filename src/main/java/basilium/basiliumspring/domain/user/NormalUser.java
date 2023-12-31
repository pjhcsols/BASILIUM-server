package basilium.basiliumspring.domain.user;

import javax.persistence.Entity;

@Entity
public class NormalUser extends User{
    private String name;
    private Long age;
    private String address;

    public NormalUser() {
        super();
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
