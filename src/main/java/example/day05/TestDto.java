package example.day05;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDto { // 서로 계층간 이동객체 (controller에서는 엔티티 사용금지)
    // 엔티티와 동일하게 멤버변수 구성 : 기능별로 DTO 구성 ex) 등록DTO, 조회DTO
    private Integer no;
    private String name;
    private String descri;
    private Integer price;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // DTO --> Entity , 주로 save/update
    public TestEntity toEntity(){
        return TestEntity.builder().name(this.name)
                .descri(this.descri).price(this.price).build();
    }

    // Entity --> DTO
    public static TestDto from(TestEntity testEntity){
        return TestDto.builder().name(testEntity.getName()).descri(testEntity.getDescri()).no(testEntity.getNo())
                .price(testEntity.getPrice()).createDate(testEntity.getCreateDate()).updateDate(testEntity.getUpdateDate()).build();
    }

}
