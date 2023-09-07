package msjo.jpa.example.jpapractice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PracticeServiceTest {

    @Autowired
    private PracticeService practiceService;

//    @Test
    void main() {

        practiceService.main();

    }
}