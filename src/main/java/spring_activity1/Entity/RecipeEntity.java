package spring_activity1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Recipe")
@Data @NoArgsConstructor @AllArgsConstructor
public class RecipeEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipe_no;

    private Integer recipe_order;

    @ManyToOne
    @JoinColumn(name = "product_no")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "menu_no")
    private MenuEntity menuEntity;

}

