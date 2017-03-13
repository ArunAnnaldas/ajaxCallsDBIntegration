<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	First Name : <input type="text" name="fn" id="firstName" oninput="ajaxCall()">
	<br><br>
	<div id="replaceText">text to be replaced</div>
	<script>
		function ajaxCall(){
			var ajaxRequestObject = new browserObject();
			ajaxRequestObject.onreadystatechange = function() {
				if (ajaxRequestObject.readyState == 4) {
					var data = ajaxRequestObject.responseText;
					document.getElementById("replaceText").firstChild.nodeValue = data;
				}
			}
			ajaxRequestObject.open('POST','TestingAjaxCall', true);
			ajaxRequestObject.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			ajaxRequestObject.send("firstName=" +document.getElementById("firstName").value);
		}
		
		function autoSuggest(){
			var ajaxRequestObject2 = new browserObject();
			ajaxRequestObject2.onreadystatechange = function() {
				if (ajaxRequestObject2.readyState == 4) {
					var availableData = ajaxRequestObject2.responseText;
				    $("#firstName").autocomplete({
				        source: availableData
				      });
					//document.getElementById("replaceText").firstChild.nodeValue = data;
				}
			}
			ajaxRequestObject2.open('POST','TestingAjaxCall', true);
			ajaxRequestObject2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			ajaxRequestObject2.send("firstName=" +document.getElementById("firstName").value);
		}
		
		function browserObject(){
			var activexmodes=["Msxml2.XMLHTTP", "Microsoft.XMLHTTP"]
			if (window.ActiveXObject){
				for (var i=0; i<activexmodes.length; i++){
					try{
						return new ActiveXObject(activexmodes[i])
					}
					catch(e){
						return null;
						window.alert("Failed in creating ActiveXObject for IE browser")
					}
				}
			}
			else if (window.XMLHttpRequest)
				return new XMLHttpRequest()
			else
				return false;
		}
	</script>
</body>
</html>