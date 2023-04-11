package kr.co.opgg.common.jwttoken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private static final Long ACCESS_TIME = 60 * 1000L;
    private static final Long REFRESH_TIME = 2 * 60 * 1000L;
    public static final String ACCESS_TOKEN = "Access_Token";
    public static final String REFERSH_TOKEN = "Refersh_Token";
    private static final String SECRET_KEY = "TestSecretKey";


    private String secretKey = SECRET_KEY;
    private Key key;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public TokenDto createAllToken(String userId){
        return new TokenDto(createToken(userId, "Access"), createToken(userId, "Refresh"));
    }

    public String createToken(String userId, String type){
        Date date = new Date();

        Long time = type.equals("Access") ? ACCESS_TIME : REFRESH_TIME;

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(date.getTime() + time))
                .setIssuedAt(date)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

}
