package model;


import java.sql.*;
public class PaymentServlet
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.cj.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useTimezone=true&serverTimezone=UTC", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertPayment(String price, String cardType, String name, String cardno, String exdate, String cvc)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into payments(`paymentPrice`, `cardType`, `paymentName`,`paymentCardNo`,`paymentExDate`,`paymentCvc`)"
 + " values (?, ?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values

 preparedStmt.setDouble(1, Double.parseDouble(price));
 preparedStmt.setString(2, cardType);
 preparedStmt.setString(3, name);
 preparedStmt.setString(4, cardno);
 preparedStmt.setString(5, exdate);
 preparedStmt.setString(6, cvc);
// execute the statement
 preparedStmt.execute();
 con.close();
 
 String newPayment = readPayments(); 
 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}"; 
} 
catch (Exception e) 
{ 
output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment details.\"}";
System.err.println(e.getMessage());
} 
return output; 
}

public String readPayments()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Payment ID</th><th>Price</th><th>Card Type</th><th>Name on Card</th><th>Card Number</th>" +
 "<th>Expire Date</th>" +
 "<th>CVC</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from payments";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String paymentID = Integer.toString(rs.getInt("paymentID"));
 String paymentPrice = Double.toString(rs.getDouble("paymentPrice"));
 String cardType = rs.getString("cardType");
 String paymentName = rs.getString("paymentName");
 String paymentCardNo = rs.getString("paymentCardNo");
 String paymentExDate = rs.getString("paymentExDate");
 String paymentCvc = rs.getString("paymentCvc");
 // Add into the html table
 output += "<tr class='text-center'><td>" + paymentID + "</td>";
 output += "<td>" + paymentPrice + "</td>";
 output += "<td>" + cardType + "</td>";
 output += "<td>" + paymentName + "</td>";
 output += "<td>" + paymentCardNo + "</td>";
 output += "<td>" + paymentExDate + "</td>";
 output += "<td>" + paymentCvc + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
		 + "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ paymentID +"' >Remove</button></td></tr>";
} 

 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the payment details.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updatePayment(String ID, String price, String cardType, String name, String cardno, String exdate, String cvc)
{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE payments SET paymentPrice=?, cardType=?,paymentName=?,paymentCardNo=?,paymentExDate=?,paymentCvc=? WHERE paymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setDouble(1, Double.parseDouble(price));
	 preparedStmt.setString(2, cardType);
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, cardno);
	 preparedStmt.setString(5, exdate);
	 preparedStmt.setString(6, cvc);
	 preparedStmt.setInt(7, Integer.parseInt(ID));

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newPayment = readPayments();
	 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the payment details.\"}"; 
		 System.err.println(e.getMessage()); 
	 } 
	 	return output; 
	 }

	public String deletePayment(String paymentID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from payments where paymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(paymentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newPayment = readPayments(); output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment details.\"}"; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
} 
	} 
