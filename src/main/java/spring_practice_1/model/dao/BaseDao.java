package spring_practice_1.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {

    private String url = "jdbc:mysql://127.0.0.1:3306/mydb0826_practice";
    private String user = "root";
    private String password = "1234";

    protected Connection conn;

    private void connect( ){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password );
        }catch( Exception e ){ System.out.println("DB연동실패 : " + e);}
    }

    protected BaseDao(){ connect(); }
}
