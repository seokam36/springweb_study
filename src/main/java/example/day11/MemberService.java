package example.day11;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    // ***  비크립트(단방향 암호화 사용) 라이브러리 객체주입
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // [1] 회원가입
    public boolean signup(MemberDto memberDto){
        // 1) 회원가입
        MemberEntity memberEntity = memberDto.toEntity();
        // *** 저장하기전에 평문(원본 비밀번호) -> 암호문으로 변환
        // passwordEncoder.encode("평문")
        memberEntity.setMpwd(passwordEncoder.encode(memberDto.getMpwd())); // 암호문으로 변경해서 엔티티에 pwd저장
        MemberEntity savedEntity = memberRepository.save(memberEntity);
        if (savedEntity.getMno() >= 1){
            return true;
        }
        return false;
    }

    // [2] 로그인
    public MemberDto login(MemberDto memberDto){
        // 입력받은 아이디가 존재하는지 검증, findByMid 추상정의한 메소드 사용
        MemberEntity memberEntity = memberRepository.findByMid(memberDto.getMid());
        if (memberEntity == null){ // 아이디 불일치
            return null;
        }
        // 존재하면 *** 평문과 암호문 비교!!!
        //passwordEncoder.matches(평문, 암호문)
        boolean matches = passwordEncoder.matches(memberDto.getMpwd(), memberEntity.getMpwd());
        if (matches == false){ // 비밀번호 불일치
            return null;
        }

        // 아이디, 비밀번호 일치하면 dto로 변환해서 반환, 로그인 전용 loginDto 있으면 좋다 (패스워드는 노출 X)
        return MemberDto.from(memberEntity);
    }

    // [3] 내 정보 조회(PK:회원번호)
    public MemberDto getMyInfo(Long mno){
        // 1) 컨트롤러에게 조회할 회원번호 받기
        // 2) findById
        Optional<MemberEntity> byId = memberRepository.findById(mno);
        if (byId.isPresent()){
            MemberEntity memberEntity = byId.get();
            return MemberDto.from(memberEntity);
        }
        return null;
    }
}
