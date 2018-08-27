
import java.io.PrintWriter;

public class ServletPoham {
    private String irum;

    public ServletPoham(String irum) {
        this.irum = irum;
    }
    public String getIrum() {
        return irum;
    }

    public void display(int n, PrintWriter out) {   // 자바에서 서블렛 out 할때, printwriter out 써줌.
        int su = 10 / n;
        out.println("<br>연산결과 : " + su);
    }
}
