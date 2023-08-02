package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.repository.user.MemoryNormalUserRepository;
import basilium.basiliumspring.repository.user.NormalUserRepository;

import java.util.Optional;

public class MemoryNormalUserService implements NormalUserService{
    private final NormalUserRepository normalUserRepository = new MemoryNormalUserRepository();

    // 회원가입
    @Override
    public NormalUser join(NormalUser normalUser){
        validateDuplicateUser(normalUser);
        validateUserIdLength(normalUser);
        normalUserRepository.save(normalUser);
        return normalUser;
    }
    @Override
    public NormalUser login(NormalUser normalUser) throws IllegalStateException{
        Optional<NormalUser> user = normalUserRepository.findById(normalUser.getId());
        if (user.isEmpty())throw new IllegalStateException("사용자를 찾을 수 없습니다.");
        if(user.get().getPassword() != normalUser.getPassword())throw new IllegalStateException("사용자를 찾을 수 없습니다.");
        return normalUser;
    }

    @Override
    public void validateDuplicateUser(NormalUser normalUser){
        normalUserRepository.findById(normalUser.getId()).ifPresent(m->{throw new IllegalStateException("아이디가 중복됬습니다.");});
    }
    @Override
    public void validateUserIdLength(NormalUser normalUser){
        Optional.of(normalUser)
                .map(NormalUser::getId)
                .filter(id -> id.length() >= 6 && id.length() <= 20)
                .orElseThrow(() -> new IllegalStateException("아이디는 6자 이상 20자 이하여야 합니다."));
    }
}
