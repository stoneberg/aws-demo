package me.stone.aws.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AwsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsDemoApplication.class, args);
    }

}
