<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.user.account.locale.postfix}"
               scope="session"/>
<fmt:setBundle basename="property/pagecontent"/>

<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/error-500.css" rel="stylesheet">

<div class="page-500">
    <div class="outer">
        <div class="middle">
            <div class="inner">
                <!--BEGIN CONTENT-->
                <div class="inner-circle"><i class="fa fa-cogs"></i><span>500</span></div>
                <span class="inner-status"><fmt:message key="error_500.oops"/></span>
                <span class="inner-detail"><fmt:message key="error_500.unfortunately"/></span>
                <!--END CONTENT-->
            </div>
        </div>
    </div>
</div>