package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.BrandUser;

import java.util.*;

public class MemoryBrandUserRepository implements BrandUserRepository{
    private static Map<String, BrandUser> store = new HashMap<>();

    @Override
    public long count() {
        return store.size();
    }

    @Override
    public void delete(BrandUser brandUser) {
        store.remove(brandUser.getId());
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

    @Override
    public void modify(BrandUser brandUser) {
        deleteById(brandUser.getId());
        save(brandUser);
    }

    @Override
    public BrandUser save(BrandUser brandUser) {
        store.put(brandUser.getId(), brandUser);
        return brandUser;
    }

    @Override
    public Optional<BrandUser> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<BrandUser> findByEmail(String emailAddress) {
        return store.values().stream().filter(brandUser->brandUser.getEmailAddress().equals(emailAddress)).findAny();
    }

    @Override
    public Optional<BrandUser> findByPhoneNumber(String phoneNumber) {
        return store.values().stream().filter(brandUser->brandUser.getPhoneNumber().equals(phoneNumber)).findAny();
    }

    @Override
    public Optional<BrandUser> findByName(String name) {
        return store.values().stream().filter(brandUser->brandUser.getFirmName().equals(name)).findAny();
    }

    @Override
    public List<BrandUser> findAll() {
        return new ArrayList<>(store.values());
    }
}
