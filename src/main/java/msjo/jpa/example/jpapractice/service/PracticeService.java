package msjo.jpa.example.jpapractice.service;


import lombok.extern.slf4j.Slf4j;
import msjo.jpa.example.jpapractice.cascade.Child;
import msjo.jpa.example.jpapractice.cascade.Parent;
import msjo.jpa.example.jpapractice.entity.Member;
import msjo.jpa.example.jpapractice.entity.Order;
import msjo.jpa.example.jpapractice.strategy.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Slf4j
public class PracticeService {

    @Autowired
    private EntityManager em;

    @Transactional(isolation = Isolation.DEFAULT)
    public void updateTest() {

        Member member = em.find(Member.class, 1L);
        System.out.println("member = " + member);
        member.setCity("달나라");


    }

    @Transactional
    public void cascadeTest() {

        Parent parent = new Parent();

        Child childA = new Child();
        Child childB = new Child();

        parent.addChild(childA);
        parent.addChild(childB);

        em.persist(parent);
    }

    @Transactional
    public void proxyTest() {

        Member member = new Member();
        member.setName("손풍기");

        em.persist(member);

        em.flush();
        em.clear();

//        Member fineMember = em.find(Member.class, 1L);
        Member fineMember = em.getReference(Member.class, 1L);
        System.out.println("fineMember.getId() = " + fineMember.getId());
        printMember(fineMember);
        printMemberAndOrders(fineMember);
    }

    private void printMember(Member member) {
        String username = member.getName();
        System.out.println("username = " + username);
    }

    private void printMemberAndOrders(Member member) {
        String username = member.getName();
        System.out.println("username = " + username);

        List<Order> orders = member.getOrders();
        System.out.println("orders = " + orders);

    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void persistTest() {

        System.out.println("em = " + em);

        try {

            Member member1 = new Member();
            member1.setName("조민수");
            member1.setStreet("남문로");
            member1.setZipcode("21117");
            member1.setCity("성남시 중원구 태평3동");
            member1.setCreateBy("msjo");

            Member member2 = new Member();
            member2.setName("홍은영");
            member2.setStreet("영통롱");
            member2.setZipcode("21111");
            member2.setCity("경기도 수원시 영통구");
            member2.setCreateBy("eyhong");

            Member member3 = new Member();
            member3.setName("이지석");
            member3.setStreet("영남로");
            member3.setZipcode("11115");
            member3.setCity("인천시 계양구 동양동");
            member3.setCreateBy("jslee");

            System.out.println("member1 = " + member1);
            System.out.println("member2 = " + member2);
            System.out.println("member3 = " + member3);

            System.out.println("<========================");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1 = " + member1.getId());
            System.out.println("member2 = " + member2.getId());
            System.out.println("member3 = " + member3.getId());

            System.out.println("========================>");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void inheritTest() {

        Movie movie = new Movie();
        movie.setName("태극기 휘날리며");
        movie.setPrice(14000);
        movie.setDirector("봉준호");
        movie.setActor("장동건");

        em.persist(movie);

        em.flush();
        em.clear();

        Movie movie1 = em.find(Movie.class, movie.getId());
        System.out.println("movie1 = " + movie1);

    }

}
