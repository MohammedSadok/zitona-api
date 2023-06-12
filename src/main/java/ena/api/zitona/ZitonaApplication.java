package ena.api.zitona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class ZitonaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZitonaApplication.class, args);
    }

}
