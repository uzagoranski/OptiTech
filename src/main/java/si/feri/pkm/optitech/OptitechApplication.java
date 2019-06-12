package si.feri.pkm.optitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;

@SpringBootApplication
@RestController
public class OptitechApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OptitechApplication.class, args);
    }

    // Mapping for user details from OAuth2Authentication
    @RequestMapping(value = "/user")
    public String[] user(OAuth2Authentication authentication) {

        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

        String email = (String) properties.get("email");
        String name = (String) properties.get("name");
        String image = (String) properties.get("picture");

        String[] data = {email, name, image};
        return data;
    }

}