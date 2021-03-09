<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.user.account.locale.postfix}" scope="session"/>
<fmt:setBundle basename="property/pagecontent"/>

<!DOCTYPE html>

<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>
        <fmt:message key="admin.title"/>
    </title>
    <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport'/>
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <!-- CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/css/material-dashboard.css?v=2.1.2" rel="stylesheet"/>

    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/jquery.dataTables.min.js"></script>
</head>

<body>
<jsp:include page="${pageContext.request.contextPath}/pages/modal/feedback_reply.jsp"/>
<div class="wrapper ">
    <jsp:include page="${pageContext.request.contextPath}/pages/admin_sidebar.jsp"/>
    <div class="main-panel">
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header card-header-primary">
                                <h4 class="card-title "><fmt:message key="admin_feedbacks.tableTitle"/></h4>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table id="feedTable" class="table" style="font-size: small">
                                        <thead class=" text-primary">
                                        <th width="100">
                                            <fmt:message key="admin_feedbacks.feedbackDate"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_feedbacks.senderName"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_feedbacks.senderEmail"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_feedbacks.feedbackSubject"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_feedbacks.feedbackMessage"/>
                                        </th>
                                        <th>
                                            <fmt:message key="admin_feedbacks.feedbackReply"/>
                                        </th>
                                        <th>

                                        </th>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${feedbacks}" var="feedback">
                                            <tr>
                                                <td>
                                                        ${feedback.date}
                                                </td>
                                                <td>
                                                        ${feedback.senderName}
                                                </td>
                                                <td>
                                                        ${feedback.senderEmail}
                                                </td>
                                                <td>
                                                        ${feedback.subject}
                                                </td>
                                                <td>
                                                    <textarea class="form-control" rows="3" cols="50"
                                                              style="background-color: #fff;"
                                                              readonly>${feedback.message}</textarea>
                                                </td>
                                                <td>
                                                    <textarea class="form-control" rows="3" cols="50"
                                                              style="background-color: #fff;"
                                                              readonly>${feedback.reply}</textarea>
                                                </td>
                                                <td class="td-actions text-right">
                                                    <c:if test="${empty feedback.reply}">
                                                        <fmt:message key='admin_feedbacks.reply' var="replyTooltip"/>
                                                        <button type="button" class="btn btn-rose btn-round"
                                                                title="${replyTooltip}"
                                                                data-toggle="modal" data-target="#modalFeedbackReply"
                                                                data-feedbackid="${feedback.id}"
                                                                data-replyemail="${feedback.senderEmail}"
                                                                data-replysubject="${feedback.subject}">
                                                            <i class="material-icons">mail_outline</i>
                                                        </button>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <ctg:table-utility tableId="feedTable"/>
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
<script src="${pageContext.request.contextPath}/assets/js/modal-data.js"></script>
</body>
</html>