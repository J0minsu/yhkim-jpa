package msjo.jpa.example.jpapractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class JpaPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaPracticeApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {

        /**
         * SpringSecurity 에서 ID 정보를 꺼내서 return 해줘야 함
         */

        return () -> Optional.of(UUID.randomUUID().toString());
    }
}
