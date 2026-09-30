package example.day12;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component  // Spring MVC 패턴이 아닌 일반 객체
public class JWTUtil {
    @Value("${jwt.secret}")// @Value("${propertis파일내 속성명}")
    private String key;

    // hmacSha 알고리즘 : 단방향 , 대칭키 (바이트로 변환 하여 설정 )
    private SecretKey secretKey;

    @PostConstruct // 객체 생성시 의존성 (@Value)가 완료 된 후에 아래 메소드가 1번 호출 되도록 하는 어노테이션
    public void init(){
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    // [3] JWT Refresh 토큰 생성 메소드
    public String createRefreshToken(Long mno){
        return Jwts.builder()
                .claim("type", "REFRESH")
                .subject(mno+"")
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 1000L * 60 * 60 * 24 * 7)) // 액세스 토큰 보다 만료기간 길게
                .compact();
    }

    // [1] JWT Access 토큰 생성 메소드
    public String createAccessToken(long mno){
        return Jwts.builder() // 토큰 생성 시작
                .claim("type","ACCESS")
                .subject(mno+"") // 토큰에 들어갈 내용들 ( 주로 식별번호 )
                .issuedAt(new Date()) // 토큰 생성 시간 , 현재 시간
                .expiration(new Date(new Date().getTime() + 1000L * 60 * 30)) // 토큰 만료시간
                // new Date() 현재시간, newDate().getTime() 현재시간 초 * 60(1분) , 현재시간 분 * 60(1시간)
                .signWith(secretKey) // 비밀키로 전자서명
                .compact(); // 토큰 생성 끝
    }

    // [2] JWT 토큰 검증 메소드
    public Long getMnoFromToken(String token){
        try { // 만약 token 파싱(가져오기) 실패하하면 예외 발생
            Claims claims = Jwts.parser() // 파싱
                    .verifyWith(secretKey) // 전자서명 이용한 검증
                    .build()
                    .parseSignedClaims(token) // 파싱할 토큰
                    .getPayload(); // JWT안에 payload
            Long mno = Long.parseLong(claims.getSubject()); // payload안에 subject꺼내기 long타입 변환
            System.out.println(mno);
            return mno;
        } catch (Exception e){
            return null;
        }
    }
}
