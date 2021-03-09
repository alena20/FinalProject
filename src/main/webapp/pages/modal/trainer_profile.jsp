<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.user.account.locale.postfix}"
               scope="session"/>
<fmt:setBundle basename="property/pagecontent"/>

<div class="modal fade" id="modalTrainerProfile" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title w-100 text-center">
                    <fmt:message key="client_profile.name"/> <label id="nameText" style="color: #0b0b0b"></label>
                </h5>
            </div>
            <div class="modal-body">
                <div class="card" style="margin-top: 0px; margin-bottom: 0px;">
                    <div class="card-header-image ">
                        <div class="avatar-upload">
                            <div class="avatar-preview">
                                <div id="imagePreview"></div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="container">
                            <c:if test="${user.account.role=='ADMIN'}">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <fmt:message key="trainer_profile.userType"/>
                                    </div>
                                    <div class="col-lg-6">
                                        <label id="roleText" style="color: #0b0b0b"></label>
                                    </div>
                                </div>
                            </c:if>
                            <div class="row">
                                <div class="col-lg-6">
                                    <fmt:message key="trainer_profile.email"/>
                                </div>
                                <div class="col-lg-6">
                                    <label id="emailText" style="color: #0b0b0b"></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <fmt:message key="trainer_profile.phone"/>
                                </div>
                                <div class="col-lg-6">
                                    <label id="phoneText" style="color: #0b0b0b"></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <fmt:message key="trainer_profile.institution"/>
                                </div>
                                <div class="col-lg-6">
                                    <label id="institutionText" style="color: #0b0b0b"></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <fmt:message key="trainer_profile.graduation"/>
                                </div>
                                <div class="col-lg-6">
                                    <label id="graduationText" style="color: #0b0b0b"></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <fmt:message key="trainer_profile.rating"/>
                                </div>
                                <div class="col-lg-6">
                                    <div id="ratingTrainerProfile"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>