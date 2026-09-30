package example.day12;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class MemberController {
    private final MemberService memberService;
    private final JWTUtil jwtUtil;
    private final RedisTokenService redisTokenService;

    // [1] 회원가입
    @PostMapping("/signup")
    public boolean signUp(@RequestBody MemberDto memberDto){
        return memberService.signup(memberDto);
    }

    // [2] 로그인 + 쿠키변경 ( 회원 번호 쿠키에 담아 클라이언트에 전송)
    @PostMapping("/login")
    public MemberDto login(@RequestBody MemberDto memberDto , HttpServletResponse response){
        // 1. 서비스 에게 인증/로그인 확인
        MemberDto result = memberService.login(memberDto);
        if (result == null) {
            return null;
        }

        // 토큰 **2개** 발급 요청
        String accessToken = jwtUtil.createAccessToken(result.getMno());
        String refreshToken = jwtUtil.createRefreshToken(result.getMno());

        // refreshToken만 redis에 저장
        redisTokenService.setRefreshToken(result.getMno(), refreshToken);

        // // 2. 로그인 성공 시 쿠키 생성/발급
        ResponseCookie cookie1 = ResponseCookie.from("accessToken", accessToken)
                .path("/")
                .maxAge(Duration.ofMinutes(30)) // 30분
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .build();
        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", accessToken)
                .path("/")
                .maxAge(Duration.ofDays(7)) // 7일
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .build();

        // 3. 응답 헤더에 쿠키 2개 등록 , response.setHeader()
        response.setHeader(HttpHeaders.SET_COOKIE , cookie1.toString());
        response.setHeader(HttpHeaders.SET_COOKIE , cookie2.toString());
        return result;
    }

    // [3] 내정보조회 + 쿠키
    @GetMapping("/me")
    public MemberDto getMyInfo(@CookieValue(value = "login_member", required = false)String token){ // 요청한 브라우저의 쿠키 가져오기
        // 1. 만약에 token이 없다면 비로그인
        if (token == null){
            return null;
        }
        // **** 쿠키에 저장된 token 이용하여 회원번호 찾기 ****
        Long loginMno = jwtUtil.getMnoFromToken(token);
        // 2. 로그인 중이면 서비스에게 회원정보 요청
        // 문자->정수 : 래퍼클래스명.parse타입(문자)
        return memberService.getMyInfo(loginMno);
    }

    // [4] 로그아웃 + 쿠키
    @PostMapping("/logout")
    public boolean logout(HttpServletResponse response){
        // 1. 삭제할 쿠키명과 동일한 이름으로 maxAge(0) 으로 재발급
        ResponseCookie cookie = ResponseCookie.from("login_member","")
                .path("/") // 모든곳에서 로그아웃 가능하도록
                .httpOnly(true)
                .secure(false)
                .maxAge(0) //바로 삭제
                .build();

        // 2. 응답객체내 헤더에 쿠키 포함
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return true;
    }
}

