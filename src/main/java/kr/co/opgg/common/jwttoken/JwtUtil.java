package kr.co.opgg.common.jwttoken;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.common.exception.JwtTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private static final Long ACCESS_TIME = 60 * 1000L;
    private static final Long REFRESH_TIME = 2 * 60 * 1000L;
    public static final String ACCESS_TOKEN = "Access_Token";
    public static final String REFERSH_TOKEN = "Refersh_Token";
    private static final String SECRET_KEY = "happilyeverafterhappilyeverafterdasgsadgasdgsadgasdgsdag";
    



    private String secretKey = SECRET_KEY;
    private Key key;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public TokenDto createAllToken(UserRequest user){
        return new TokenDto(createToken(user, "Access"), createToken(user, "Refresh"));
    }

    public String createToken(UserRequest user, String type){
        Date date = new Date();

        Long time = type.equals("Access") ? ACCESS_TIME : REFRESH_TIME;

        return Jwts.builder()
                .setSubject(String.valueOf(user.getUserId()))
                .claim("roleType", user.getAuthorityIdx())
                .setExpiration(new Date(date.getTime() + time))
                .setIssuedAt(date)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public Authentication getAuthentication(String token){
        Claims claims = tokenParser(token);

        Collection<? extends GrantedAuthority> authorite =
                (Collection<? extends GrantedAuthority>) claims.get("roleType");

        CustomUserDetails userInfo = new CustomUserDetails();
        userInfo.setUserIdx(Long.valueOf(claims.getSubject()));

        return new UsernamePasswordAuthenticationToken(userInfo, token, authorite);
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new JwtTokenException.TokenParseException();
        } catch (ExpiredJwtException e) {
            throw new JwtTokenException.ExpiredToken();
        }
    }

    public Claims tokenParser(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserIdx() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserIdx();
    }
}
