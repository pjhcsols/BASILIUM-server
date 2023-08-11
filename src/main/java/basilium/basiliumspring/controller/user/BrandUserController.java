package basilium.basiliumspring.controller.user;

import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.JoinStatus;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.service.user.BrandUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandUserController {
    private final BrandUserService brandUserService;

    @Autowired
    public BrandUserController(BrandUserService brandUserService) {
        this.brandUserService = brandUserService;
    }

    @PostMapping(value = "/v1/branduser/signup")
    public ResponseEntity<String> createNormalUser(@RequestBody BrandUser brandUser) {
        JoinStatus result = brandUserService.join(brandUser);
        return new ResponseEntity<>(result.getMessage(), result.getStatus());
    }
}