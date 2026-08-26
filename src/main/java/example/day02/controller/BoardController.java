package example.day02.controller;

import example.day02.model.dao.BoardDao;
import example.day02.model.dto.BoardDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/*
* 컨트롤러에 서블릿( HTTP 프로토콜 사용 가능하게 기능 제공하는 클래스) 기능 달기
*
* 1. 웹기술 포함할 컨트롤럴 클래스위에 @Controller 또는 JSON반환이면 @RestController
*   HTTP content type : text/html, application/json, form 등등
* 2. 해당 메소드 마다의 URL 정의
*   * url 정의 -> http://127.0.0.1:8080(도메인) 이후 경로 정의 , 중복없이
*   1) @PostMapping("/url") : HTTP 메소드중에 Post 메소드 매핑 어노테이션
*
*/

@RestController
public class BoardController {
    private BoardDao bd = BoardDao.getInstance();

    // [1] 등록
    @PostMapping("/board/save") // HTTP 메소드중에 Post 메소드 매핑 어노테이션
    public boolean save(BoardDto boardDto){
        return bd.save(boardDto);
    }

    // [2] 전체 조회
    @GetMapping("/board/findAll")
    public ArrayList<BoardDto> findAll(){
        return bd.findAll();
    }

    // [3] 개별 수정
    @PutMapping("/board/update")
    public boolean update(BoardDto boardDto){
        return bd.update(boardDto);
    }

    // [4] 개별 삭제
    @DeleteMapping("/board/delete")
    public boolean delete(int no){
        return bd.delete(no);
    }
}
