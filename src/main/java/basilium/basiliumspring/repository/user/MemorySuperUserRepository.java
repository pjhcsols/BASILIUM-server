package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.SuperUser;

import java.util.*;

public class MemorySuperUserRepository implements SuperUserRepository{

    private static Map<String, SuperUser> store = new HashMap<>();

    @Override
    public long count() {
        return store.size();
    }

    @Override
    public void delete(SuperUser superUser) {
        store.remove(superUser.getId());
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
    public void modify(SuperUser superUser) {
        deleteById(superUser.getId());
        save(superUser);
    }

    @Override
    public SuperUser save(SuperUser superUser) {
        store.put(superUser.getId(), superUser);
        return superUser;
    }

    @Override
    public Optional<SuperUser> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<SuperUser> findByEmail(String emailAddress) {
        return store.values().stream().filter(superUser->superUser.getEmailAddress().equals(emailAddress)).findAny();
    }


    @Override
    public Optional<SuperUser> findByPhoneNumber(String phoneNumber) {
        return store.values().stream().filter(superUser->superUser.getPhoneNumber().equals(phoneNumber)).findAny();
    }

    @Override
    public List<SuperUser> findAll() {
        return new ArrayList<>(store.values());
    }
}
