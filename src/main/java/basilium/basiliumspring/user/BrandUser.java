package basilium.basiliumspring.user;

public class BrandUser extends User{

    private String firmName;
    private String firmAddress;
    private String businessRegistration;
    private String firmWebUrl;

    public BrandUser(String id, String password, String emailAddress, String phoneNumber, Grade userGrade, String firmName, String firmAddress, String businessRegistration, String firmWebUrl) {
        super(id, password, emailAddress, phoneNumber, userGrade);
        this.firmName = firmName;
        this.firmAddress = firmAddress;
        this.businessRegistration = businessRegistration;
        this.firmWebUrl = firmWebUrl;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmAddress() {
        return firmAddress;
    }

    public void setFirmAddress(String firmAddress) {
        this.firmAddress = firmAddress;
    }

    public String getBusinessRegistration() {
        return businessRegistration;
    }

    public void setBusinessRegistration(String businessRegistration) {
        this.businessRegistration = businessRegistration;
    }

    public String getFirmWebUrl() {
        return firmWebUrl;
    }

    public void setFirmWebUrl(String firmWebUrl) {
        this.firmWebUrl = firmWebUrl;
    }
}
