<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<c:choose>

    <c:when test="${sessionScope.userRoleId== 2}">
            <nav class="navbar navbar-expand-xl navbar-dark bg-dark ">
                <a href="##" class="navbar-brand" style="color:#545481">
                    HERITAGE APARTMENTS</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                        aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav ml-auto">
                        <a class="nav-item nav-link active" href="Controller?command=GO_TO_HOME_PAGE"><fmt:message key="header.common.home"/><span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_REQUEST_PAGE"><fmt:message key="header.common.book_now"/></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_GALLERY_PAGE"><fmt:message key="header.common.gallery"/></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_GUEST_ROOM_PAGE"> <fmt:message key="header.common.my_page"/></a>
                        <a class="nav-item nav-link"href="Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE"><fmt:message key="header.common.requestUsers"/></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_APARTMENT_MANAGEMENT_PAGE"><fmt:message key="header.common.apartments"/></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_OFFER_MANAGEMENT_PAGE"><fmt:message key="header.common.offers"/></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_GUEST_MANAGEMENT_PAGE"><fmt:message key="header.common.guests"/></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_TIMESHEET_MANAGEMENT_PAGE"><fmt:message key="header.common.timesheet"/></a>
                            <%--            <a class="nav-item nav-link" href="#">PAYMENT</a>--%>
                        <a class="nav-item nav-link" href="#"></a>
                        <a class="nav-item nav-link " href="${pageContext.request.contextPath}?command=${pageContext.request.getParameter("command")}&locale=en_EN"><fmt:message key="header.common.en.button"/></a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}?command=${pageContext.request.getParameter("command")}&locale=ru_RU"><fmt:message key="header.common.ru.button"/></a>
                        <a class="nav-item nav-link" href="#"></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_REGISTRATION_PAGE"><fmt:message key="header.common.sign_up"/></a>
                        <a class="nav-item nav-link" class="text-right" href="Controller?command=GO_TO_LOGIN_PAGE"><fmt:message key="header.common.login"/></a>
                        <a class="nav-item nav-link" class="text-right" href="Controller?command=CLOSE_GUEST_SESSION"><fmt:message key="header.common.exit"/></a>
                    </div>
                </div>
            </nav>
    </c:when>

    <c:when test="${sessionScope.userRoleId==1}">
            <nav class="navbar navbar-expand-xl navbar-dark bg-dark  ml-auto">
                <a href="##" class="navbar-brand" style="color:blueviolet">
                    HERITAGE APARTMENTS</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                        aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup2" flex-row-reverse>
                    <div class="navbar-nav ml-auto" >
                        <a class="nav-item nav-link active" href="Controller?command=GO_TO_HOME_PAGE"><fmt:message key="header.common.home"/><span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_REQUEST_PAGE"><fmt:message key="header.common.book_now"/></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_GALLERY_PAGE"><fmt:message key="header.common.gallery"/></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_GUEST_ROOM_PAGE"> <fmt:message key="header.common.my_page"/></a>
                        <a class="nav-item nav-link" href="#"></a>
                        <a class="nav-item nav-link " href="${pageContext.request.contextPath}?command=${pageContext.request.getParameter("command")}&locale=en_EN"><fmt:message key="header.common.en.button"/></a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}?command=${pageContext.request.getParameter("command")}&locale=ru_RU"><fmt:message key="header.common.ru.button"/></a>
                        <a class="nav-item nav-link" href="#"></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_REGISTRATION_PAGE"><fmt:message key="header.common.sign_up"/></a>
                        <a class="nav-item nav-link" class="text-right" href="Controller?command=GO_TO_LOGIN_PAGE"><fmt:message key="header.common.login"/></a>
                        <a class="nav-item nav-link" class="text-right" href="Controller?command=CLOSE_GUEST_SESSION"><fmt:message key="header.common.exit"/></a>
                    </div>
                </div>
            </nav>
    </c:when>

    <c:otherwise>
            <nav class="navbar navbar-expand-xl navbar-dark bg-dark  ml-auto">
                <a href="##" class="navbar-brand" style="color:blueviolet">
                    HERITAGE APARTMENTS</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                        aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                   <div class="collapse navbar-collapse" id="navbarNavAltMarkup3" flex-row-reverse>
                    <div class="navbar-nav ml-auto" >
                        <a class="nav-item nav-link active" href="Controller?command=GO_TO_HOME_PAGE"><fmt:message key="header.common.home"/><span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_GALLERY_PAGE"><fmt:message key="header.common.gallery"/></a>
                        <a class="nav-item nav-link" href="#"></a>
                        <a class="nav-item nav-link " href="${pageContext.request.contextPath}?command=${pageContext.request.getParameter("command")}&locale=en_EN"><fmt:message key="header.common.en.button"/></a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}?command=${pageContext.request.getParameter("command")}&locale=ru_RU"><fmt:message key="header.common.ru.button"/></a>
                        <a class="nav-item nav-link" href="#"></a>
                        <a class="nav-item nav-link" href="Controller?command=GO_TO_REGISTRATION_PAGE"><fmt:message key="header.common.sign_up"/></a>
                        <a class="nav-item nav-link" class="text-right" href="Controller?command=GO_TO_LOGIN_PAGE"><fmt:message key="header.common.login"/></a>
                    </div>
                </div>
            </nav>
    </c:otherwise>

</c:choose>




