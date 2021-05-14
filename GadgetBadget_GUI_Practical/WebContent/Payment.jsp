<%@page import="model.PaymentServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Payment Management - GadgetBadget</title>
	
		<link href="myStyle.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/Payment.js"></script>
		   <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">


	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><u><i><b>Payment Management - GadgetBadget</b></i></u></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>Add Payment Details</b></legend>
					<form id="PAYMENT" name="PAYMENT" class="border border-light p-5">
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Product Price:</label>
						    <input type="hidden" id="paymentID" name="paymentID">
						    <input type="text" id="paymentPrice" class="form-control" name="paymentPrice">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Card Type:</label>
								<select id="cardType" name="cardType">
    								<option value="credit">Credit Card</option>
   			 						<option value="debit">Debit Card</option>
    							</select>						    
						</div>
					
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Name on Card:</label>
						    <input type="text" id="paymentName" class="form-control" name="paymentName">						    
						</div>

						 
						<div class="row mb-4">
						    <div class="col">
						      <div class="form-outline">
						        <label class="form-label" for="form6Example1" class="col-sm-2 col-form-label col-form-label-sm">Card No:</label>
						        <input type="text" id="paymentCardNo" class="form-control" name="paymentCardNo">						        
						      </div>
						    </div>
						    <div class="col">
						      <div class="form-outline">
						        <label class="form-label" for="form6Example1" class="col-sm-2 col-form-label col-form-label-sm">Expire Date:</label>
						        <input type="" id="startDate"  class="form-control" placeholder="MM.YY" name="startDate">						        
						      </div>
						    </div>
						    <div class="col">
						      <div class="form-outline">
								<label class="form-label" for="form6Example2" class="col-sm-2 col-form-label col-form-label-sm">CVC:</label>
						        <input type="text" id="paymentCvc" class="form-control" name="paymentCvc" aria-describedby="passwordHelpInline" placeholder="3 digit security code">
						    
						      </div>
						    </div>
						  </div>						
						<br> 
						
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>	
						
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						
					</form>
				
							
			</fieldset>
			
			<br> 
			
			<div class="container" id="PaymentGrid">
				<fieldset>
					<legend><b>View Payment Details</b></legend>
					<form method="post" action="Payment.jsp" class="table table-striped">
						<%
						    PaymentServlet viewPayment = new PaymentServlet();
							out.print(viewPayment.readPayments());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>