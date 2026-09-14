package spring_total.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring_total.model.dto.CategoryDto;
import spring_total.service.CategoryService;


@RestController
@RequestMapping("/api/categories")
@CrossOrigin(value = "http://localhost:5174")
public class CategoryController {
    @Autowired private CategoryService categoryService;

    @PostMapping("")
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("")
    public List<CategoryDto> printCategory() {
        return categoryService.printCategory();
    }

    @DeleteMapping("")
    public boolean deleteCategory(@RequestParam (name="cno") Integer cno){
        return categoryService.deleteCategory(cno);
    }
}
