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

@WebServlet(name = "shopping")
public class Cart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String pname = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        HttpSession session = request.getSession(true);
        ArrayList<Goods> glist = (ArrayList<Goods>)session.getAttribute("list");

        if (glist == null) {
            glist = new ArrayList<>();
            glist.add(new Goods(pname, price));
            session.setAttribute("list", glist);    // 첫번째 상품이 들어감.
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>☞" + pname + " 낚이셨습니다.");
        out.println("<h2>장바구니 결과</h2>");
        out.println("<a href='shop_name.html'>계속 쇼핑</a>");
        out.println("<br><a href='Buy'>결제하기</a><br>");
        out.println("<table width='70%'>");
        out.println("<table border='1px solid black'>");
        out.println("<tr><th>상품명</th><th>가격</th></tr>");
        for (int i = 0; i < glist.size(); i++) {
            Goods goods = glist.get(i);
            out.println("<tr><td>" + goods.getPname() + "</td>");
            out.println("<td>" + goods.getPrice() + "</td></tr>");
        }
        out.println("</body></html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
