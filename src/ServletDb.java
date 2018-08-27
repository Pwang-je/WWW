import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "ServletDb")
public class ServletDb extends HttpServlet {

    Connection conn = null;
    PreparedStatement pstmt= null;
    ResultSet rs = null;
    String result=""; //문자열더하기

    @Override
    public void init() throws ServletException {    // DB connect.
        try{
            String orcDriver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(orcDriver);   // Driver loading.
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                    "scott", "tiger");
            pstmt = conn.prepareStatement("SELECT * FROM SANGDATA");
        }catch(Exception e){
            System.out.println("Connection err~ err~ err~: " + e);
            return;
        }
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>상품자료</h2>");
        try {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                out.println(rs.getString("code") + " "
                + rs.getString("sang") + " "
                + rs.getString("su") + " "
                + rs.getString("dan"));
            }
        } catch (Exception e) {
            System.out.println("Service err~ err~ err~: " + e);
        }
        out.println("</body></html>");
        out.close();
    }

    @Override
    public void destroy() { // DB disconnect.
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {

        }
    }
}
