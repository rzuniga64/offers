<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Offer</title>
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

    <div class="container">
        <div class="row"></div>
        <sf:form method="post"
                 action="${pageContext.request.contextPath}/docreate"
                 modelAttribute="offer">

            <div class="form-group">
                <label for="username">Username</label>
                <sf:input type="username"
                          class="form-control"
                          id="username"
                          name="username"
                          path="username"
                          placeholder="Username"/>
                <br />
                <sf:errors path="username" cssClass="error"></sf:errors>
            </div>
            <div class="form-group">
                <label for="text">Your offer</label>
                <sf:textarea class="form-control"
                             id="text"
                             name="text"
                             path="text"
                             rows="10"
                             cols="10"/>
                <br />
                <sf:errors path="text" cssClass="error"></sf:errors>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>

        </sf:form>
    </div>
</body>
</html>