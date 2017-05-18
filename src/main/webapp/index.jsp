<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<html lang="en">

    <body>
        Message: ${message}

        <%-- use name of attribute set in Offers Controller --%>
        <c:forEach var="row" items="${offers}">
            ID ${row.id}<br/>
            Name ${row.name}<br/>
            EMail ${row.id}<br/>
            Text ${row.name}<br/>
        </c:forEach>
    </body>

</html>
