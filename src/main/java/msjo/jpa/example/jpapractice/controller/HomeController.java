package msjo.jpa.example.jpapractice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * packageName    : msjo.jpa.example.jpapractice.controller
 * fileName       : HomeController
 * author         : ms.jo
 * date           : 2023/09/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/26        ms.jo       최초 생성
 */

@RestController
@RequestMapping({"/home", "/"})
@Slf4j
public class HomeController {

    @GetMapping
    public String home() {
        return LocalDateTime.now().toString();
    }
}
