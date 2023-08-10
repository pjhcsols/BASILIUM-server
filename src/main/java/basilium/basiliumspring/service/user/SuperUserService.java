package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.domain.user.SuperUser;
import basilium.basiliumspring.repository.user.NormalUserRepository;
import basilium.basiliumspring.repository.user.SuperUserRepository;

public class SuperUserService {

    private final SuperUserRepository superUserRepository;

    public SuperUserService(SuperUserRepository superUserRepository) {
        this.superUserRepository = superUserRepository;
    }

    public SuperUser join(SuperUser superUser){
        validateDuplicateMember(superUser);
        checkPasswordLength(superUser);
        checkStrongPassword(superUser);
        superUserRepository.save(superUser);
        return superUser;
    }

    private void validateDuplicateMember(SuperUser superUser) {
        superUserRepository.findById(superUser.getId()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    private void checkPasswordLength(SuperUser superUser) {
        if (superUser.getPassword().length() >= 8 && superUser.getPassword().length() <= 16) return;
        else throw new IllegalStateException("비밀번호는 8 ~ 16자 사이여야 합니다.");
    }
    private void checkStrongPassword(SuperUser superUser){
        String password = superUser.getPassword();
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;
        for (char c : password.toCharArray()){
            if (Character.isUpperCase(c))
                hasUpperCase = true;
            else if (Character.isLowerCase(c))
                hasLowerCase = true;
            else if ("!@#$%^&*()-_=+[]{}|;:'\",.<>/?".indexOf(c) != -1)
                hasSpecialChar = true;
        }

        if (!(hasUpperCase && hasLowerCase && hasSpecialChar))throw new IllegalStateException("비밀번호는 영문 소문자, 대문자, 특수문자를 포함해야됩니다.");
    }
}
