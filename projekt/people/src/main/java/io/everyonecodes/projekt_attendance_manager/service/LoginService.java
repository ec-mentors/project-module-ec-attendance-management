package io.everyonecodes.projekt_attendance_manager.service;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties("login-service")
public class LoginService {
    private String message;
    private String message2;
    private String message3;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public void setMessage3(String message3) {
        this.message3 = message3;
    }

    public String getWelcomeMessage(Authentication authentication) {
        String username = authentication.getName();

        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_STUDENT"))) {
            return message + " " + username + "!";
        } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE"))) {
            return message2 + " " + username + "!";
        } else {
            return message3;
        }
    }
}
