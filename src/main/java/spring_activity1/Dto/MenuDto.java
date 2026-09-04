package spring_activity1.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_activity1.Entity.MenuEntity;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDto {
    private Integer menu_no;
    private String menu_name;
    private Integer menu_price;

    public MenuEntity dtoToEntity(){
        return new MenuEntity(null, this.menu_name, this.menu_price, new ArrayList<>());
    }

    public static MenuDto entityToDto(MenuEntity entity){
        return new MenuDto(entity.getMenu_no(),entity.getMenu_name(), entity.getMenu_price());
    }
}
