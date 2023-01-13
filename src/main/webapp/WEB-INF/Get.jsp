<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Table</title>
</head>
<body><a href='Create'>Add a Contact</a><br>
	<table border="1">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Phone Number</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userEntries}" var="userEntry">
				<input type="hidden" name="userId" value="${userEntry.userId}">
				<tr>
					<td>${userEntry.firstName}</td>
					<td>${userEntry.lastName}</td>
					<td>${userEntry.phoneNum}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>