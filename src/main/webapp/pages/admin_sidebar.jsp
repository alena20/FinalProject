<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.user.account.locale.postfix}" scope="session"/>
<fmt:setBundle basename="property/pagecontent"/>

<div class="sidebar" data-color="purple" data-background-color="white">
    <div class="logo">
        <a class="simple-text logo-normal">
            <fmt:message key="admin_sidebar.title"/>
        </a>
    </div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li class="${activeTab == 'feedbacksTab' ? 'nav-item active' : 'nav-item'}">
                <a class="nav-link" href="/mainController?command=open_admin_feedbacks">
                    <p><fmt:message key="admin_sidebar.feedbacks"/></p>
                </a>
            </li>
            <li class="${activeTab == 'registrationsTab' ? 'nav-item active' : 'nav-item'}">
                <a class="nav-link" href="/mainController?command=open_admin_registrations">
                    <p><fmt:message key="admin_sidebar.recentRegistrations"/></p>
                </a>
            </li>
            <li class="${activeTab == 'applicationsTab' ? 'nav-item active' : 'nav-item'}">
                <a class="nav-link" href="/mainController?command=open_admin_main">
                    <p><fmt:message key="admin_sidebar.newTrainerApplications"/></p>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mainController?command=change_admin_locale">
                    <p><fmt:message key="admin_sidebar.switchLanguage"/></p>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mainController?command=logout">
                    <p><fmt:message key="admin_sidebar.logOut"/></p>
                </a>
            </li>
        </ul>
    </div>
</div>
