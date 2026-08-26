package example.day01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 1. 해당 컨트롤러에게 HTTP(웹기술) 적용 <- 서블릿필요(톰캣)
// 2. 서블릿에게 상속받기 , extends HttpServlet
// 3. 물려받은 기능(init, service, destroy) 재정의 -> 오버라이딩
// 4. Http doXXX 메소드 오버라이딩 해서 기능 구현 -> 컨트롤러
// 5. 해당 컨트롤러에 http 주소 등록 , @WebServlet("/주소정의")
@WebServlet ("/example/day01")
public class BoardController extends HttpServlet {

    // [1] 서블릿이 최초 실행 된 경우 1번 실행되는 메소드
    @Override
    public void init() throws ServletException {
        super.init();
    }

    // [2] 서블릿이 생성되고 요청마다(스레드 할당받아) 실행되는 메소드
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    // [3] 서블릿이 사라질때(서버 종료될 때) 1번 실행되는 메소드 , 외부연동 해제
    @Override
    public void destroy() {
        super.destroy();
    }

    // HTTP METHOD CRUD
    // 1. doGet : Http 요청 Get이면
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    // 2. doPost : Http요청 Post
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    // 3. doPut
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    // 4. doDelete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
