package spring_activity1.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productlog")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductLogEntity
        extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productlog_no;

    private Integer product_qty;

    @Column(length = 20)
    private String product_condition;

    private Integer productlog_price;

    private Integer customerlog_day;

    @ManyToOne
    @JoinColumn(name = "product_no")
    private ProductEntity productEntity;
}

