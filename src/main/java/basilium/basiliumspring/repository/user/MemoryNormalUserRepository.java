package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.NormalUser;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryNormalUserRepository implements NormalUserRepository{

    private static Map<String, NormalUser> store = new HashMap<>();

    @Override
    public long count() {
        return store.size();
    }

    @Override
    public void delete(NormalUser normalUser) {
        store.remove(normalUser.getId());
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
    public void modify(NormalUser normalUser) {
        deleteById(normalUser.getId());
        save(normalUser);
    }

    @Override
    public NormalUser save(NormalUser normalUser) {
        store.put(normalUser.getId(), normalUser);
        return normalUser;
    }

    @Override
    public Optional<NormalUser> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<NormalUser> findByEmail(String emailAddress) {
        return store.values().stream().filter(normalUser->normalUser.getEmailAddress().equals(emailAddress)).findAny();
    }

    @Override
    public List<NormalUser> findByName(String name) {
        return store.values().stream().filter(normalUser->normalUser.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Optional<NormalUser> findByPhoneNumber(String phoneNumber) {
        return store.values().stream().filter(normalUser->normalUser.getPhoneNumber().equals(phoneNumber)).findAny();
    }

    @Override
    public List<NormalUser> findAll() {
        return new ArrayList<>(store.values());
    }
}
