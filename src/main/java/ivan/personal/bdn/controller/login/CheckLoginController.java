package ivan.personal.bdn.controller.login;

import ivan.personal.bdn.model.User;
import ivan.personal.bdn.repository.UserRepository;
import ivan.personal.bdn.utility.CustomPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/check-login")
@AllArgsConstructor
public class CheckLoginController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<CustomPrincipal> isLogged(Principal principal) {
        CustomPrincipal cPrincipal = new CustomPrincipal(principal);
        if (cPrincipal.getPrincipal() != null) {
            User user = userRepository.findByUsername(cPrincipal.getPrincipal().getName());
            cPrincipal.setHospitalCode(user.getHospitalCode());
        }
        return new ResponseEntity<>(cPrincipal, HttpStatus.OK);
    }

}
