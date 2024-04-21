package backend.musinsa.domain.jwt;


import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.TokenWithOutCredentials;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private final Key key;
    @Value("${access.token.expire}")
    private Integer ACCESS_TOKEN_EXPIRES;
    @Value("${refresh.token.expire}")
    private Integer REFRESH_TOKEN_EXPIRES;

    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] decode = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(decode);
    }

    /**
     * @param authentication
     * 제공된 인증객체 Authentication 을 기반으로 Access Token 과 Refresh Token 을 생성한다.
     * Access Token 은 인증된 사용자의 권한 정보와 만료 시간을 담고있다.
     * Access Token 또는 Refresh Token 갱신을 위해 사용할수있다.
     * @return
     */
    public TokenDto generateToken(Authentication authentication){

        //권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        long now = new Date().getTime();

        // Access Token 생성

        Date accessTokenExpires = new Date(now + ACCESS_TOKEN_EXPIRES);//기본 밀리초 -> 86,400,000 (1일)

        String accessToken = Jwts.builder()         //JWT 빌더 객체 생성
                .setSubject(authentication.getName())   // JWT의 payload 에 sub식별자 추가.
                .claim("auth", authorities)         //  사용자의 권한 추가.
                .setExpiration(accessTokenExpires)          // 토큰의 만료시간 추가
                .signWith(key, SignatureAlgorithm.HS256)    // JWT에 서명을 추가.
                .compact();                                 //JWT를 문자열로 변환하여 반환.

        // Refresh Token 생성

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRES))       //7일
                .signWith(key, SignatureAlgorithm.HS256)
                .claim("mid",authentication.getName())
                .claim("auth",authorities)
                .compact();

        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * @param accessToken
     * 주어진 Access Token을 복호화 하여 인증정보(Authentication)을 생성한다.
     * 토큰의 Claims 에서 권한 정보를 추출하고, User 객체를 생성하여 Authentication 객체로 반환한다.
     *  권한정보를 다양하게 받을수 있도록 Collection< ? extends GrantedAuthority> 타입으로 리턴을 받는다.
     *
     *  Authentication 객체 생성 과정
     *  1. Token Claims 에서 권한 정보를 가져온다. "auth" Claim 은 토큰에 저장된 권한 정보를 나타낸다.
     *  2. 가져온 권한 정보를 SimpleGrantedAuthority 객체로 변환하여 컬렉션에 추가한다.
     *  3. UserDetails 객체를 생성하여 주체(subject)와 권한정보, 추가 정보를 설정한다.
     *  4. UsernamePasswordAuthenticationToken 객체를 생성하여 주체와 권한 정보를 포함한 인증 객체를 생성한다.
     * @return
     */
    public Authentication getAuthentication(String accessToken){
        //Jwt 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if(claims.get("auth") == null){
            throw new TokenWithOutCredentials(ExceptionEnum.TOKEN_WITH_OUT_CREDENTIALS);
        }

        //Claim 에서 권한 정보 가져오기.
        List<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        // UserDetails 객체 생성
        UserDetails principal = new User(claims.getSubject(),"",authorities);
        return new UsernamePasswordAuthenticationToken(principal,"",authorities);
    }

    /**
     * 리프레시 토큰에 있는 회원의 아이디 조회
     * @param refreshToken
     * @return
     */
    public String extractMemberId(String refreshToken){
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken);
        Claims body = claimsJws.getBody();
        return body.get("mid",String.class);
    }

    /**
     * 토큰을 검증하여 유효성 검사
     * Jwts.parserBuilder 를 사용하여 토큰의 서명 키를 설정하고, 예외처리를 통하여 토큰의 유효성 여부를 판단한다.
     * @param token
     * @return
     */
    //토큰 정보 검증 메서드
    public boolean validateToken(String token){

        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e){
            //서명키가 올바르지 않은경우, JWT 토큰이 잘못된 방식으로 작성된 경우
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e){
            //JWT 토큰 만료 시간이 지난경우
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e){
            //JWt 토큰이 지원되지 않는 형식 또는 구조를 가지고 있는 경우
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e){
            //JWT Claims 문자열이 비어 있는 경우
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    /**
     * @param accessToken
     * 클레임(Claims) : 토큰에서 사용할 정보
     * 주어진 Access token 을 복호화하고, 만료된 토큰일 경우에도 Claims 반환
     * parseClaimsJwt()메서드가 JWT 토큰의 검증과 파싱을 모두 수행
     * @return
     */
    private Claims parseClaims(String accessToken) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public TokenDto reissueToken(String refreshToken){
        //Jwt 토큰 복호화
        Claims claims = parseClaims(refreshToken);

        if(claims.get("auth") == null){
            throw new TokenWithOutCredentials(ExceptionEnum.TOKEN_WITH_OUT_CREDENTIALS);
        }

        //Claim 에서 권한 정보 가져오기.
        List<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        String memberId = claims.get("mid", String.class);
        // UserDetails 객체 생성
        UserDetails principal = new User(memberId,"",authorities);
        //생성된 객체를 통해 새로운 토큰 발급
        return generateToken(new UsernamePasswordAuthenticationToken(principal,"",authorities));
    }
}
