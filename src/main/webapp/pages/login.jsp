<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.pagecontent"/>
<html>
<head>
    <c:import url="fragment/bootstrap_style.jsp"/>
    <c:import url="fragment/bootstrap_script.jsp"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/singInAndSignUp.css">
    <title>sign in</title>
</head>
<body>
<c:import url="section/header.jsp"/>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form action="${pageContext.request.contextPath}/mainController" method="post">
            <input type="hidden" name="command" value="login">
            <input type="text" id="email" class="fadeIn second" name="login" placeholder="Login" required>
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="<fmt:message key="placeholder.password"/>" required>
            <input type="submit" class="fadeIn fourth"  name="command" value="<fmt:message key="login.signIn"></input>">
        </form>
        <c:if test="${errorUserMessageIsValid}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="login.emailAndPasswordIsValid"/>
            </div>
        </c:if>
        <c:if test="${errorUserMessageIsNotExist}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="login.incorrectLoginPassword"/>
            </div>
        </c:if>

        <div id="formFooter">
            <form action="${pageContext.request.contextPath}/mainController" method="get">
                <input type="hidden" name="command" value="register">
                <input type="submit" value="<fmt:message key="login.signUp"/>">
            </form>
        </div>
    </div>
</div>
</body>
</html>
