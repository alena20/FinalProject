<%@ page import="com.epam.finalproject.validator.ValidationError" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="property/pagecontent"/>

<script src="${pageContext.request.contextPath}/assets/js/login.js"></script>

<div class="modal fade" id="modalLogin" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body">
                <div class="login-wrap">
                    <div class="login-html">
                        <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
                        <label for="tab-1" class="tab">
                            <fmt:message key="login.signIn"/>
                        </label>
                        <input id="tab-2" type="radio" name="tab" class="sign-up">
                        <label for="tab-2" class="tab">
                            <fmt:message key="login.signUp"/>
                        </label>
                        <div class="login-form">
                            <form action="/mainController" method="post">
                                <div class="sign-in-htm">
                                    <input type="hidden" name="command" value="login"/>
                                    <div class="group">
                                        <label for="login" class="label"><fmt:message key="login.userName"/></label>
                                        <input class="input" type="text" id="login" name="login"
                                               value="${loginMap['login']}" required
                                               pattern="[a-zA-Z][a-zA-Z0-9_]{1,19}"
                                               onchange="this.setCustomValidity('')"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="login.invalidLoginFormat"/>')">
                                    </div>
                                    <div class="group">
                                        <label for="loginPassword" class="label"><fmt:message
                                                key="login.password"/></label>
                                        <input class="input" id="loginPassword" type="password" name="loginPassword"
                                               value="${loginMap['loginPassword']}" required
                                               pattern="[a-zA-Z0-9_]{5,30}"
                                               onchange="this.setCustomValidity('')"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="login.invalidPasswordFormat"/>')">
                                    </div>
                                    <div class="group">
                                        <fmt:message key="login.signIn" var="loginSubmit" scope="page"/>
                                        <input type="submit" class="button" value="${loginSubmit}" id="submitLogin">
                                    </div>
                                    <c:if test="${errorSet.contains(ValidationError.WRONG_LOGIN_PASSWORD)}">
                                        <label style="color: red; font-size: medium"><fmt:message
                                                key="login.incorrectLoginPassword"/></label>
                                    </c:if>
                                    <label style="color: red; font-size: medium; display: none" id="needLogin">
                                        <fmt:message key="login.loginFirst"/>
                                    </label>
                                    <div class="hr"></div>
                                </div>
                            </form>
                            <form action="/mainController" method="post" id="regForm">
                                <div class="sign-up-htm">
                                    <input type="hidden" name="command" value="register"/>
                                    <div class="group">
                                        <label for="regLogin" class="label">
                                            <fmt:message key="login.userName"/>
                                        </label>
                                        <input id="regLogin" type="text" name="regLogin" class="input"
                                               value="${regMap['regLogin']}" required
                                               pattern="[a-zA-Z][a-zA-Z0-9_]{1,19}"
                                               onchange="this.setCustomValidity('')"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="login.invalidLoginFormat"/>')">
                                        <c:if test="${errorSet.contains(ValidationError.LOGIN_EXISTS)}">
                                            <label style="color: red; font-size: medium"><fmt:message
                                                    key="login.loginExists"/></label>
                                        </c:if>
                                    </div>
                                    <div class="group">
                                        <label for="regPassword" class="label"><fmt:message
                                                key="login.password"/></label>
                                        <input id="regPassword" type="password" name="regPassword" class="input"
                                               value="${regMap['regPassword']}" required pattern="[a-zA-Z0-9_]{5,30}"
                                               onchange="form.repeatPassword.pattern = this.value; this.setCustomValidity('')"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="login.invalidPasswordFormat"/>')">
                                    </div>
                                    <div class="group">
                                        <label for="repeatPassword" class="label"><fmt:message
                                                key="login.repeatPassword"/></label>
                                        <input id="repeatPassword" name="repeatPassword" type="password" class="input"
                                               required onchange="this.setCustomValidity('')"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="login.passwordsNotEqual"/>')">
                                    </div>
                                    <div class="group">
                                        <label for="regEmail" class="label"><fmt:message key="login.email"/></label>
                                        <input id="regEmail" type="email" name="regEmail" class="input" required
                                               value="${regMap['regEmail']}"
                                               onchange="this.setCustomValidity('')"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="login.invalidEmailFormat"/>')">
                                        <c:if test="${errorSet.contains(ValidationError.EMAIL_EXISTS)}">
                                            <label style="color: red; font-size: medium"><fmt:message
                                                    key="login.emailExists"/></label>
                                        </c:if>
                                    </div>
                                    <div class="group">
                                        <fmt:message key="login.signUp" var="regSubmit" scope="page"/>
                                        <input type="submit" class="button" value="${regSubmit}" id="regButton">
                                    </div>
                                    <div class="hr"></div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
