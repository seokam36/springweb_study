package spring_ativity2;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final StringRedisTemplate stringRedisTemplate;

    @GetMapping("/session/add")
    public String sessionSave(@RequestParam String data, HttpSession session){
        List<Object> sessionList = (List<Object>) session.getAttribute("data");
        if (sessionList == null){
            sessionList = new ArrayList<>();
        }
        sessionList.add(data);
        session.setAttribute("data", sessionList);
        return "세션저장성공";
    }

    @GetMapping("/session/all")
    public List<Object> findAll(HttpSession session){
        List<Object> sessionList = (List<Object>) session.getAttribute("data");
        if (sessionList == null){
            return new ArrayList<>();
        }
        return sessionList;
    }

    @GetMapping("/cookie/add")
    public String cookieSave(@RequestParam String data,
                             @CookieValue(value = "data", required = false)String cookieData,
                             HttpServletResponse response){
        String newData;
        if (cookieData == null){
            newData = data;
        } else {
            newData = cookieData+"_"+data;
        }

        ResponseCookie cookie = ResponseCookie.from("data", newData)
                .path("/")
                .maxAge(Duration.ofDays(1))
                .httpOnly(true)
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return "쿠키저장성공";
    }

    @GetMapping("/cookie/all")
    public List<String> findAll(@CookieValue(value = "data",required = false)String cookie){
        List<String> cookieData = new ArrayList<>();
        if (cookie == null){
            return new ArrayList<>();
        }
        String[] split = cookie.split("_");
        for (String s : split) {
            cookieData.add(s);
        }
        return cookieData;
    }

    private final ObjectMapper objectMapper = new ObjectMapper(); // 직렬화
    @GetMapping("/redis/add")
    public String redisSave(@RequestParam String data){
        stringRedisTemplate.opsForValue().set("list:"+data,data);
        return "레디스 저장 성공";
    }

    @GetMapping("/redis/all")
    public List<String> findAll(){

        Set<String> keys = stringRedisTemplate.keys("list:*");
        List<String> list = new ArrayList<>();
        for (String key : keys) { // 모든 키들을 하나씩 반복해서 data로 저장
            String data = stringRedisTemplate.opsForValue().get(key);
            list.add( data);
        }
        return list;
    }
}
