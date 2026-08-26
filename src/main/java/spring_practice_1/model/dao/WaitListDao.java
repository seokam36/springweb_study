package spring_practice_1.model.dao;

import spring_practice_1.model.dto.WaitListDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WaitListDao extends BaseDao {
    private WaitListDao(){}
    private static final WaitListDao instance = new WaitListDao();
    public static WaitListDao getInstance(){
        return instance;
    }

    public boolean save(WaitListDto waitListDto) {
        String sql = "insert into waitlist(telNum, people) value (?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, waitListDto.getTelNum());
            ps.setInt(2,waitListDto.getPeople());
            int result = ps.executeUpdate();
            if (result==1){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }
        return false;
    }

    public ArrayList<WaitListDto> findAllList() {
        ArrayList<WaitListDto> waitList = new ArrayList<>();
        String sql = "select * from waitlist";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                WaitListDto waitListDto = new WaitListDto();
                waitListDto.setNum(rs.getInt("num"));
                waitListDto.setTelNum(rs.getString("telNum"));
                waitListDto.setPeople(rs.getInt("people"));
                waitList.add(waitListDto);
            }
        } catch (SQLException e) {
            System.out.println("에외 발생 : " + e);
        }
        return waitList;
    }

    public boolean update(String telNum, int people) {
        String sql = "update waitlist set people = ? where telNum = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,people);
            ps.setString(2,telNum);
            int result = ps.executeUpdate();
            if (result==1){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }
        return false;
    }

    public boolean delete(String telNum) {
        String sql = "delete from waitlist where telNum = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,telNum);
            int result = ps.executeUpdate();
            if (result==1){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }

        return false;
    }
}
