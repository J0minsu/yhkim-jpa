package msjo.jpa.example.jpapractice.controller;

import lombok.RequiredArgsConstructor;
import msjo.jpa.example.jpapractice.domain.entity.Member;
import msjo.jpa.example.jpapractice.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : msjo.jpa.example.jpapractice.controller
 * fileName       : MemberController
 * author         : ms.jo
 * date           : 2023/10/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/10/10        ms.jo       최초 생성
 */
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).orElse(Member.of("NuLL"));
        return member.getUsername();
    }

    @GetMapping("/v2/{id}")
    public String findMemberV2(@PathVariable("id") Optional<Member> member) {
        Member afterMember = member.orElse(Member.of("NuLL"));
        return afterMember.getUsername();
    }

    @GetMapping
    public Page<Member> findMembers(Pageable pageable) {

        return memberRepository.findAll(pageable);

    }

    @PostConstruct
    public void init() {
        memberRepository.save(Member.of("memberA", 15, null));
        memberRepository.save(Member.of("memberB", 15, null));
        memberRepository.save(Member.of("memberC", 15, null));
        memberRepository.save(Member.of("memberD", 15, null));
    }
}
