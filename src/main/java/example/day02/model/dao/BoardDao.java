package example.day02.model.dao;

import example.day02.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDao extends BaseDao{
    private BoardDao(){};
    private static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance() {
        return instance;
    }

    // [1] 등록
    public boolean save(BoardDto boardDto){
        // 1. SQL 작성
        String sql = "insert into board(content, writer) values (?,?)";
        // 2. SQL 기재 (SQL은 자바가 아니고 외부(MYSQL서버) 전달)
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. 기재된 SQL에 매개변수 대입 , ps.setXXX
            ps.setString(1,boardDto.getContent());
            ps.setString(2,boardDto.getWriter());
            // 4. 기재된 SQL 실행 , ps.execute() / ps.executeUpdate
            int result = ps.executeUpdate();
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }
        return false;
    }
}
