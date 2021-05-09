//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidPaymentIDSave").val("");
	$("#PAYMENT")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "PaymentAPI",
		type : type,
		data : $("#PAYMENT").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#PaymentGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidPaymentIDSave").val("");
	$("#PAYMENT")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "PaymentAPI",
		type : "DELETE",
		data : "paymentID=" + $(this).data("paymentID"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#PaymentGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
			$("#hidPaymentIDSave").val($(this).data("paymentID"));
			$("#paymentPrice").val($(this).closest("tr").find('td:eq(0)').text());
			$("#cardType").val($(this).closest("tr").find('td:eq(1)').text());
			$("#paymentName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#paymentCardNo").val($(this).closest("tr").find('td:eq(3)').text());
			$("#paymentExDate").val($(this).closest("tr").find('td:eq(4)').text());
			$("#paymentCvc").val($(this).closest("tr").find('td:eq(5)').text());	
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	
	// Payment Price
	if ($("#paymentPrice").val().trim() == "") {
		return "Please insert Payment Price.";
	}
	
	// Card Type
	if ($("#cardType").val().trim() == "") {
		return "Please insert Card Type.";
	}

	// Name on Card
	if ($("#paymentName").val().trim() == "") {
		return "Please insert Name on Card.";
	}
	
	// Card No
	if ($("#paymentCardNo").val().trim() == "") {
		return "Please insert Card No.";
	}
	
	// Card Expire Date
	if ($("#paymentExDate").val().trim() == "") {
		return "Please insert Card Expire Date.";
	}
	
	// Card CVC
	if ($("#paymentCvc").val().trim() == "") {
		return "Please insert CVC.";
	}
	
	return true;
}