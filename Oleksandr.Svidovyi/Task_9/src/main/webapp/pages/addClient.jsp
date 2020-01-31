<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Bank service</title>
</head>
<body>
<form:form modelAttribute="clientAttribute" action="addClient" method="post">
    <table>


        <tr>
            <td>
                <form:label path="firstName">First name:</form:label>
            </td>
            <td>
                <form:input path="firstName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastName">Last name:</form:label>
            </td>
            <td>
                <form:input path="lastName"/>
            </td>
        </tr>
        <tr>
             <td>
                 <form:label path="email">Email:</form:label>
             </td>
             <td>
                 <form:input path="email"/>
             </td>
         </tr>
    </table>
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>