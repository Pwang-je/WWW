package Shopping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Buy")
public class Buy extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) return;

        ArrayList<Goods> glist = (ArrayList<Goods>)session.getAttribute("list");
        if (glist == null) return;

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>결제처리</h2>");
        out.println("<table width='70%'>");
        out.println("<table border='1px solid black'>");
        out.println("<tr><th>상품명</th><th>가격</th></tr>");
        int tot = 0;
        for (int i = 0; i < glist.size(); i++) {
            Goods goods = glist.get(i);
            out.println("<tr><td>" + goods.getPname() + "</td>");
            out.println("<td>" + goods.getPrice() + "</td></tr>");
            tot += goods.getPrice();
        }
        out.println("<tr><td colspan='2'>결제총액: " + tot + "</td></tr>");
        out.println("</table>");
        out.println("<br>이제 넌 필요없다!");
        out.println("<br><a href='shop_main.html'>새마음으로 쇼핑해라</a>");

        // 세션 삭제.
//        session.invalidate();   // 해당 고객이 모든 세션을 삭제ㅔ한다.
        session.removeAttribute("list");    // 특정 세션만 삭제.
        out.println("</body></html>");
        out.close();
    }
}
