import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class PaymentServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String user = req.getParameter("user");
String pwd = req.getParameter("password");
String itm1 = req.getParameter("itmid");
int itm = Integer.parseInt(itm1);
String quantity1 = req.getParameter("quantity");
int quantity = Integer.parseInt(quantity1);
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping?useSSL=false","root","hari@9RUSHI");
Statement stm = con.createStatement();
ResultSet rs = stm.executeQuery("select price from price where itmid ='"+itm+"'");
if(rs.next()){
String n1 = rs.getString(1);
int price1 = Integer.parseInt(n1);
String q = "update cus2 set balance = balance - '"+(price1 * quantity)+"' where name='"+user+"' and password = '"+pwd+"'";
int x = stm.executeUpdate(q);
if(x > 0){

out.print("<html><br><br><center><img src = https://png.pngtree.com/png-vector/20190228/ourmid/pngtree-check-mark-icon-design-template-vector-isolated-png-image_711429.jpg width = 400 height = 400 <br></center></html>");
out.println("<center><h2>Purchase Succesfull...</h2></center>");
}
else{
out.print("<html><br><br><center><img src = https://jumeirahroyal.com/wp-content/uploads/d7e50cb89c.png width = 400 height = 400 <br></center></html>");
out.println("<center><h2>Invalid Username or Password</h2></center>");
}
}
else{
out.print("<html><br><br><center><img src = https://jumeirahroyal.com/wp-content/uploads/d7e50cb89c.png width = 400 height = 400 <br></center></html>");
out.println("<center><h2>Invalid ItemId</h2></center>");
}
}
catch(Exception e){
out.print("<html><br><br><center><img src = https://jumeirahroyal.com/wp-content/uploads/d7e50cb89c.png width = 400 height = 400 <br></center></html>");
out.println("<center><h2>Insufficient Balance..... Purchase Failed</h2></center>");
}
}
}