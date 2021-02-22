<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--TODO мб тут .ueser лишнее ибо аккаунт локаль лежит у меня отдельно--%>
<fmt:setLocale value="${sessionScope.user.account.locale.postfix}"
               scope="session"/>
<fmt:setBundle basename="property.pagecontent"/>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><fmt:message key="title"/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300,400,500,600,700&display=swap" rel="stylesheet">


    <!-- Css Styles -->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css"/>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/themify-icons.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.min.css" type="text/css"/>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>

<body>
<!-- Page Preloder -->
<%--
<div id="preloder">
    <div class="loader"></div>
</div>
--%>

<jsp:include page="section/header.jsp"/>
<jsp:include page="modal/confirm_sent.jsp"/>
<jsp:include page="modal/confirmed.jsp"/>
<jsp:include page="modal/access_error.jsp"/>
<jsp:include page="modal/login.jsp"/>

<section class="hero-section">
    <div class="hero-items owl-carousel">
        <div class="single-hero-item set-bg" data-setbg="${pageContext.request.contextPath}/assets/img/slider-bg-1.jpg">
            <div class="container">
                <div class="hero-text">
                    <h4><fmt:message key="home.elite"/></h4>
                    <h1><fmt:message key="home.make"/> <span><fmt:message key="home.shape"/></span></h1>
                    <c:choose>
                        <c:when test="${user==null}">
                            <button type="button" class="btn primary-btn" data-toggle="modal" data-target="#modalLogin">
                                <fmt:message key="home.join"/></button>
                        </c:when>
                        <c:otherwise>
                            <a href="/mainController?command=open_personal_data">
                                <button type="button" class="btn primary-btn"><fmt:message key="home.profile"/></button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="single-hero-item set-bg" data-setbg="${pageContext.request.contextPath}/assets/img/slider-bg-2.jpg">
            <div class="container">
                <div class="hero-text">
                    <h4><fmt:message key="home.elite"/></h4>
                    <h1><fmt:message key="home.make"/> <span><fmt:message key="home.shape"/></span></h1>
                    <c:choose>
                        <c:when test="${user==null}">
                            <button type="button" class="btn primary-btn" data-toggle="modal" data-target="#modalLogin">
                                <fmt:message key="home.join"/></button>
                        </c:when>
                        <c:otherwise>
                            <a href="/mainController?command=open_personal_data">
                                <button type="button" class="btn primary-btn"><fmt:message key="home.profile"/></button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="single-hero-item set-bg" data-setbg="${pageContext.request.contextPath}/assets/img/slider-bg-3.jpg">
            <div class="container">
                <div class="hero-text">
                    <h4><fmt:message key="home.elite"/></h4>
                    <h1><fmt:message key="home.make"/> <span><fmt:message key="home.shape"/></span></h1>
                    <c:choose>
                        <c:when test="${user==null}">
                            <button type="button" class="btn primary-btn" data-toggle="modal" data-target="#modalLogin">
                                <fmt:message key="home.join"/></button>
                        </c:when>
                        <c:otherwise>
                            <a href="/mainController?command=open_personal_data">
                                <button type="button" class="btn primary-btn"><fmt:message key="home.profile"/></button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->

<!-- Team Section Begin -->
<section class="team-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2><fmt:message key="home.trainers"/></h2>
                    <p><fmt:message key="home.fitnessExperts"/></p>
                </div>
                <button type="button" class="btn primary-btn" data-toggle="modal" data-target="#modalAllTrainers">
                    <fmt:message key="home.viewAll"/></button>
            </div>
        </div>
        <div class="team-members">
            <div class="row m-0">
                <div class="col-lg-4 order-lg-1 p-0">
                    <div class="member-pic first">
                        <img src="${trainers.get(0).imageName}" alt="">
                    </div>
                </div>
                <div class="col-lg-4 order-lg-2 p-0">
                    <div class="member-text">
                        <h5>${trainers.get(0).firstName} ${trainers.get(0).lastName}</h5>
                        <h5>
                            <div class="ratingView" data-score="${trainers.get(0).rating}" data-half="true"></div>
                        </h5>
                        <p>${trainers.get(0).shortSummary}</p>
                        <div class="member-social">
                            <a href="${trainers.get(0).instagramLink}" target="_blank"><i class="ti-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 order-lg-3 p-0">
                    <div class="member-pic second">
                        <img src="${trainers.get(1).imageName}" alt="">
                    </div>
                </div>
                <div class="col-lg-4 order-lg-6 p-0">
                    <div class="member-text second">
                        <h5>${trainers.get(1).firstName} ${trainers.get(1).lastName}</h5>
                        <h5>
                            <div class="ratingView" data-score="${trainers.get(1).rating}" data-half="true"></div>
                        </h5>
                        <p>${trainers.get(1).shortSummary}</p>
                        <div class="member-social">
                            <a href="${trainers.get(1).instagramLink}" target="_blank"><i class="ti-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 order-lg-5 p-0">
                    <div class="member-pic third">
                        <img src="${trainers.get(2).imageName}" alt="">
                    </div>
                </div>
                <div class="col-lg-4 order-lg-4 p-0">
                    <div class="member-text third">
                        <h5>${trainers.get(2).firstName} ${trainers.get(2).lastName}</h5>
                        <h5>
                            <div class="ratingView" data-score="${trainers.get(2).rating}" data-half="true"></div>
                        </h5>
                        <p>${trainers.get(2).shortSummary}</p>
                        <div class="member-social">
                            <a href="${trainers.get(2).instagramLink}" target="_blank"><i class="ti-instagram"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="section/footer.jsp"/>

<!-- Search model Begin -->
<%--<div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
            <input type="text" id="search-input" placeholder="Search here.....">
        </form>
    </div>
</div>--%>

</body>

</html>
