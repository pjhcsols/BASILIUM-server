package basilium.basiliumspring.repository.user;
import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.NormalUser;

import java.util.List;
import java.util.Optional;

public interface NormalUserRepository {
    long count();
    void delete(NormalUser normalUser);
    void deleteById(String id);
    void deleteAll();
    NormalUser save(NormalUser normalUser);
    Optional<NormalUser> findById(String id);
    Optional<NormalUser> findByEmail(String emailAddress);
    Optional<NormalUser> findByName(String name);
    Optional<NormalUser> findByPhoneNumber(String phoneNumber);
    List<NormalUser> findAll();
}