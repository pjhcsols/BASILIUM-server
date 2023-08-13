package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.domain.user.SuperUser;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaSuperUserRepository implements SuperUserRepository{
    private final EntityManager em;

    public JpaSuperUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public long count() {
        return em.createQuery("select count(u) from SuperUser u", Long.class).getSingleResult();
    }

    @Override
    public void delete(SuperUser superUser) {
        SuperUser result = em.find(SuperUser.class, superUser.getId());
        if (result != null) em.remove(result);
    }

    @Override
    public void deleteById(String id) {
        SuperUser result = em.find(SuperUser.class, id);
        if (result != null) em.remove(result);
    }

    @Override
    public void deleteAll() {
        em.createQuery("delete from SuperUser").executeUpdate();
    }

    @Override
    public void modify(SuperUser superUser) {
        SuperUser existingUser = em.find(SuperUser.class, superUser.getId());

        if (existingUser != null) {
            existingUser.setPassword(superUser.getPassword());
            existingUser.setUserGrade(superUser.getUserGrade());
            existingUser.setPhoneNumber(superUser.getPhoneNumber());
            existingUser.setEmailAddress(superUser.getEmailAddress());
            em.merge(existingUser); // 변경 내용 저장
        }

    }

    @Override
    public SuperUser save(SuperUser superUser) {
        em.persist(superUser);
        return superUser;
    }

    @Override
    public Optional<SuperUser> findById(String id) {
        SuperUser superUser = em.find(SuperUser.class, id);
        return Optional.ofNullable(superUser);
    }

    @Override
    public Optional<SuperUser> findByEmail(String emailAddress) {
        SuperUser superUser = em.createQuery("select m from SuperUser m where m.emailAddress = :emailAddress", SuperUser.class).setParameter("emailAddress", emailAddress).getSingleResult();
        return Optional.ofNullable(superUser);
    }

    @Override
    public Optional<SuperUser> findByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(em.createQuery("select m from SuperUser m where m.phoneNumber = :phoneNumber", SuperUser.class).setParameter("phoneNumber", phoneNumber).getSingleResult());
    }

    @Override
    public List<SuperUser> findAll() {
        return em.createQuery("select m from SuperUser m", SuperUser.class).getResultList();
    }
}
