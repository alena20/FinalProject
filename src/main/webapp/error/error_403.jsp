<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.account.locale.postfix}"
               scope="session"/>
<fmt:setBundle basename="property/pagecontent"/>

<link href="${pageContext.request.contextPath}/css/error-403.css" rel="stylesheet"/>

<div class="scene">
    <div class="overlay"></div>
    <div class="overlay"></div>
    <div class="overlay"></div>
    <div class="overlay"></div>
    <span class="bg-403">403</span>
    <div class="text">
        <span class="hero-text"></span>
        <span class="msg"><fmt:message key="error_403.let"/></span>
        <span class="support">
      <span><fmt:message key="error_403.unexpected"/></span>
      <a href="/"><fmt:message key="error_403.home"/></a>
    </span>
    </div>
    <div class="lock"></div>
</div>