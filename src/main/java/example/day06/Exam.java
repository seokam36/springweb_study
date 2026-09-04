package example.day06;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class Exam {
    public static void main(String[] args) {
        // 1] 자유 카테고리 등록
        Category c1 = new Category(1,"자유",new ArrayList<>());
        // c1 참조 -> 1개 , c1 -> category(101번지)

        // 2] 자유 카테고리 게시물 작성
        Board b1 = new Board(1,"제목1",c1);
        // b1 참조 -> 1개 , b1 -> Board(101번지) -> Category(201번지)

        // b1은 c1을 알수가 있다 -> board에 category가 있으니까 , 단방향참조
        // c1은 b1을 알수가 없다 -> 양방향 참조 필요

        // 3] category에 board 넣기
        c1.getList().add(b1);
        // c1 -> category -> list(board)
        // c1을 통해 b1 참조 가능 : 양방향 참조

        System.out.println(b1);
        // b1->c1->b1->c1->b1 ~ 무한참조 , 순환참조
        // 양방향쪽에 @ToString.Exclude 주입한다
        // 실무에서는 양방향 비권장!
    }
}

@Data @NoArgsConstructor @AllArgsConstructor
class Board{
    private int bno;
    private String title;
    private Category category; // 참조 FK
}

@Data @NoArgsConstructor @AllArgsConstructor
class Category{
    private int cno;
    private String cname;

    @ToString.Exclude // toString 사용금지
    private List<Board> list = new ArrayList<>();
}
