package basilium.basiliumspring.controller.user;

import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.JoinStatus;
import basilium.basiliumspring.domain.user.SuperUser;
import basilium.basiliumspring.service.user.BrandUserService;
import basilium.basiliumspring.service.user.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuperUserController {
    private final SuperUserService superUserService;

    @Autowired
    public SuperUserController(SuperUserService superUserService) {
        this.superUserService = superUserService;
    }

    @PostMapping(value = "/v1/superuser/signup")
    public ResponseEntity<String> createNormalUser(@RequestBody SuperUser superUser) {
        JoinStatus result = superUserService.join(superUser);
        return new ResponseEntity<>("회원가입 성공", result.getStatus());
    }
}
