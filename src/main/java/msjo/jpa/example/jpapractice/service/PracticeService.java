package msjo.jpa.example.jpapractice.service;


import lombok.extern.slf4j.Slf4j;
import msjo.jpa.example.jpapractice.cascade.Child;
import msjo.jpa.example.jpapractice.cascade.Parent;
import msjo.jpa.example.jpapractice.constants.DeliveryStatus;
import msjo.jpa.example.jpapractice.constants.MemberType;
import msjo.jpa.example.jpapractice.constants.OrderStatus;
import msjo.jpa.example.jpapractice.entity.*;
import msjo.jpa.example.jpapractice.entity.embed.Address;
import msjo.jpa.example.jpapractice.entity.embed.Period;
import msjo.jpa.example.jpapractice.strategy.Book;
import msjo.jpa.example.jpapractice.strategy.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        /*member.setCity("달나라");*/

    }

    @Transactional
    public void fetchTest() {

        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        em.persist(teamA);
        em.persist(teamB);

        Member memberA = new Member();
        memberA.setName("수원");
        memberA.setType(MemberType.ADMIN);
        memberA.setAge(18);
        memberA.setTeam(teamA);
        em.persist(memberA);

        Member memberB = new Member();
        memberB.setType(MemberType.USER);
        memberB.setTeam(teamB);
        em.persist(memberB);

        Member memberC = new Member();
        memberC.setType(MemberType.USER);
        memberC.setTeam(teamB);
        em.persist(memberC);

        AddressEntity addressA = new AddressEntity("인천", "아나지로", "21116");
        AddressEntity addressB = new AddressEntity("성남", "남문로", "31167");
        AddressEntity addressC = new AddressEntity("수원", "영통로", "67623");

        memberA.getAddressHistory().add(addressA);
        memberA.getAddressHistory().add(addressB);
        memberA.getAddressHistory().add(addressC);

        em.flush();
        em.clear();

        String query = "";
        /**
         * N + 1
         */
        //query = "SELECT m FROM Member m";

        /**
         * fetch
         */
        //query = "SELECT m FROM Member m JOIN FETCH m.team";

        /**
         * collection fetch ( need 'DISTINCT' )
         */
        query = "SELECT DISTINCT t FROM Team t JOIN FETCH t.members";

        List<Team> resultList = em.createQuery(query, Team.class).getResultList();

        for (Team team : resultList) {
            System.out.println("team = " + team.getName() + " :: {" + team.getMembers().size());
        }
    }

    @Transactional
    public void expressionTest() {

        Member memberA = new Member();
        memberA.setName("수원");
        memberA.setType(MemberType.ADMIN);
        memberA.setAge(18);

        Member memberB = new Member();
        memberB.setType(MemberType.USER);

        AddressEntity addressA = new AddressEntity("인천", "아나지로", "21116");
        AddressEntity addressB = new AddressEntity("성남", "남문로", "31167");
        AddressEntity addressC = new AddressEntity("수원", "영통로", "67623");

        memberA.getAddressHistory().add(addressA);
        memberA.getAddressHistory().add(addressB);
        memberA.getAddressHistory().add(addressC);


        Book book = new Book();
        book.setName("토끼와 선녀");
        book.setPrice(17000);
        book.setIsbn("RAA-001");
        book.setAuthor("hyshayishi jo");

        em.persist(memberA);
        em.persist(memberB);
        em.persist(book);

        em.flush();
        em.clear();

        List<String> resultList = new ArrayList<>();


        /**
         * NULLIF
         */
        String nullIfQuery = "SELECT NULLIF(m.name, '이름 없는 헤원') FROM Member m";
                resultList = em.createQuery(nullIfQuery, String.class).getResultList();

        /**
         * COALESCE
         */
        /*String coalesceQuery = "SELECT COALESCE(m.name, '이름 없는 헤원') FROM Member m";
        resultList = em.createQuery(coalesceQuery, String.class).getResultList();*/

        /**
         * Case
         */
        /*String caseQuery = "SELECT " +
                "CASE " +
                "WHEN m.age <= 10 THEN '학생요금' " +
                "WHEN m.age > 60 THEN '경로요금' " +
                "ELSE '일반요금' " +
                "END " +
                "FROM Member m";

        resultList = em.createQuery(caseQuery, String.class).getResultList();
        */
        for (String s : resultList) {
            System.out.println("s = " + s);
        }

        /*em.createQuery("SELECT i FROM StrategyItem i WHERE type(i) = Book", StrategyItem.class).getResultList();*/

        /*String query = "SELECT m.name, 'HELLO', TRUE " +
                         "FROM Member m " +
                        "WHERE m.type = :userType";
        List<Object[]> result = em.createQuery(query).setParameter("userType", MemberType.ADMIN).getResultList();

        for (Object[] obs : result) {
            System.out.println("obs[0] = " + obs[0]);
            System.out.println("obs[1] = " + obs[1]);
            System.out.println("obs[2] = " + obs[2]);
        }*/
    }

    @Transactional
    public void criteriaTest() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);

        CriteriaQuery<Member> cq = query.select(m)
                                        .where(cb.equal(m.get("name"), "Kim"));

        List<Member> members = em.createQuery(cq).getResultList();

        System.out.println("members = " + members);

        List<Member> memberResult = em.createQuery("SELECT m FROM Member m", Member.class)
                .setFirstResult(0)
                .setMaxResults(5)
                .getResultList();
    }

    @Transactional
    public void jpqlJoinTest() {

        Member member = new Member();
        member.setName("수원");

        AddressEntity addressA = new AddressEntity("인천", "아나지로", "21116");
        AddressEntity addressB = new AddressEntity("성남", "남문로", "31167");
        AddressEntity addressC = new AddressEntity("수원", "영통로", "67623");

        member.getAddressHistory().add(addressA);
        member.getAddressHistory().add(addressB);
        member.getAddressHistory().add(addressC);

        em.persist(member);

        em.flush();
        em.clear();

        /**
         * inner join
         */
        /*String innerJoinQuery = "SELECT a FROM Member m INNER JOIN m.addressHistory a";
        List<AddressEntity> innerResultList = em.createQuery(innerJoinQuery, AddressEntity.class).getResultList();*/

        /**
         * left outer join
         */
        /*String outerJoinQuery = "SELECT a FROM Member m LEFT OUTER JOIN m.addressHistory a";
        List<AddressEntity> outerResultList = em.createQuery(outerJoinQuery, AddressEntity.class).getResultList();*/

        /**
         * setter join
         */
        /*String setterJoinQuery = "SELECT m FROM Member m, AddressEntity a WHERE m.name = a.address.city";
        List<Member> setterResultList = em.createQuery(setterJoinQuery, Member.class).getResultList();

        for (Member m : setterResultList) {
            System.out.println("m = " + m);
        }*/

        /**
         * left outer join using 'on'
         */
        /*String leftOuterJoinQuery = "SELECT m FROM Member m LEFT OUTER JOIN m.addressHistory a ON a.address.street = '영통로'";
        List<Member> innerResultList = em.createQuery(leftOuterJoinQuery, Member.class).getResultList();

        for (Member m : innerResultList) {
            System.out.println("m = " + m);
        }*/

        /**
         * left outer join using 'on' between non-related tables
         */
        String leftQueryJoinUsingOnQuery = "SELECT m FROM Member m LEFT JOIN Item i ON m.name = i.name";
        List<Member> resultList = em.createQuery(leftQueryJoinUsingOnQuery, Member.class).getResultList();

        for (Member m : resultList) {
            System.out.println("m = " + m);
        }
    }

    @Transactional
    public void jpqlTest() {

        List<Member> members = em.createQuery(
                "SELECT m FROM Member m WHERE m.name LIKE '%길%'", Member.class
        ).getResultList();

        System.out.println("members = " + members);
    }

    @Transactional
    public void valueTypeTest() {
        Member member = new Member();
        member.setName("혼길돈");
        member.setHomeAddress(new Address("도시", "거리", "21116"));

        member.getFavorites().add("치킨");
        member.getFavorites().add("족발");
        member.getFavorites().add("보쌈");
        member.getFavorites().add("피자");

        member.getAddressHistory().add(new AddressEntity("도시1", "거리1", "21116"));
        member.getAddressHistory().add(new AddressEntity("도시2", "거리2", "21117"));
        member.getAddressHistory().add(new AddressEntity("도시3", "거리3", "21118"));
        AddressEntity address4 = new AddressEntity("도시4", "거리4", "21119");
        member.getAddressHistory().add(address4);

        em.persist(member);

        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());

        System.out.println("findMember = " + findMember.getAddressHistory());
//        System.out.println("findMember.getFavorites() = " + findMember.getFavorites());

//        findMember.getFavorites().remove("치킨");

//        findMember.getAddressHistory().remove(2);
//        findMember.getAddressHistory().add(new AddressEntity("도시5", "거리5", "21111"));



        Delivery delivery = new Delivery();
        delivery.setAddress(findMember.getAddressHistory().get(2));
        delivery.setStatus(DeliveryStatus.QUICK);

        Order order = new Order();
        order.setStatus(OrderStatus.ORDER);
        order.setMember(findMember);
        order.setOrderDate(LocalDateTime.now());
        order.setDelivery(delivery);

        em.persist(order);

        em.flush();
        em.clear();

        Order findOrder = em.find(Order.class, order.getId());

        System.out.println("findOrder.member = " + findOrder.getMember());
        System.out.println("findOrder.delivery = " + findOrder.getDelivery().getAddress());
        System.out.println("findOrder.orderItems = " + findOrder.getOrderItems());

        System.out.println("<================================ VALUE TYPE UPDATE EMD =================");

        findOrder.getDelivery().setAddress(new AddressEntity("뉴시티", "뉴거리", "12345"));

        em.flush();
        em.clear();

        Order newFindOrder = em.find(Order.class, findOrder.getId());

        System.out.println("newFindOrder.getDelivery().getAddress().getAddress().fullAddress() = " + newFindOrder.getDelivery().getAddress().getAddress().fullAddress());
    }

    @Transactional
    public void embedTest() {

        Member memberA = new Member();
        memberA.setName("홍길동");

        Member memberB = new Member();
        memberB.setName("성춘향");

        Period period = new Period(LocalDateTime.now(), LocalDateTime.now().plusHours(8L));
        Address homeAddressA = new Address("인천", "아나지로", "1234");
        Address homeAddressAA = new Address("인천", "아나지로", "1234");
        Address officeAddressA = new Address("서울", "언주로", "5676");

        Address homeAddressB = new Address(homeAddressA.getCity(), homeAddressA.getStreet(), homeAddressA.getZipcode());
        Address officeAddressB = new Address(officeAddressA.getCity(), officeAddressA.getStreet(), officeAddressA.getZipcode());

        Address officeAddressC = new Address(officeAddressA.getCity(), "새로운 거리", "99324");

        memberA.setPeriod(period);
        memberA.setHomeAddress(homeAddressA);
        memberA.setOfficeAddress(officeAddressA);

        memberB.setPeriod(period);
        memberB.setHomeAddress(homeAddressB);
        memberB.setOfficeAddress(officeAddressB);

        em.persist(memberA);
        em.persist(memberB);

        System.out.println("member = " + memberA);

        memberA.setOfficeAddress(officeAddressC);

        System.out.println("member = " + memberA);

        System.out.println("(homeAddressA == homeAddressAA) = " + (homeAddressA == homeAddressAA));
        System.out.println("(homeAddressA equals homeAddressAA) = " + (homeAddressA.equals(homeAddressAA)));

    }

    @Transactional
    public void cascadeTest() {

        Parent parent = new Parent();
        Child childA = new Child();
        Child childB = new Child();

        parent.addChild(childA);
        parent.addChild(childB);

        em.persist(parent);

        em.flush();
        em.clear();

        Parent findParent = em.find(Parent.class, parent.getId());

        em.remove(findParent);
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
            member1.setHomeAddress(new Address("남문로", "성남시 중원구 태평3동", "21117"));
            member1.setCreateBy("msjo");

            Member member2 = new Member();
            member2.setName("홍은영");
            member1.setHomeAddress(new Address("영통롱", "경기도 수원시 영통구", "21111"));
            member2.setCreateBy("eyhong");

            Member member3 = new Member();
            member3.setName("이지석");
            member1.setHomeAddress(new Address("영남로", "인천시 계양구 동양동", "11115"));
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
