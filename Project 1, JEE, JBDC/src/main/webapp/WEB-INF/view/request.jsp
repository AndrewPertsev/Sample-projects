<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="header.common.book_now"/>
    </title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css\bootstrap-datepicker3.min.css">

</head>

<style>body {
    background-image: url("../../img/sight4.png");
    background-repeat: no-repeat;
}</style>
<body>
<%--<jsp:include page="/WEB-INF/view/common/header_common.jsp"/>--%>
<br>
<div class="container">
    <a href="Controller?command=GO_TO_MAIN_PAGE" button class="btn btn-warning  badge-pill"><fmt:message
            key="button.commands.to_home"/>
    </a>
</div>

<c:if test="${sessionScope.message_fail != null}">
    <div id="error-alert" class="alert alert-danger mt-2 mb-2" role="alert">
        <h4 style="color:#de1212">${sessionScope.message_fail} ${sessionScope.att_exception}</h4>
    </div>
    ${sessionScope.remove("message_fail")}
    ${sessionScope.remove("att_exception")}
</c:if>
<c:if test="${sessionScope.message_success != null}">
    <div id="error-alert" class="alert alert-danger mt-2 mb-2" role="alert">
        <h4 style="color:#1297de">${sessionScope.message_success}</h4>
    </div>
    ${sessionScope.remove("message_success")}
</c:if>


<div class row>
    <div class="col-md-4"></div>

    <div class="container-fluid">
        <div class="border rounded col-md-4" style="background-color: #545481">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="REQUEST">
                <br>
                <div class="container">
                    <div class="form-group" class="form-outline">
                        <div class="col-md-4" id="sandbox-container">
                            <div class="input-daterange input-group" id="datepicker">
                                <input type="text" id="check-in" class="col-md-5" class="form-control setMargin"
                                       placeholder=
                                       <fmt:message key="requestUser.Check-in"/> name="start" required autofocus/>
                                <label class="form-label" class="control-label" for="check-in"></label>
                                <input type="text" id="check-out" class="col-md-5" class="form-control setMargin"
                                       placeholder=
                                       <fmt:message key="requestUser.Check-out"/>  name="end" required autofocus/>
                                <label class="form-label" class="control-label" for="check-out"></label>
                            </div>
                        </div>
                    </div>
                </div>

                <br>
                <select class="form-control badge-pill setMargin" name="quantity" id="quantity" class="col-sm-4">
                    <option value=1><fmt:message key="requestUser.management.Quantity"/>
                    </option>
                    <option value=1>1</option>
                    <option value=2>2</option>
                    <option value=3>3</option>
                    <option value=4>4</option>
                    <option value=5>5</option>
                    <option value=6>6</option>
                </select>
                <br>
                <select class="form-control badge-pill setMargin" name="category" id="category" class="col-sm-4">
                    <option value="1"><fmt:message key="requestUser.category_apartment.Apartment_category"/></option>
                    <option value="1"><fmt:message key="requestUser.category_apartment.Suite"/></option>
                    <option value="2"><fmt:message key="requestUser.category_apartment.Business_apartment"/></option>
                    <option value="3"><fmt:message key="requestUser.category_apartment.Luxury_apartment"/></option>
                </select>
                <br>
                <select class="form-control badge-pill setMargin" name="menu" id="menu" class="col-sm-4">
                    <option value="1"><fmt:message key="requestUser.Menu"/></option>
                    <option value="1"><fmt:message key="requestUser.Menu.Breakfast_only"/></option>
                    <option value="2"><fmt:message key="requestUser.Menu.Half-board"/></option>
                    <option value="3"><fmt:message key="requestUser.Menu.All_Inclusive"/></option>
                    <option value="4"><fmt:message key="requestUser.Menu.Ultra_All_Inclusive"/></option>
                </select>
                <br>
                <select class="form-control badge-pill setMargin" name="transfer" id="transfer" class="col-sm-4">
                    <option value="1"><fmt:message key="requestUser.Transfer"/>
                    </option>
                    <option value="1"><fmt:message key="requestUser.Transfer.Shuttle"/></option>
                    <option value="2"><fmt:message key="requestUser.Transfer.Taxi"/></option>
                    <option value="3"><fmt:message key="requestUser.Transfer.Vip_car"/></option>
                    <option value="4"><fmt:message key="requestUser.Transfer.Helicopter"/></option>
                </select>
                <br>
                <input type="submit" class=" btn-primary badge-pill btn-block " value=
                <fmt:message key="header.common.book_now"/>/>
            </form>
            <br>
        </div>
    </div>
    <div class="col-md-4"></div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js\bootstrap-datepicker.min.js"></script>
<script src="js\bootstrap-datepicker.ru.min.js"></script>
<script>$('#sandbox-container .input-daterange').datepicker({
    format: "yyyy-mm-dd",
    startDate: "2021-11-01",
    autoclose: true,
    todayHighlight: true,
    weekStart: 1,
    daysOfWeekHighlighted: "6,0",
});</script>

<br><br>
<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</body>
</html>


