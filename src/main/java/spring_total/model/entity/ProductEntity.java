package spring_total.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dno;

    private String name;
    private Integer price;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "c_no")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "reviewEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<ReviewEntity> reviewEntities = new ArrayList<>();
}
