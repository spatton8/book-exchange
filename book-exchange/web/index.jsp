<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>UGA Book Exchange</title>
	</head>
	<style>
	table
	{
	margin-left:40%;
	}
	</style>
	
	<body>
		<h1 style="text-align:center">UGA Book Exchange</h1><br>
		<p style="text-align:right">
			<a href="login.jsp">login</a>
			<a href="signup.jsp">signup</a>
		</p>
	
		<p style="text-align:center">
			<form style="text-align:center">
				<input type="text" placeholder="Seach for a book" />
				<input type="submit" value="Search" /> 
			</form>
			<br>
			<form style="text-align:center">
				<select name="department">
					<option selected="selected">Browse by department</option>
					<option value="CSCI">CSCI</option>
					<option value="ANTH">ANTH</option>
				</select>
				<input type="submit" value="Search" />
			</form>
		</p>
		<table> 
			<tr>
			<td></td>
			<td></td>
			<td></td>
			<td><a href="sell.jsp">sell a book</a></td>
			<td><a href="trade.jsp">trade a book</a></td>
			</tr>
		</table>	
	
	</body>
</html>