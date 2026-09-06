package spring_activity1.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu")
@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class MenuEntity extends BaseTime{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer menu_no;

    @Column(length = 50)
    private String menu_name;

    private Integer menu_price;

    @OneToMany(mappedBy = "menuEntity")
    @ToString.Exclude
    private List<RecipeEntity> recipeList = new ArrayList<>();
}
