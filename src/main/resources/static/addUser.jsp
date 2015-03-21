<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
<form:form method="POST" action="/api/user/addUser">
        userID<form:input path="userID" value = "4"/>
        username<form:input path="username" />
		email<form:input path="email" />
		password<form:input path="password" />
		salt<form:input path="salt" />
        commits<form:input path="commits" />
        <input type="submit" value="Submit"/>  
</form:form>
</body>
</html>