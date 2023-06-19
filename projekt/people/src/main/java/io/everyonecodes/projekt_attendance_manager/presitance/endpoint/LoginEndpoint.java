package io.everyonecodes.projekt_attendance_manager.presitance.endpoint;
import io.everyonecodes.projekt_attendance_manager.service.LoginService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class LoginEndpoint {

    private final LoginService loginService;

    public LoginEndpoint(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    @ResponseBody
    public String welcomeMessage(Authentication authentication) {
        return loginService.getWelcomeMessage(authentication);
    }
}
