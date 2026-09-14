package spring_total.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_total.model.entity.ProductEntity;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class ProductDto {
    private Integer bno;

    private String name;
    private Integer price;

    private Integer cno;

    public ProductEntity dtoToEntity(){
        return ProductEntity.builder()
                .name(this.name)
                .price(this.price).build();
    }

    public static ProductDto entityToDto(ProductEntity productEntity){
        return ProductDto.builder()
                .bno(productEntity.getBno())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .cno(productEntity.getCategoryEntity().getCno()).build();
    }

}
