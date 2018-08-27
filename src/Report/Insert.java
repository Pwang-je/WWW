package Report;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Insert")
public class Insert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        int number = Integer.parseInt(request.getParameter("number"));
        String name = request.getParameter("name");
        int kor = Integer.parseInt(request.getParameter("kor"));
        int eng = Integer.parseInt(request.getParameter("eng"));
        int total = 0;
        total = kor + eng;

        HttpSession session = request.getSession(true);
        ArrayList<report_DTO> rDto = (ArrayList<report_DTO>)session.getAttribute("list");
        if (rDto == null) {
            rDto = new ArrayList<>();
            rDto.add(new report_DTO(number, name, kor, eng, total));
            session.setAttribute("list", rDto);
        }
        session.setMaxInactiveInterval(3600);   // 저장 1시간.

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>학생들 성적표</h2>");
        out.println("<table width='70%'>");
        out.println("<table border='1px solid black'>");
        out.println("<tr><th>번호</th><th>이름</th><th>국어</th><th>영어</th><th>총점</th></tr>");
        out.println("<tr><td>" + number + "</td>");
        out.println("<td>" + name + "</td>");
        out.println("<td>" + kor + "</td>");
        out.println("<td>" + eng + "</td>");
        out.println("<td>" + total + "</td>");
        out.println("</table><br>");
        out.println("<input type='button' value='새로입력' onclick='history.back()'>");
        out.println("</body></html>");
        out.close();
    }


    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
}
