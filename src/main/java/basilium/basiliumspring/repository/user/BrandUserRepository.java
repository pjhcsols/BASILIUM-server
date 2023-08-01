package basilium.basiliumspring.repository.user;
import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.NormalUser;

import java.util.List;
import java.util.Optional;

public interface BrandUserRepository {
    long count();
    void delete(BrandUser brandUser);
    void deleteById(String id);
    void deleteAll();
    BrandUser save(BrandUser brandUser);
    Optional<BrandUser> findById(String id);
    Optional<BrandUser> findByEmail(String emailAddress);
    Optional<BrandUser> findByPhoneNumber(String phoneNumber);
    Optional<BrandUser> findByName(String name);
    List<BrandUser> findAll();
}
