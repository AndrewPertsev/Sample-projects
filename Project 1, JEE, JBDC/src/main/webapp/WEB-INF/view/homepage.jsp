<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<head>
    <title>HOME</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link href="css/style.css" rel="stylesheet">


</head>
<style>body {
    background-image: url("../../img/sight5.png");
    background-repeat: no-repeat;
}</style>

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
</c:if>

<body style=" background-color: #c0c4c4;">

<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>


<!-- Container (Portfolio Section) -->
<div class="container-fluid text-center bg-grey">
    <h4><fmt:message key="header.common.title"/></h4>
    <div class="row text-center">
        <div class="col-sm-4">
            <div class="thumbnail">
                <img src="../../img/apart5.png" alt="105" width="400" height="300">
                <p><fmt:message key="apartment.description.105"/>
                </p>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="thumbnail">
                <img src="../../img/apart6.png" alt="106" width="400" height="300">
                <p><fmt:message key="apartment.description.106"/>
                </p>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="thumbnail">
                <img src="../../img/apart7.png" alt="107" width="400" height="300">
                <%--                <p><strong>107</strong></p>--%>
                <p><fmt:message key="apartment.description.107"/></p>
            </div>
        </div>
    </div>
</div>

<%--carousel--%>

<div class="container">
    <div class="row">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>

            </ol>
            <div class="carousel-inner">

                <div class="carousel-item ">
                    <img src="../../img/apart3.png" alt="First slide" class="d-block w-100">
                </div>
                <div class="carousel-item ">
                    <img src="../../img/apart2.png" alt="Second slide" class="d-block w-100">
                </div>
                <div class="carousel-item active">
                    <img src="../../img/apart10.png" alt="Third slide" class="d-block w-100">
                </div>
                <div class="carousel-item">
                    <img src="../../img/apart2.png" alt="Fourth slide" class="d-block w-100">
                </div>

            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<c:if test="${message_regist_succ !=null}">--%>
    <p><c:out value="${message_regist_succ}"/></p>
</c:if>


<%--<jsp:include page="/WEB-INF/view/common/footer.jsp"/>--%>
</body>
</html>

