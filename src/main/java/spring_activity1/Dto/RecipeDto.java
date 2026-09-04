package spring_activity1.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_activity1.Entity.RecipeEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDto {
    private Integer recipe_no;
    private Integer recipe_order;
    private Integer product_no;
    private Integer menu_no;

    public RecipeEntity dtoToEntity(){
        return new RecipeEntity(this.recipe_no,this.recipe_order,null,null);
    }

    public static RecipeDto entityToDto(RecipeEntity entity){
        return new RecipeDto(entity.getRecipe_no(), entity.getRecipe_order(),
                entity.getProductEntity().getProduct_no(),entity.getMenuEntity().getMenu_no());
    }
}
