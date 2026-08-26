package example.day02.model.dao;

public class BoardDao extends BaseDao{
    private static final BoardDao instance = new BoardDao();

    public static BoardDao getInstance() {
        return instance;
    }
    private BoardDao(){};
}
