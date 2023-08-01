package basilium.basiliumspring.repository.user;
import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.SuperUser;

import java.util.List;
import java.util.Optional;

public interface SuperUserRepository {
    SuperUser save(SuperUser superUser);
    Optional<SuperUser> findById(String id);
    Optional<SuperUser> findByEmail(String emailAddress);
    Optional<SuperUser> findByName(String name);
    Optional<SuperUser> findByPhoneNumber(String phoneNumber);
    List<SuperUser> findAll();
}
