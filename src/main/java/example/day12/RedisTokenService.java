package example.day12;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisTokenService {
    // [1] 레디스 조작 객체 주입
    private final StringRedisTemplate stringRedisTemplate;

    // [2] refresh 토큰 레디스 저장함수
    public void  setRefreshToken(Long mno, String token){
        // key는 RT:회원번호 조합 , value는 refresh 토큰
        // 만료기간 : Duration,ofDays(7) -> 7일 만료
        stringRedisTemplate.opsForValue().set("RT:"+mno, token, Duration.ofDays(7));
    }

    // [3] refresh 토큰 조회 함수
    public String getRefreshToken(Long mno){
        // 조회할 key 조합
        return  stringRedisTemplate.opsForValue().get("RT:" + mno);
    }

    // [4] refresh 토큰 삭제 함수
    public boolean deleteRefreshToken(Long mno){
        return stringRedisTemplate.delete("RT:" + mno);
    }
}
