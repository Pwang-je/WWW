import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * Servlet implementation class PostData
 */
@WebServlet(name = "/PostData")
public class PostData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String irum = request.getParameter("name");
        System.out.println(irum);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>결과 확인</h2>");
        out.println("이름은: " + irum + "<br>");

        String juso[] = request.getParameterValues("addr");    // 여러개를 받을땐 배열로 받는데, 그때는 getparameter values 써줌.
        out.println("주소는: " + juso[0] + " " + juso[1] + "<br>");

        // checkbox !
        try {
            String lang[] = request.getParameterValues("lan");
            for (String a:lang) {
                out.print(a + " ");
            }
            out.println("<br>");
//            out.println("언어는: " + lang[0] + " " + lang[1] + " " + lang[2] + "<br>");
        } catch (Exception e) {
            out.println("<b> 하나 이상은 선택 안됨.</b>");
        }


        // radio !
        String trans = request.getParameter("tr");
        out.println("교통수단: " + trans);


        // select !
        String opr = request.getParameter("os");
        out.println("운영체제는: " + opr);


        // hidden
        String hby = request.getParameter("os");
        out.println("취미는: " + hby);

        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        try {
            id = URLEncoder.encode(id,"UTF-8"); // 암호화
            Cookie idCookie = new Cookie("id",id);
            idCookie.setMaxAge(60 * 60 * 24 * 365); //  쿠키 1년간 유효. 초,분,일,년

            pwd = URLEncoder.encode(pwd,"UTF-8"); // 암호화
            Cookie pwdCookie = new Cookie("pwd",pwd);
            pwdCookie.setMaxAge(60 * 60 * 24 * 365); //  쿠키 1년간 유효. 초,분,일,년

            response.addCookie(idCookie);   // 이러면 클라이언트 컴퓨터에 들어감.
            response.addCookie(pwdCookie);
        } catch (Exception e) {

        }

        if (id.equals("aa") && pwd.equals("11")) {
            out.println("로그인 성공");
        } else {
            out.println("로그인 실패");
        }


        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 쿠키를 읽어, 없으면 로그인 화면 출력.
        String id = null;
        String pwd = null;

        try {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                if (name.equals("id")) {
                    id = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
                if (name.equals("pwd")) {
                    pwd = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }

                if (id != null && pwd != null) {
                    out.println(id + "님 쿠키를 통해 로그인 되었음.");
                    out.close();
                    return;
                }
            }
        } catch (Exception e) {

        }

        out.println("<html><body>");
        out.println("** 로그인 **");
        out.println("<form method='post'>");
        out.println("id: <input type='text' name='id'><br>");
        out.println("pwd: <input type='text' name='pwd'><br>");
        out.println("<br><input type='submit' value='전송'>");
        out.println("</form>");
        out.println("</body></html>");


    }



}
