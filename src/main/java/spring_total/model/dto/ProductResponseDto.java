package spring_total.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_total.model.entity.ProductEntity;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class ProductResponseDto {
    private Integer bno;
    private String name;
    private Integer price;
    private Integer cno;
    private String categoryName;

    public ProductEntity entityToDto(){
        return ProductEntity.builder()
                .name(this.name)
                .price(this.price)
                .build();
    }

    public static ProductResponseDto dtoToEntity(ProductEntity productEntity){
        return ProductResponseDto.builder()
                .bno(productEntity.getBno())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .cno(productEntity.getCategoryEntity().getCno())
                .categoryName(productEntity.getCategoryEntity().getName()).build();
    }
}
