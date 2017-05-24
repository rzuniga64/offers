<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Page</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/resources/css/style.css"
          rel="stylesheet" type="text/css">
<title>Admin page</title>
</head>
<body>
<h3>Authorised Users Only!</h3>

<table class="table">
    <thead>
        <tr>
            <th>Username</th><th>Email</th><th>Role</th><th>Enabled</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">

            <tr><td><c:out value="${user.username}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.authority}"></c:out></td>
                <td><c:out value="${user.enabled}"></c:out></td>
            </tr>

        </c:forEach>
    </tbody>
</table>
</body>
</html>