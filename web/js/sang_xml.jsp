<?xml version="1.0" encoding="UTF-8"?>

<%@ page import="java.sql.*" %>
<%@ page contentType="text/xml; charset=UTF-8" language="java" %>

<sang>
<%
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        String orcDriver = "oracle.jdbc.driver.OracleDriver";
        Class.forName(orcDriver);   // Driver loading.
    } catch (Exception e) {
        System.out.println("Connection err: " + e);
        return;
    }

    try {
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        pstmt = conn.prepareStatement("SELECT * FROM SANGDATA");
        rs = pstmt.executeQuery();

        while (rs.next()) {
%>
        <Product>
            <code><%=rs.getString("CODE")%></code>
            <name><%=rs.getString("SANG")%></name>
            <qty><%=rs.getString("SU")%></qty>
            <unitp><%=rs.getString("DAN")%></unitp>
        </Product>
<%
        }
        rs.close();
        pstmt.close();
        conn.close();
    } catch (Exception e) {
    System.out.println("Handling err: " + e);
    return;
    }
%>
</sang>