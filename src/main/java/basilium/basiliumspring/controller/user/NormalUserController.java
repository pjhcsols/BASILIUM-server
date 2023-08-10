package basilium.basiliumspring.controller.user;

import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.service.user.NormalUserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NormalUserController {
    private final NormalUserService normalUserService;

    @Autowired
    public NormalUserController(NormalUserService normalUserService) {
        this.normalUserService = normalUserService;
    }

    @PostMapping(value = "/v1/normalUser/signup")
    public ResponseEntity<String> createNormalUser(@RequestBody NormalUser normalUser){
        Boolean result = normalUserService.join(normalUser);
        if (result){
            return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("회원가입 실패", HttpStatus.BAD_REQUEST);
        }

    }
}