package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.NormalUser;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaBrandUserRepository implements BrandUserRepository{
    private EntityManager em;

    public JpaBrandUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public long count() {
        return em.createQuery("select count(u) from BrandUser u", Long.class).getSingleResult();
    }

    @Override
    public void delete(BrandUser brandUser) {
        BrandUser result = em.find(BrandUser.class, brandUser.getId());
        if (result != null) em.remove(result);
    }

    @Override
    public void deleteById(String id) {
        BrandUser result = em.find(BrandUser.class, id);
        if (result != null) em.remove(result);
    }

    @Override
    public void deleteAll() {
        em.createQuery("delete from BrandUser").executeUpdate();
    }

    @Override
    public void modify(BrandUser brandUser) {
        BrandUser existingUser = em.find(BrandUser.class, brandUser.getId());

        if (existingUser != null) {
            existingUser.setPassword(brandUser.getPassword());
            existingUser.setUserGrade(brandUser.getUserGrade());
            existingUser.setBusinessRegistration(brandUser.getBusinessRegistration());
            existingUser.setFirmAddress(brandUser.getFirmAddress());
            existingUser.setFirmName(brandUser.getFirmName());
            existingUser.setFirmWebUrl(brandUser.getFirmWebUrl());
            existingUser.setEmailAddress(brandUser.getEmailAddress());
            existingUser.setPhoneNumber(brandUser.getPhoneNumber());
            em.merge(existingUser); // 변경 내용 저장
        }
    }

    @Override
    public BrandUser save(BrandUser brandUser) {
        em.persist(brandUser);
        return brandUser;
    }

    @Override
    public Optional<BrandUser> findById(String id) {
        BrandUser brandUser = em.find(BrandUser.class, id);
        return Optional.ofNullable(brandUser);
    }

    @Override
    public Optional<BrandUser> findByEmail(String emailAddress) {
        BrandUser brandUser = em.createQuery("select m from BrandUser m where m.emailAddress = :emailAddress", BrandUser.class).setParameter("emailAddress", emailAddress).getSingleResult();
        return Optional.ofNullable(brandUser);
    }

    @Override
    public Optional<BrandUser> findByPhoneNumber(String phoneNumber) {
        BrandUser brandUser = em.createQuery("select m from BrandUser m where m.phoneNumber = :phoneNumber", BrandUser.class).setParameter("phoneNumber", phoneNumber).getSingleResult();
        return Optional.ofNullable(brandUser);
    }

    @Override
    public Optional<BrandUser> findByName(String name) {
        BrandUser brandUser = em.createQuery("select m from BrandUser m where m.firmName = :name", BrandUser.class).setParameter("name", name).getSingleResult();
        return Optional.ofNullable(brandUser);
    }

    @Override
    public List<BrandUser> findAll() {
        return em.createQuery("select m from BrandUser m", BrandUser.class).getResultList();
    }
}
