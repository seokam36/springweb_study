package spring_practice1.controller;

import org.springframework.web.bind.annotation.*;
import spring_practice1.model.dao.WaitListDao;
import spring_practice1.model.dto.WaitListDto;

import java.util.ArrayList;

@RestController
public class WaitListController {
    WaitListDao wd = WaitListDao.getInstance();

    @PostMapping("/waitList/save")
    public boolean save(WaitListDto waitListDto){
        return wd.save(waitListDto);
    }

    @GetMapping("/waitList/findAllList")
    public ArrayList<WaitListDto> findAllList(){
        return wd.findAllList();
    }

    @PutMapping("/waitList/update")
    public boolean update(String telNum, int people){
        return wd.update(telNum,people);
    }

    @DeleteMapping("/waitList/delete")
    public boolean delete(String telNum){
        return wd.delete(telNum);
    }
}
