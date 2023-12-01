package mohist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MohistApplication {

    public static void main(String[] args) {
        Database.init();
        SpringApplication.run(MohistApplication.class, args);
    }

}
