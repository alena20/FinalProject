<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.user.account.locale.postfix}" scope="session"/>
<fmt:setBundle basename="property/pagecontent"/>

<!DOCTYPE html>

<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>
        <fmt:message key="admin.title"/>
    </title>
    <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport'/>
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <!-- CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/css/material-dashboard.css?v=2.1.2" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/assets/css/img-upload.css" rel="stylesheet"/>

    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/plugins/jquery.dataTables.min.js"></script>

</head>

<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/modal/client_profile.jsp"/>
<div class="wrapper ">
    <jsp:include page="${pageContext.request.contextPath}/jsp/admin_sidebar.jsp"/>
    <div class="main-panel">
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title "><fmt:message key="admin_main.newTrainerApplications"/></h4>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table id="appTable" class="table">
                                        <thead class=" text-primary">
                                        <th>
                                            <fmt:message key="admin_main.id"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_main.firstName"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_main.lastName"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_main.institution"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_main.graduationYear"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_main.instagram"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_main.applicationDate"/>
                                        </th>
                                        <th>

                                        </th>
                                        <th>

                                        </th>
                                        <th>

                                        </th>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${applications}" var="application">
                                            <tr>
                                                <td>
                                                        ${application.user.account.id}
                                                </td>
                                                <td>
                                                        ${application.user.firstName}
                                                </td>
                                                <td>
                                                        ${application.user.lastName}
                                                </td>
                                                <td>
                                                        ${application.institution}
                                                </td>
                                                <td>
                                                        ${application.graduationYear}
                                                </td>
                                                <td>
                                                    <a href="${application.instagramLink}">${application.instagramLink}</a>
                                                </td>
                                                <td>
                                                        ${application.applicationDate}
                                                </td>
                                                <td class="td-actions text-right">
                                                    <fmt:message key='admin_main.openProfile' var="openTooltip"/>
                                                    <ctg:profile-button user="${application.user}"
                                                                        tooltip="${openTooltip}"/>
                                                </td>
                                                <td class="td-actions text-right">
                                                    <form action="${pageContext.request.contextPath}/mainController"
                                                          method="post">
                                                        <input type="hidden" name="command"
                                                               value="approve_trainer_application"/>
                                                        <input type="hidden" name="appId"
                                                               value="${application.user.account.id}"/>
                                                        <input type="hidden" name="appInstitution"
                                                               value="${application.institution}"/>
                                                        <input type="hidden" name="appGraduation"
                                                               value="${application.graduationYear}"/>
                                                        <input type="hidden" name="appInstagram"
                                                               value="${application.instagramLink}"/>
                                                        <fmt:message key='admin_main.approve' var="approveTooltip"/>
                                                        <button type="submit" rel="tooltip"
                                                                title="${approveTooltip}"
                                                                class="btn btn-success btn-round">
                                                            <i class="material-icons">done</i>
                                                        </button>
                                                    </form>
                                                </td>
                                                <td class="td-actions text-right">
                                                    <form action="${pageContext.request.contextPath}/mainController"
                                                          method="post">
                                                        <input type="hidden" name="command"
                                                               value="refuse_trainer_application"/>
                                                        <input type="hidden" name="appId"
                                                               value="${application.user.account.id}"/>
                                                        <fmt:message key='admin_main.refuse' var="refuseTooltip"/>
                                                        <button type="submit" rel="tooltip"
                                                                title="${refuseTooltip}"
                                                                class="btn btn-danger btn-round">
                                                            <i class="material-icons">close</i>
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <ctg:table-utility tableId="appTable"/>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/js/img-upload.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/rating.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/modal-data.js"></script>
</body>
</html>