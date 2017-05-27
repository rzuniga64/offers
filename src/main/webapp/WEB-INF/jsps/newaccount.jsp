<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Account</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/resources/css/style.css"
          rel="stylesheet" type="text/css">
    <script
            type="text/javascript"
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">
    </script>

    <script type="text/javascript">

        function onLoad() {
            $("#password").keyup(checkPasswordsMatch);
            $("#confirmpass").keyup(checkPasswordsMatch);

            $("#details").submit(canSubmit);
        }

        function canSubmit() {
            var password = $("#password").val();
            var confirmpass = $("#confirmpass").val();

            if(password != confirmpass) {
                alert("<fmt:message key='UnmatchedPasswords.user.password' />")
                return false;
            } else {
                return true;
            }
        }

        function checkPasswordsMatch() {
            var password = $("#password").val();
            var confirmpass = $("#confirmpass").val();

            if (password.length > 3 || confirmpass.length > 3) {

                if (password == confirmpass) {
                    $("#matchpass").text("<fmt:message key='MatchedPasswords.user.password' />");
                    $("#matchpass").addClass("valid");
                    $("#matchpass").removeClass("error");
                } else {
                    $("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password' />");
                    $("#matchpass").addClass("error");
                    $("#matchpass").removeClass("valid");
                }
            }
        }

        $(document).ready(onLoad);
    </script>
</head>
<body>
<h2 style="text-align:center">Create New Account</h2>
<div class="container">
    <div class="row">
        <sf:form method="post"
                 action="${pageContext.request.contextPath}/createaccount"
                 modelAttribute="user"
                 id="detaisl">

        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
                <sf:input type="text"
                          class="form-control"
                          id="username"
                          name="username"
                          path="username"
                          placeholder="Username"/>
                <br />
                <sf:errors path="username" cssClass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">Email</label>
            <div class="col-sm-10">
                <sf:input type="email"
                          class="form-control"
                          id="email"
                          name="email"
                          path="email"
                          placeholder="Email"/>
                <br />
                <sf:errors path="email" cssClass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <sf:input type="password"
                          class="form-control"
                          id="password"
                          name="password"
                          path="password"
                          placeholder="Password"/>
                <br />
                <sf:errors path="password" cssClass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group">
            <label for="confirmpass" class="col-sm-2 control-label">Confirm Password</label>
            <div class="col-sm-10">
                <input type="password"
                          class="form-control"
                          id="confirmpass"
                          name="confirmpass"
                          placeholder="Password"/>
            </div>
            <div class="col-sm-2"></div>
            <div id="matchpass" class="col-sm-10"></div>
        </div>
        <button type="submit" class="btn btn-default">Create Account</button>

        </sf:form>
    </div><!-- row -->
</div><!-- container -->
</body>
</html>