import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class LoginServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String acno = req.getParameter("acno");
String pwd = req.getParameter("pwd");
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping?useSSL=false&allowPublicKeyRetrieval=true","root","hari@9RUSHI");
Statement stm = con.createStatement();
ResultSet rs = stm.executeQuery("select * from cus2 where name='"+acno+"'and password='"+pwd+"'");
if(rs.next()){
res.sendRedirect("index.html");
}
else{
    out.print("<html><br><br><center><img src = https://qodeinteractive.com/magazine/wp-content/uploads/2021/11/Is-Having-Too-Many-Failed-Login-Attempts-in-WordPress-a-Problem.jpg width = 400 height = 400 <br></center></html>");

out.print("<center><h1>Username or password are wrong...</h1></center>");
}
con.close();
}catch(Exception e){
System.out.println(e.getMessage());
}
}
}