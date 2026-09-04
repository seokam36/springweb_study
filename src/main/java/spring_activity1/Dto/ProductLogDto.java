package spring_activity1.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_activity1.Entity.ProductEntity;
import spring_activity1.Entity.ProductLogEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductLogDto {
    private Integer productlog_no;
    private Integer product_qty;
    private String product_condition;
    private Integer productlog_price;
    private Integer customerlog_day;
    private Integer product_no;

    public ProductLogEntity dtoToEntity(){
        return new ProductLogEntity(null,this.product_qty,this.product_condition,this.productlog_price,
                this.customerlog_day,null);
    }
    public static ProductLogDto entityToDto(ProductLogEntity entity){
        return new ProductLogDto(entity.getProductlog_no(), entity.getProduct_qty(), entity.getProduct_condition(),
                entity.getProductlog_price(),entity.getCustomerlog_day(),entity.getProductEntity().getProduct_no());
    }
}
