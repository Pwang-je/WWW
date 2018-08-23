<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%> <!--자바가 아니라 jsp다  json은 html이므로 text/plain으로 해줘야함.-->
<%@ page language="java" contentType="text/plain; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!--  자바문법시작. xml과 다르게 자바문법이므로 while밑에 %>없다. -->
{"mysang:
[
<%

	request.setCharacterEncoding("utf-8");

	Connection conn = null;
	PreparedStatement pstmt= null;
	ResultSet rs = null;
	String result=""; //문자열더하기

    try {
        String orcDriver = "oracle.jdbc.driver.OracleDriver";
        Class.forName(orcDriver);   // Driver loading.
    } catch (Exception e) {
        System.out.println("Connection err: " + e);
        return;
    }
try{
	conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
    pstmt = conn.prepareStatement("SELECT * FROM SANGDATA");
    rs = pstmt.executeQuery();
//	Thread.sleep(2000);
	while(rs.next()){

	result +="{";
	result +="\"code\":" + "\"" + rs.getString("code") + "\",";
	result +="\"sang\":" + "\"" + rs.getString("sang") + "\",";
	result +="\"su\":" + "\"" + rs.getString("su") + "\",";
	result +="\"dan\":" + "\"" + rs.getString("dan") + "\"";
	result +="},";

	}
	if(result.length()>0){
		result = result.substring(0,result.length() -1);  /*마지막,빼기  */
	}
	out.println(result);
	rs.close();
	pstmt.close();
	conn.close();
}catch(Exception e){
	System.out.println("처리실패" + e);
	return;
}
%>
]
}