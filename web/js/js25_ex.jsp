<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/plain; charset=EUC-KR" pageEncoding="UTF-8"%>

{"jikwon":
[
<%
	request.setCharacterEncoding("utf-8");

	Connection conn = null;
	PreparedStatement pstmt= null;
	ResultSet rs = null;
	String result=""; //문자열더하기

    try{
        String orcDriver = "oracle.jdbc.driver.OracleDriver";
        Class.forName(orcDriver);   // Driver loading.
    }catch(Exception e){
        System.out.println("Connection err~ err~ err~: " + e);
        return;
    }
    try{
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        pstmt = conn.prepareStatement("SELECT * FROM JIKWON");
//        SELECT JIKWON_NO, JIKWON_NAME, JIKWON_JIK, JIKWON_GEN
//        FROM JIKWON INNER JOIN BUSER
//              ON BUSER_NUM = BUSER_NO;
//        pstmt.setString(1, "jik");
        rs = pstmt.executeQuery();

        while(rs.next()){
            result +="{";
            result +="\"no\":" + "\"" + rs.getString("JIKWON_NO") + "\",";
            result +="\"name\":" + "\"" + rs.getString("JIKWON_NAME") + "\",";
            result +="\"jik\":" + "\"" + rs.getString("JIKWON_JIK") + "\",";
            result +="\"gen\":" + "\"" + rs.getString("JIKWON_GEN") + "\"";
            result +="},";
        }

        if(result.length()>0){
            result = result.substring(0, result.length()-1);  /*마지막,빼기  */
        }
        out.println(result);
        rs.close();
        pstmt.close();
        conn.close();
    }catch(Exception e){
        System.out.println("Handling err~ err~ err~: " + e);
        return;
    }
%>
]
}