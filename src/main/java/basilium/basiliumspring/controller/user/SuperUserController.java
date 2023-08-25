package basilium.basiliumspring.controller.user;

import basilium.basiliumspring.domain.user.*;
import basilium.basiliumspring.service.user.BrandUserService;
import basilium.basiliumspring.service.user.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/superUser")
public class SuperUserController {
    private final SuperUserService superUserService;

    @Autowired
    public SuperUserController(SuperUserService superUserService) {
        this.superUserService = superUserService;
    }

    @PostMapping(value = "/v1/superuser/signup")
    public ResponseEntity<String> createNormalUser(@RequestBody SuperUser superUser) {
        JoinStatus result = superUserService.join(superUser);
        return new ResponseEntity<>(result.getMessage(), result.getStatus());
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginNormalUser(@RequestBody LoginRequest loginRequest){
        LoginStatus loginResult = superUserService.login(loginRequest.getUserId(), loginRequest.getUserPassword());

        if (loginResult != LoginStatus.SUCCESS)return new ResponseEntity<>(loginResult.getMessage(), loginResult.getStatus());

        String token = superUserService.afterSuccessLogin(loginRequest.getUserId());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/review")
    public ResponseEntity<String> writeReview(Authentication authentication){
        return ResponseEntity.ok().body(authentication.getName() + "님의 리뷰 등록이 완료되었습니다.");

    }
}
