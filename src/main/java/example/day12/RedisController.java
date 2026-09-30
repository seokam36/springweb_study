package example.day12;

import example.day12.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.*;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class RedisController {
    // [*] 레디스 조작 객체 (문자열 기반의 자료 레디스에 crud)
    private final StringRedisTemplate stringRedisTemplate;

    // 1.
    @GetMapping("/test1")
    public Map<String, Object> test1(){
        // [1] 레디스에 자료 삽입, key : value , 문자열타입
        // key 중복 X , value 중복 가능 => NOSQL
        stringRedisTemplate.opsForValue().set("A","90");
        stringRedisTemplate.opsForValue().set("B","100");
        stringRedisTemplate.opsForValue().set("C","80");

        // [2] 레디스 자료 조회 , .keys("*") , 모든 키 호출
        Set<String> keys = stringRedisTemplate.keys("*");
        Map<String, Object> map = new HashMap<>();
        for (String key : keys) { // 모든 키들을 하나씩 반복해서 data로 저장
            String data = stringRedisTemplate.opsForValue().get(key);
            map.put(key, data);
        }
        return map;
    }

    // *********  Redis CRUD *********
    private final ObjectMapper objectMapper = new ObjectMapper(); // 직렬화

    @PostMapping("/member")
    public boolean save(@RequestBody MemberDto memberDto){
        // 1. 중복 없는 key 구성 (도메인명 : 식별키(PK))
        String key = "member:"+memberDto.getMno(); // ex)member:3

        // 2. 문자열템플릿에 DTO/자바객체 대입 , DTO -> 문자열()
        // writeValueAsString(자바 객체) -> 문자열 변환
        String data = objectMapper.writeValueAsString(memberDto);// dto --> String 직렬화

        // 3. 레디스에 저장
        stringRedisTemplate.opsForValue().set(key, data);
        return true;
    }

    // [2] 전체조회
    @GetMapping("/member")
    public List<MemberDto> findAll(){
        List<MemberDto> memberDtos = new ArrayList<>();
        // 1. 특정 패턴의 key 조회 , member:* , member로 시작하는 모든 키 조회
        Set<String> keys = stringRedisTemplate.keys("member:*");
        // 2. 모든 키 반복 하여 하나씩 키에 대응하는 dto 호출
        for (String key : keys) {
            String value = stringRedisTemplate.opsForValue().get(key);
            // 3. 역질렬화 , 문자열 -> 자바객체
            // objectMapper.readValue(값, 자바객체)
            MemberDto memberDto = objectMapper.readValue(value, MemberDto.class);
            memberDtos.add(memberDto);
        }
        return memberDtos;
    }

    // [3] 개별 조회
    @GetMapping("/member/find")
    public MemberDto find(@RequestParam Long mno){
        // 1. 조회할 mno 매개변수로 받기
        // 2. 레디스에서 특정 mno의 키 조회
        String findKey = "member:"+mno;
        String value = stringRedisTemplate.opsForValue().get(findKey);
        if (value == null) return null;

        // 3. 역직렬화
        MemberDto memberDto = objectMapper.readValue(value, MemberDto.class);
        return memberDto;
    }

    // [4] 삭제
    @DeleteMapping("/member")
    public boolean delete(@RequestParam Long mno){
        // 1. 레디스에서 삭제할 mno의 키 조회
        String deleteKey = "member:"+mno;

        Boolean result = stringRedisTemplate.delete(deleteKey);
        return result;
    }

    // [5] 수정
    @PutMapping("/member")
    public boolean update(@RequestBody MemberDto memberDto){
        // 1. 수정할 자료 dto로 받아 수정할 key 조합
        String updateKey = "member:" + memberDto.getMno();
        if (updateKey == null) return false;

        // 3. 동일한 키로 입력받은 dto 직렬화 저장
        String value = objectMapper.writeValueAsString(memberDto);
        stringRedisTemplate.opsForValue().set(updateKey, value);
        return true;
    }
}

