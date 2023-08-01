package basilium.basiliumspring.repository.user;
import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.NormalUser;

import java.util.List;
import java.util.Optional;

public interface NormalUserRepository {
    NormalUser save(NormalUser normalUser);
    Optional<NormalUser> findById(String id);
    Optional<NormalUser> findByEmail(String id);
    Optional<NormalUser> findByName(String Name);
    Optional<NormalUser> findByPhoneNumber(String id);
    List<NormalUser> findAll();
}
