package example.day09.controller;

import example.day09.model.dto.ApiDto;
import example.day09.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;

    @GetMapping
    public List<ApiDto> findAll(){
        return apiService.findAll();
    }

    @PostMapping
    public boolean save(@RequestBody ApiDto apiDto){
        return apiService.save(apiDto);
    }
}
