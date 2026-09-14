package spring_total.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_total.model.dto.ProductDto;
import spring_total.model.dto.ProductResponseDto;
import spring_total.model.entity.CategoryEntity;
import spring_total.model.entity.ProductEntity;
import spring_total.repository.CategoryRepository;
import spring_total.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = productDto.dtoToEntity();
        Optional<CategoryEntity> byId = categoryRepository.findById(productDto.getCno());
        if (byId.isPresent()){
            CategoryEntity categoryEntity = byId.get();
            productEntity.setCategoryEntity(categoryEntity);
            productRepository.save(productEntity);
            return productDto;
        }
        return null;
    }

    public List<ProductResponseDto> findAll() {
        List<ProductEntity> all = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        all.forEach(productEntity -> {
            productResponseDtos.add(ProductResponseDto.dtoToEntity(productEntity));
        });
        return productResponseDtos;
    }

    public boolean update(ProductDto productDto) {
        ProductEntity productEntity = productDto.dtoToEntity();
        Optional<ProductEntity> byId = productRepository.findById(productDto.getBno());
        if (byId.isPresent()){
            ProductEntity entity = byId.get();
            entity.setName(productEntity.getName());
            entity.setPrice(productEntity.getPrice());
            Optional<CategoryEntity> byId1 = categoryRepository.findById(productDto.getCno());
            if (byId1.isPresent()){
                CategoryEntity categoryEntity = byId1.get();
                entity.setCategoryEntity(categoryEntity);
            }
            return true;
        }
        return false;
    }

    public boolean delete(Integer bno) {
        Optional<ProductEntity> byId = productRepository.findById(bno);
        if (byId.isPresent()){
            productRepository.deleteById(bno);
            return true;
        }
        return false;
    }
}
