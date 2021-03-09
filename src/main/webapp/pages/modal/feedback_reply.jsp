<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.user.account.locale.postfix}"
               scope="session"/>
<fmt:setBundle basename="property/pagecontent"/>

<div class="modal fade" id="modalFeedbackReply" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title w-100 text-center">
                    <fmt:message key="feedback_reply.header"/>
                </h5>
            </div>
            <div class="modal-body">
                <div class="card" style="margin-top: 0px; margin-bottom: 0px;">
                    <div class="card-body">
                        <form action="/mainController" method="post">
                            <input type="hidden" name="command" value="send_feedback_reply">
                            <input type="hidden" name="feedbackId" id="feedbackId">
                            <div class="form-group">
                                <label for="replyEmail"><fmt:message key="feedback_reply.replyTo"/></label>
                                <input type="email" class="form-control" id="replyEmail" name="replyEmail"
                                       style="background-color: #fff;" readonly>
                            </div>
                            <div class="form-group">
                                <label for="replyEmail"><fmt:message key="feedback_reply.subject"/></label>
                                <input type="text" class="form-control" id="replySubject" name="replySubject"
                                       style="background-color: #fff;" readonly>
                            </div>
                            <div class="form-group">
                                <label for="replyMessage"><fmt:message key="feedback_reply.message"/> *</label>
                                <textarea class="form-control" id="replyMessage" name="replyMessage"
                                          rows="3" minlength="1" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary mb-2"><fmt:message
                                    key="feedback_reply.send"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>