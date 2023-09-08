package msjo.jpa.example.jpapractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Date;
import java.util.List;

//@Service
public class HomeService {

    /*@Autowired
    private HomeRepository homeRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public String getHome(int id) {

        String token =  Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                                      .setIssuer("LIFESEMANTICS")
                                      .setIssuedAt(new Date())
                                      .setExpiration(new Date(new Date().getTime() + Duration.ofMinutes(30).toMillis()))
                                      .claim("id", "msjo")
                                      .claim("email", "tok0419@naver.com")
                                      .signWith(SignatureAlgorithm.HS256, "LIFE1234")
                                      .compact();

        System.out.println("TOKEN :: " + token);

        System.out.println("PARSE ::" +Jwts.parser().setSigningKey("LIFE1234")
                                                    .parseClaimsJws(token)
                                                    .getBody());

//        Home home = Home.builder().comment("WELCOME")
//                                  .visitType(VisitType.ADMIN)
//                                  .build();

//        entityManager.persist(home);

        Home refHome = entityManager.getReference(Home.class, id);

        return refHome.getVisitType().isAccess() ? "환엽합니다 고객님!"
                                                 : "허용되지 않은 사용자입니다";
    }


    public void test() {

        


    }*/
}
