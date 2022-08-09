import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class RegistrationServlet extends HttpServlet implements Runnable{
public void run(){
try{
Thread.sleep(1000);
}
catch(Exception e){
System.out.println(e);
}
}
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String username = req.getParameter("user");
String password = req.getParameter("pwd");
int balance = Integer.parseInt(req.getParameter("bal"));
String email = req.getParameter("email");
try{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping?useSSL=false&allowPublicKeyRetrieval=true","root","hari@9RUSHI");
String q = "insert into cus2(name, email, balance, password) values(?,?,?,?)";
PreparedStatement stm = con.prepareStatement(q);
stm.setString(1, username);
stm.setString(2, email);
stm.setInt(3, balance);
stm.setString(4, password);
int x = stm.executeUpdate();
System.out.println("Data upadated sucessfully...." + x);
if(x > 0){
out.print("<html><br><br><center><img src = https://codeconvey.com/wp-content/uploads/2020/06/registration-successful-message-html.png?ezimgfmt=ng%3Awebp%2Fngcb2%2Frs%3Adevice%2Frscb2-2 width = 500 height = 400 <br></center></html>");
out.println("<h2><center>Registration Successfull</center></h2>");
RegistrationServlet r = new RegistrationServlet();
Thread th = new Thread(r);
th.start();
res.sendRedirect("login.html");
}
else{
out.println("<html>Register Not successful</html>");
}
con.close();}
catch(Exception e){
out.println("<html>Register Not successful</html>");
}}}