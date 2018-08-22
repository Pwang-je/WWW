<?xml version="1.0" encoding="UTF-8"?>

<%@ page import="java.sql.* "%>
<%@ page contentType="text/xml; charset=UTF-8" language="java" %>

<jikwon>
<%
    request.setCharacterEncoding("utf-8");
    String jik = request.getParameter("jik");
    System.out.println(jik);


    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // Db connection.
    try {
        String orclDriver = "oracle.jdbc.driver.OracleDriver";
        Class.forName(orclDriver);
    } catch (Exception e) {
        System.out.println("Connection err: " + e);
        return;
    }

    try {
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        pstmt = conn.prepareStatement("SELECT * FROM JIKWON");
        rs = pstmt.executeQuery();

        while (rs.next()) {
%>
        <Jikwon>
            <no><%=rs.getString("JIKWON_NO")%></no>
            <name><%=rs.getString("JIKWON_NAME")%></name>
            <jik><%=rs.getString("JIKWON_JIK")%></jik>
            <pay><%=rs.getString("JIKWON_PAY")%></pay>
            <ibsail><%=rs.getString("JIKWON_IBSAIL")%></ibsail>
            <gen><%=rs.getString("JIKWON_GEN")%></gen>
        </Jikwon>
<%
        }
        rs.close();
        pstmt.close();
        conn.close();
    } catch (Exception e) {
        System.out.println("Handling err: " + e);
    }
%>
</jikwon>