<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="customtag" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html lang="en">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">

<head>
    <title>Main page</title>
</head>

<body>
<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>

<c:if test="${sessionScope.message_fail != null}">
    <div id="error-alert" class="alert alert-danger mt-2 mb-2" role="alert">
        <h4 style="color:#de1212">${sessionScope.message_fail}</h4>
    </div>
    ${sessionScope.remove("message_fail")}
</c:if>
<c:if test="${sessionScope.message_success != null}">
    <div id="error-alert" class="alert alert-danger mt-2 mb-2" role="alert">
        <h4 style="color:#1297de">${sessionScope.message_success}</h4>
    </div>
    ${sessionScope.remove("message_success")}
    <h4>Mr. <c:out value="${userName}"/> <c:out value="${userSurName}"/></h4>
    <p>you have id <c:out value="${userId}"/> and you are<c:choose>
        <c:when test="${userRoleId == 1}"> our client </c:when>
        <c:when test="${userRoleId == 2}"> manager</c:when>
        <c:when test="${userRoleId == 0}"> ! unauthorized guest</c:when>
    </c:choose></p>
</c:if>
<div><style>body{background-image: url("../../img/apart8.png") ; background-repeat: no-repeat ;}</style></div>

<%--<h5>INF about success ::: <c:out value="${message_success}"/></h5>--%>

<%--<c:set var="message" scope="page" value="${2*255}"/>--%>
<%--<c:out value="${message}"/>--%>
<%--<c:if test="${message<500}" var="testIF">--%>
<%--    <p>my message <c:out value="${message}"/></p>--%>
<%--</c:if>--%>
<%--<c:out value="${testIF}"/>--%>

<%--<h1 style="color:#fa8847"> HELLO</h1>--%>

<%--<div class="container">--%>
<%--    <div class="form-row col-md-3 text-center">--%>
<%--        <form action="Controller" method="post">--%>
<%--            <input type="hidden" name="command" value="PUSH_OFFER_TO_USER">--%>
<%--            <input type="text" name="apartmentId" value="" class="form-control setMargin"--%>
<%--                   placeholder="Enter ">--%>
<%--            <input type="submit" class=" btn-primary btn-block  badge-pill " value="Push "/>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>

<%--&lt;%&ndash;check if admin&ndash;%&gt;--%>
<%--<c:set var="role" scope="session" value="roleId"/>--%>
<%--&lt;%&ndash;<c:out value="${message}"/>&ndash;%&gt;--%>
<%--<c:if test="${not empty role and role eq 'roleId'}" var="testRole">--%>
<%--    <p>here is role : <c:out value="it ccccc is admin"/></p>--%>
<%--</c:if>--%>
<%--<c:out value="${testRole}"/>--%>


<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>
