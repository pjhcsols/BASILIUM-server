package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.JoinStatus;
import basilium.basiliumspring.domain.user.LoginStatus;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.repository.user.BrandUserRepository;
import basilium.basiliumspring.repository.user.NormalUserRepository;
import basilium.basiliumspring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class BrandUserService {
    private final BrandUserRepository brandUserRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;

    public BrandUserService(BrandUserRepository brandUserRepository) {
        this.brandUserRepository = brandUserRepository;
    }

    public JoinStatus join(BrandUser brandUser){
        try{
            validateDuplicateMember(brandUser);
        }
        catch (IllegalStateException e){
            System.out.println(e);
            return JoinStatus.DUPLICATE;
        }
        try{
            checkPasswordLength(brandUser);
        }
        catch (IllegalStateException e){
            System.out.println(e);
            return JoinStatus.INVALID_PASSWORD_LENGTH;
        }
        try{
            checkStrongPassword(brandUser);
        }
        catch (IllegalStateException e)
        {
            System.out.println(e);
            return JoinStatus.INVALID_PASSWORD_STRENGTH;
        }
        brandUserRepository.save(brandUser);
        return JoinStatus.SUCCESS;
    }

    public LoginStatus login(String userId, String userPassword){
        Optional<BrandUser> tar = brandUserRepository.findById(userId);
        if (tar.get()== null || !(tar.get().getPassword().equals(userPassword))){
            return LoginStatus.FAIL;
        }
        return LoginStatus.SUCCESS;

    }
    public String afterSuccessLogin(String userId){
        return JwtUtil.createJwt(userId, "brand", secretKey, expiredMs);
    }

    private void validateDuplicateMember(BrandUser brandUser) {
        brandUserRepository.findById(brandUser.getId()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    private void checkPasswordLength(BrandUser brandUser) {
        if (brandUser.getPassword().length() >= 8 && brandUser.getPassword().length() <= 16) return;
        else throw new IllegalStateException("비밀번호는 8 ~ 16자 사이여야 합니다.");
    }
    private void checkStrongPassword(BrandUser brandUser){
        String password = brandUser.getPassword();
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
