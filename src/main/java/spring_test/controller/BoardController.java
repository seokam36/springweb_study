package spring_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring_test.model.dto.BoardDto;
import spring_test.service.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public boolean save(@RequestBody BoardDto boardDto){
        return boardService.save(boardDto);
    }

    @GetMapping
    public List<BoardDto> findAll(){
        return boardService.findAll();
    }

    @DeleteMapping
    public boolean delete(@RequestParam Integer id, @RequestParam String password){
        return boardService.delete(id,password);
    }
}
