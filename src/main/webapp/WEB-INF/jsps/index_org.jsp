<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<sql:query var="rs" dataSource="jdbc:mysql://localhost:3306/springtutorial?user=rzuniga64&password=aggies92">
    select id, name, email, text from offers
</sql:query >

<html lang="en">

    <body>
        Message: ${message}

        <c:forEach var="row" items="${rs.rows}">
            ID ${row.id}<br/>
            Name ${row.name}<br/>
        </c:forEach>
    </body>

</html>
