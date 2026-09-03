package spring_practice3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {
    private final MovieService service;
    // 영화등록
    @PostMapping
    public boolean save(@RequestBody MovieDto movieDto){
        return service.save(movieDto);
    }

    // 영화 전체 조회
    @GetMapping
    public List<MovieDto> findAll(){
        return service.findAll();
    }

    // 영화 개별 조회
    @GetMapping("/detail")
    public MovieDto findById(@RequestParam int movieId){
        return service.findById(movieId);
    }

    // 특정 영화 수정
    @PutMapping
    public boolean update(@RequestBody MovieDto movieDto){
        return service.update(movieDto);
    }

    // 특정 영화 삭제
    @DeleteMapping
    public boolean delete(@RequestParam int movieId){
        return service.delete(movieId);
    }
}
/*
[조건 5] 서비스 구현 : Service 클래스를 작성하여 아래 기능을 구현한다.
영화 등록: 새로운 영화 정보를 입력받아 DB에 저장
영화 전체 조회: 모든 영화 목록을 조회
영화 개별 조회: 영화번호(movieid)를 기준으로 특정 영화 상세 정보 조회
특정 영화 수정: 영화번호(movieid)를 기준으로 영화번호 외 모든 영화 정보 수정
특정 영화 삭제: 영화번호(movieid)를 기준으로 해당 영화 삭제
*/
