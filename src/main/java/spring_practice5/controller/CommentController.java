package spring_practice5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring_practice5.dto.CommentDto;
import spring_practice5.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public boolean save(@RequestBody CommentDto commentDto){
        return commentService.save(commentDto);
    }

    @DeleteMapping
    public boolean delete(@RequestParam Integer commentId, @RequestParam String password){
        return commentService.delete(commentId, password);
    }
}
