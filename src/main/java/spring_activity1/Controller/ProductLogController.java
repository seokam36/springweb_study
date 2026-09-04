package spring_activity1.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring_activity1.Dto.ProductLogDto;
import spring_activity1.Service.ProductLogService;

import java.util.List;

@RequestMapping("/productLog")
@RestController
@RequiredArgsConstructor
public class ProductLogController {
    private final ProductLogService service;

    // 로그 저장
    @PostMapping
    public boolean save(@RequestBody ProductLogDto productLogDto){
        return service.save(productLogDto);
    }
    // 로그 전체 조회
    @GetMapping
    public List<ProductLogDto> findAll(){
        return service.findAll();
    }

    // 로그 개별 조회
    @GetMapping("/detail")
    public List<ProductLogDto> findById(@RequestParam int no){
        return service.findById(no);
    }

    // 로그 수정
    @PutMapping
    public boolean update(@RequestBody ProductLogDto productLogDto){
        return service.update(productLogDto);
    }

    // 로그 삭제
    @DeleteMapping
    public boolean delete(@RequestParam int no){
        return service.delete(no);
    }
}
