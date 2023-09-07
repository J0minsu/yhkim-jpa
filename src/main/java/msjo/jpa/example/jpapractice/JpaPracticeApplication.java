package msjo.jpa.example.jpapractice;

import msjo.jpa.example.jpapractice.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JpaPracticeApplication {

    @Autowired
    PracticeService practiceService;

    public static void main(String[] args) {
        SpringApplication.run(JpaPracticeApplication.class, args);
    }

    @PostConstruct
    void post() {

//        practiceService.main();
        practiceService.inheritTest();
    }

}
