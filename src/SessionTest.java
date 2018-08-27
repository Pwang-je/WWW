import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SessionTest")
public class SessionTest extends HttpServlet {
    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Session : 각 클라이언트의 정보를 웹서버 RAM 에 저장.
        HttpSession session = request.getSession(true); // true = 세션이 있으면 읽고, 없으면 생성.
//        HttpSession session = request.getSession(false); // false = 세션이 있으면 읽고, 없으면 생성 안함.
        session.setMaxInactiveInterval(30); // 30초간 유효.
        if (session != null) {
            session.setAttribute("name", "james");
        }

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("session id : " + session.getId());
        out.println("<br>name 키로 설정된 값 : " + session.getAttribute("name"));
        out.println("</body></html>");

    }
}
