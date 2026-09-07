package spring_practice4.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_practice4.Dto.EnrollDto;
import spring_practice4.Service.EnrollService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enroll")
public class EnrollController {
    private final EnrollService enrollService;

    @PostMapping
    public boolean save(@RequestBody EnrollDto enrollDto){
        return enrollService.save(enrollDto);
    }

    @GetMapping("/detail")
    public EnrollDto findById(@RequestParam Integer enrollId){
        return enrollService.findById(enrollId);
    }
}
