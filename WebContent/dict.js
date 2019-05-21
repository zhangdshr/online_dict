/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
	$('#btnLookup').click(function() {
		wordLookup();
	});

	$('#txtWord').keypress(function(e) {
		var key = e.which;
		if (key === 13) {
			wordLookup();
			return false;
		}
	});

	$(".loader").hide().ajaxStart(function() {
		$(this).show();
	}).ajaxStop(function() {
		$(this).hide();
	});
});

function wordLookup() {
	var value = $('#txtWord').val();
	if (!value) {
		$(".output").text("Please insert value in term section!!");
		return;
	}

	$.get("dictServlet", {
		value : value
	}).done(myAjaxSuccessFunction).fail(ajaxFailure);
}

function myAjaxSuccessFunction(responseJson) {
	$('.output').empty();
	console.log(responseJson);
	var json_obj = $.parseJSON(responseJson);//parse JSON
	console.log(json_obj);
	var output = "<table>";
	var count = 0;
	for ( var i in responseJson) {
		output += "<tr>";
		output += "<th>" + responseJson[i].wordtype + "</th>" + "<th>"
				+ responseJson[i].definition + "</th>";
		output += "</tr>";
		count++;
	}
	output += "</table>";
	if (count === 0)
		output += "<p>==== Sorry, no records found =====</p>";
	console.log(output);
	$('.output').html(output);
}

function ajaxFailure(xhr, status, exception) {
	alert("error: " + data + " status: " + status + " er:" + er);
	console.log(xhr, status, exception);
}