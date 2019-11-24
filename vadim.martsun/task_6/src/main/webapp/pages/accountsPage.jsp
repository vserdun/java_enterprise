<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<style>
    DIV.table
    {
        display:table; width: 100%;;
    }

    FORM.tr, DIV.tr
    {
        display:table-row;
        padding: 10px;
        margin: 10px;
    }
    SPAN.td
    {
        display:table-cell;
        padding: 10px;
        margin: 10px;
    }
    SPAN.th
    {
        display:table-cell;
        font-weight: bold;
    }
</style>

<div class="table">
    <div class="tr">
        <span class="th">Creation date</span>
        <span class="th">Amount</span>
        <span class="th">First name</span>
        <span class="th">Last name</span>
        <span class="th">Birth date</span>
        <span class="th">Gender</span>
    </div>
    <c:forEach items="${accounts}" var="account">

        <form:form class="tr" modelAttribute="accountAttribute" method="post">
            <span class="td"><c:out value="${account.creationDate}"/></span>
            <span class="td"><c:out value="${account.amount}"/></span>
            <span class="td"><c:out value="${account.user.firstName}"/></span>
            <span class="td"><c:out value="${account.user.lastName}"/></span>
            <span class="td"><c:out value="${account.user.birthDate}"/></span>
            <span class="td"><c:out value="${account.user.gender}"/></span>
        </form:form>
    </c:forEach>
</div>


<input type="button" onclick="location.href='add';" value="Add new account"/>
</body>
</html>