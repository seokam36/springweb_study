package example.day11;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

        // 2. 로그인 성공 시 쿠키 생성/발급 *** 쿠키 값을 jwt 안전하게 변경 ***
        // 토큰 발급 요청
        String token = jwtUtil.createToken(result.getMno()); // mno --> jwt
        ResponseCookie cookie = ResponseCookie.from("login_member", token)
                .path("/") // 쿠키 사용할 경로, "/" 도메인 전체
                .maxAge(Duration.ofDays(1)) // 쿠키의 유효기간, 1일
                .httpOnly(true) // JS 이용한 탈취 방지, XSS공격
                .secure(false) // HTTPS 에서만 사용, 개발단계 : false, 배포단계 : true
                .sameSite("LAX") // CSRF 공격방어
                .build(); // 쿠키 생성 끝

        // 3. 응답 헤더에 쿠기 등록 , response.setHeader()
        response.setHeader(HttpHeaders.SET_COOKIE , cookie.toString());
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

