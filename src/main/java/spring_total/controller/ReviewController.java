package spring_total.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import spring_total.model.dto.ReviewDto;
import spring_total.service.ReviewService;


@CrossOrigin(value = "http://localhost:5174")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired private ReviewService reviewService;

    // 1. 제품별 리뷰 조회 기능
    @GetMapping("")
    public List<ReviewDto> 제품별조회 (@RequestParam ( name = "bno") Integer bno){
        return reviewService.제품별조회(bno );
    }

    // 2. 제품 리뷰 등록 기능
    @PostMapping("")
    public boolean 제품리뷰등록(@RequestBody ReviewDto reviewDto) {
        return reviewService.제품리뷰등록(reviewDto);
    }

    // 3. 제품 리뷰 삭제 기능
    @DeleteMapping("")
    public boolean 리뷰삭제(@RequestParam(name = "rno") Integer rno){
        return reviewService.리뷰삭제(rno);
    }
}
