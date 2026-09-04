package spring_activity1.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_activity1.Entity.ProductEntity;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Integer product_no;
    private String product_name;
    private Integer product_price;

    public ProductEntity dtoToEntity(){
        return new ProductEntity(null,this.product_name,this.product_price,new ArrayList<>());
    }

    public static ProductDto entityToDto(ProductEntity entity){
        return new ProductDto(entity.getProduct_no(), entity.getProduct_name(), entity.getProduct_price());
    }
}
