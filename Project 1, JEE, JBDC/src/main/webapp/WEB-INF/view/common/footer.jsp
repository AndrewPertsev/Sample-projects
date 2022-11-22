<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ taglib prefix="mytag" uri="customtag" %>


<footer class="footer mt-auto py-3">
    <div class="container text-center text-lg-start text-muted">
        <hr class="dropdown-divider">
        <mytag:copyright/>

    </div>
</footer>
