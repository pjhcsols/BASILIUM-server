package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.repository.user.NormalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NormalUserService {
    private final NormalUserRepository normalUserRepository;

    @Autowired
    public NormalUserService(NormalUserRepository normalUserRepository) {
        this.normalUserRepository = normalUserRepository;
    }

    public boolean join(NormalUser normalUser){
        try{
            validateDuplicateMember(normalUser);
            checkPasswordLength(normalUser);
            checkStrongPassword(normalUser);
        }
        catch (IllegalStateException e){
            System.out.println(e);
            return false;
        }
        normalUserRepository.save(normalUser);
        return true;
    }

    private void validateDuplicateMember(NormalUser normalUser) {
        normalUserRepository.findById(normalUser.getId()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    private void checkPasswordLength(NormalUser normalUser) {
        if (normalUser.getPassword().length() >= 8 && normalUser.getPassword().length() <= 16) return;
        else throw new IllegalStateException("비밀번호는 8 ~ 16자 사이여야 합니다.");
    }
    private void checkStrongPassword(NormalUser normalUser){
        String password = normalUser.getPassword();
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
