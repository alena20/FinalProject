<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.user.account.locale.postfix}"
               scope="session"/>
<fmt:setBundle basename="property/pagecontent"/>

<!-- Footer Section Begin -->
<footer class="footer-section">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-6">
                <div class="map-location">
                    <iframe src="https://maps.google.com/maps?q=minsk%20adrenalin%20loshitsa&t=&z=13&ie=UTF8&iwloc=&output=embed"
                            style="border:0;" allowfullscreen=""></iframe>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="footer-form set-bg"
                     data-setbg="assets/img/contact-form-bg.jpg">
                    <div class="row">
                        <div class="col-lg-10">
                            <div class="section-title">
                                <h2><fmt:message key="footer.head"/></h2>
                                <p><fmt:message key="footer.motivation"/></p>
                            </div>
                            <form action="/mainController" method="post">
                                <input type="hidden" name="command" value="add_feedback"/>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <input type="text" placeholder="<fmt:message key="footer.name"/>"
                                               name="senderName" id="senderName" pattern="^[\p{L}\d]{0,30}$">
                                    </div>
                                    <div class="col-lg-6">
                                        <input type="email" placeholder="<fmt:message key="footer.email"/> *"
                                               name="senderEmail" id="senderEmail" required>
                                    </div>
                                    <div class="col-lg-12">
                                        <input type="text" name="feedbackSubject" pattern="^.{0,30}$"
                                               placeholder=
                                               <fmt:message key="footer.subject"/> id="feedbackSubject">
                                        <textarea name="feedbackMessage" id="feedbackMessage" required
                                                  placeholder="<fmt:message key="footer.message"/> *"></textarea>
                                        <button type="submit"><fmt:message key="footer.submit"/> <i
                                                class="ti-angle-double-right"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
