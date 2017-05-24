<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
    <p>Hi there!</p>
    <p>
        <%-- Session: <%= session.getAttribute("name") %> --%>
        <%-- Request(using EL): ${name} --%>
        Using JSTL: <c:out value="${name}"/>
    </p>

    <!-- JSTL connects to the JNDI data source. -->
    <sql:query var="rs" dataSource="jdbc/MySQLDataSource">
        select id, name, email, text from springtutorial.offers
    </sql:query>

    <c:forEach var="row" items="${rs.rows}">
        ID: ${row.id}<br/>
        Name: ${row.name}<br/>
    </c:forEach>

</body>
</html>
