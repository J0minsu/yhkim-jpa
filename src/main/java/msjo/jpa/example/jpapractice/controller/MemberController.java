package msjo.jpa.example.jpapractice.controller;

import lombok.RequiredArgsConstructor;
import msjo.jpa.example.jpapractice.domain.dto.request.MemberSearchResponse;
import msjo.jpa.example.jpapractice.domain.entity.Member;
import msjo.jpa.example.jpapractice.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
    public Page<MemberSearchResponse> findMembers(@PageableDefault(size = 5, sort = "username", direction = Direction.DESC) Pageable pageable) {

        Page<Member> members = memberRepository.findAll(pageable);
        Page<MemberSearchResponse> result = members.map(item -> new MemberSearchResponse(item.getId(), item.getUsername(), null));

        return result;

    }

    @PostConstruct
    public void init() {
        for (char i = 'A'; i  <= 'Z'; i++) {
            memberRepository.save(Member.of("member" + i, Character.getNumericValue(i), null));
        }
    }
}
