<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.user.account.locale.postfix}" scope="session"/>
<fmt:setBundle basename="property.pagecontent"/>

<header class="header-section">
    <div class="container">
        <div class="logo">
            <a href="/index.jsp">
                <img src="/assets/img/logo.png" alt="">
            </a>
        </div>
        <div class="nav-menu">
            <nav class="mainmenu mobile-menu">
                <ul>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/mainController?command=open_home">
                            <jsp:forward page="/pages/home.jsp"></jsp:forward>
                        </a>
                    </li>
                    <li>
                        <a href="/mainController?command=open_schedule" id="scheduleRef">
                            <fmt:message key="header.schedule"/>
                        </a>
                    </li>
                    <li>
                        <a href="/mainController?command=open_contacts">
                            <fmt:message key="header.contacts"/>
                        </a>
                    </li>
                    <c:choose>
                        <c:when test="${sessionScope.user!=null}">
                            <li>
                                <a href="/mainController?command=open_personal_data">
                                    <fmt:message key="header.profile"/>
                                </a>
                                <ul class="dropdown">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/mainController?command=logout">
                                            <fmt:message key="header.logout"/>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <button type="button" class="btn btn-outline-light" data-toggle="modal"
                                        data-target="#modalLogin">
                                    <fmt:message key="header.login"/>
                                </button>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
        <div id="mobile-menu-wrap"></div>
    </div>
</header>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.pagecontent"/>
<header>
    <p id="Site" align="left">
        Сайтик
    </p>
    <a id="notification">
        <img src="${pageContext.request.contextPath}/pages/parts/pic/bell.png" width="50" height="50"/>
    </a>
    <a id="russianLanguage" href="${pageContext.request.contextPath}/controller?command=change_language&language=ru">
        <img src="${pageContext.request.contextPath}/pages/parts/pic/rus.png" width="50" height="25"/>
    </a>
    <a id="englishLanguage" href="${pageContext.request.contextPath}/controller?command=change_language&language=en">
        <img src="${pageContext.request.contextPath}/pages/parts/pic/eng.png" width="50" height="25"/>
    </a>
    <ul id="navbar">
        <li>${user} <img id="down" src="${pageContext.request.contextPath}/pages/parts/pic/down.png" width="20"
                         height="20">
            <ul>
                <a id="headerButton" href="${pageContext.request.contextPath}/mainController?command=logout">
                    Выйти
                </a>
            </ul>
        </li>
    </ul>
</header>

