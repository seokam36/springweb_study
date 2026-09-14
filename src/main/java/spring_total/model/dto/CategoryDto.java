package spring_total.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import spring_total.model.entity.CategoryEntity;
import spring_total.model.entity.ProductEntity;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString @Builder
public class CategoryDto {
    private Integer cno;
    private String name;

    // private LocalDateTime createAt;
    // private LocalDateTime updateAt;

    // 제품 목록
    @Builder.Default
    private List<ProductEntity> products = new ArrayList<>();

    public CategoryEntity dtoToEntity(){
        return CategoryEntity.builder()
                .cno(this.cno)
                .name(this.name)
                .build();
    }

    public static CategoryDto entityToDto(CategoryEntity categoryEntity){
        return CategoryDto.builder()
                .cno(categoryEntity.getCno())
                .name(categoryEntity.getName())
                // .createAt(categoryEntity.getCreateAt())
                // .updateAt(categoryEntity.getUpdateAt())
                .build();
    }
}
