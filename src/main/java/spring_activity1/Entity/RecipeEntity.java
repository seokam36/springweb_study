package spring_activity1.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Recipe")
@Data
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

