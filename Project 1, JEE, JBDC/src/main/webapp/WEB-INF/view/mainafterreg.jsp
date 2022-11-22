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
<div>
    <style>body {
        background-image: url("../../img/apart8.png");
        background-repeat: no-repeat;
    }</style>
</div>

<%--<jsp:include page="/WEB-INF/view/common/footer.jsp"/>--%>
</body>
</html>
