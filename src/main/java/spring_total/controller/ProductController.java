package spring_total.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring_total.model.dto.ProductDto;
import spring_total.model.dto.ProductResponseDto;
import spring_total.service.ProductService;

import java.util.List;

@CrossOrigin(value = "http://localhost:5174")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto){
        return productService.save(productDto);
    }

    @GetMapping
    public List<ProductResponseDto> findAll(){
        return productService.findAll();
    }

    @PutMapping
    public boolean update(@RequestBody ProductDto productDto){
        return productService.update(productDto);
    }

    @DeleteMapping
    public boolean delete(@RequestParam Integer bno){
        return productService.delete(bno);
    }
}
