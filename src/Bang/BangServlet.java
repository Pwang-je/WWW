package Bang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "BangServlet")
public class BangServlet extends HttpServlet {

    Connection conn = null;
    PreparedStatement pstmt= null;
    String result=""; //문자열더하기

    @Override
    public void init() throws ServletException {
        try {
            String orcDriver = "org.mariadb.jdbc.Driver";
            Class.forName(orcDriver);   // Driver loading.
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test",
                    "root", "123");
            pstmt = conn.prepareStatement("INSERT INTO GUEST(NAME, SUBJECT, CONTENTS) VALUES = (?,?,?)");
        } catch(Exception e){
            System.out.println("Connection err~ err~ err~: " + e);
            return;
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String name = request.getParameter("name");
        String subject = request.getParameter("subject");
        String contents = request.getParameter("contents");

        try {
            pstmt.setString(1, name);
            pstmt.setString(2, subject);
            pstmt.setString(3, contents);
            pstmt.executeUpdate();
            response.sendRedirect("bang/bang_main.html");   // 이게 꼭 필요함.
        } catch (Exception e) {
            System.out.println("doPoest err~ : " + e);
        }

    }

    @Override
    public void destroy() {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {

        }
    }
}
