package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.NormalUser;

public interface NormalUserService {
    public NormalUser join(NormalUser normalUser);
    public NormalUser login(NormalUser normalUser) throws IllegalStateException;

    public void validateDuplicateUser(NormalUser normalUser);
    public void validateUserIdLength(NormalUser normalUser);

}
