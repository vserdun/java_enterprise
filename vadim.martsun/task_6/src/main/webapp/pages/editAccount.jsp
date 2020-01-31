<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Bank App</title>
</head>
<body>
<p>Go to <a href="<c:url value="/accounts/accountList"/>">Accounts Page</a></p>
<p>Go to <a href="<c:url value="/users/usersList"/>">Users Page</a></p>

<div class="table">
    <form:form modelAttribute="accountAttribute" method="POST" action="update">
        <table >
            <tr>
                <td></td>
                <td><form:hidden path="id"/></td>
            </tr>
            <tr>
                <td>First name: </td>
                <td><form:input path="creationDate"/></td>
            </tr>
            <tr>
                <td>Last name: </td>
                <td><form:input path="amount"/></td>
            </tr>
            <tr>
                <td>Birth date :</td>
                <td><form:input path="userId"/></td>
            </tr>
        </table>
        <input type="submit" value="Save"/>
    </form:form>

</div>
</body>
</html>