<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User</title>
</head>
<body>
<h2>Update User</h2>
<form action="Update" method="post">
<input type="hidden" name="userId" value="${userEntry.userId}">
First Name: <input type="text" name="firstName" value="${userEntry.firstName}"><br>
Last Name: <input type="text" name="lastName" value="${userEntry.lastName}"><br>
<button>Save</button>
</form>
</body>
</html>