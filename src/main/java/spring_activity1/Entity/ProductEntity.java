package spring_activity1.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_no;

    private String product_name;

    private Integer product_price;

    @OneToMany(mappedBy = "productEntity")
    @ToString.Exclude
    private List<ProductLogEntity> productLogList = new ArrayList<>();

}
