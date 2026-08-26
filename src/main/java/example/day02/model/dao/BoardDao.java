package example.day02.model.dao;

import example.day02.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<BoardDto> findAll() {
        ArrayList<BoardDto> list = new ArrayList<>();
        String sql = "select * from board";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                BoardDto boardDto = new BoardDto();
                boardDto.setNo(rs.getInt("no"));
                boardDto.setContent(rs.getString("content"));
                boardDto.setWriter(rs.getString("writer"));
                list.add(boardDto);
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }
        return list;
    }

    public boolean update( BoardDto boardDto ){
        try{
            String sql = "update board set content = ? where no = ? ";// 1.1 SQL 작성
            PreparedStatement ps = conn.prepareStatement(sql); // 1.2 SQL 기재 *예외*
            ps.setString( 1 , boardDto.getContent() );// 1.3 SQL내 ? 매개변수대입
            ps.setInt( 2 , boardDto.getNo() );
            int result = ps.executeUpdate(); // 1.4 SQL 실행
            if( result == 1 ) return true; // 1.5 실행 결과 반환
        }catch( SQLException e ){ System.out.println( e ); }
        return false; // 1.5 실행 결과 반환
    }

    // [4] 개별삭제 DAO
    public boolean delete( int no ){
        try{ String sql = "delete from board where no = ?";
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setInt( 1 , no ); // SQL 문법내 첫번째 ? 에 매개변수 값 대입
            int result = ps.executeUpdate();
            if( result == 1 ) return true;
        }catch( SQLException e ){ System.out.println( e ); }
        return false;
    }


}
