package service;

import model.PaymentServlet;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Payments")
public class PaymentService
{
PaymentServlet paymentObj = new PaymentServlet();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readPayments()
{
	return paymentObj.readPayments();
}

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertPayment(@FormParam("paymentPrice") String paymentPrice,
@FormParam("cardType") String cardType,
@FormParam("paymentName") String paymentName,
@FormParam("paymentCardNo") String paymentCardNo,
@FormParam("paymentExDate") String paymentExDate,
@FormParam("paymentCvc") String paymentCvc)
{
String output = paymentObj.insertPayment(paymentPrice, cardType, paymentName, paymentCardNo, paymentExDate, paymentCvc);
return output;
}

@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updatePayment(String paymentData) 
{ 
	//Convert the input string to a JSON object 
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String paymentID = paymentObject.get("paymentID").getAsString();
	 String paymentPrice = paymentObject.get("paymentPrice").getAsString();
	 String cardType = paymentObject.get("cardType").getAsString();
	 String paymentName = paymentObject.get("paymentName").getAsString(); 
	 String paymentCardNo = paymentObject.get("paymentCardNo").getAsString(); 
	 String paymentExDate = paymentObject.get("paymentExDate").getAsString();   
	 String paymentCvc = paymentObject.get("paymentCvc").getAsString(); 
	 
	 String output = paymentObj.updatePayment(paymentID, paymentPrice, cardType, paymentName, paymentCardNo, paymentExDate, paymentCvc); 
	 return output; 
}


@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deletePayment(String paymentData) 
{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <paymentID>
	 String paymentID = doc.select("paymentID").text(); 
	 String output = paymentObj.deletePayment(paymentID); 
	 return output; 
}

}