<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>

<!DOCTYPE html>
<html>
<head>
    <title>Insert title here</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link href="../static/css/style.css" rel="stylesheet" type="text/css">
</head>

    <body>

    <p><a href="${pageContext.request.contextPath}/offers">Show current offers.</a></p>
    <p><a href="${pageContext.request.contextPath}/createoffer">Add a new offer.</a></p>

    <p><c:url var="loginUrl" value="#"/></p>
    <p><c:url var="logoutUrl" value="#"/></p>

    <sec:authorize access="!isAuthenticated()">
        <form action="${loginUrl}" method="post">
            <input type="submit" value="Login" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Logout" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </sec:authorize>

    <!--sec:authorize access="(hasRole('ROLE_ADMIN'))"-->
    <p><c:url var="adminUrl" value="#"/></p>
    <form action="${adminUrl}" method="post">
        <input type="submit" value="Admin" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <!--/sec:authorize-->

    </body>

</html>
