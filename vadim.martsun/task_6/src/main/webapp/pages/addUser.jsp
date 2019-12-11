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
    <form:form modelAttribute="userAttribute" action="addUser" method="post">
        <table>
        <tr>
            <td><form:label path="firstName">First name</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><span><form:label path="lastName">Last name</form:label></span></td>
            <td><span><form:input path="lastName"/></span></td>
        </tr>
        <tr>
            <td><span><form:label path="birthDate">Birth date (Format: yyyy-MM-dd)</form:label></span></td>
            <td><span><form:input path="birthDate"/></span></td>
        </tr>
            <tr>
                <td><span><form:label path="gender">Gender</form:label></span></td>
                <td>
                    <form:radiobutton path="gender" value="male" label="Male"/>
                    <form:radiobutton path="gender" value="female" label="Female"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="Save"/>
    </form:form>
</div>
</body>
</html>