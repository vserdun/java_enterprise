<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
   <title>Add user</title>
      <link rel="stylesheet"
      		 href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<form:form modelAttribute="userAttribute" action="addUser" method="post">
   <table>
       <tr>
           <td>
               <form:label path="firstName">User First Name</form:label>
           </td>
           <td>
               <form:input path="firstName"/>
           </td>
       </tr>
       <tr>
           <td>
               <form:label path="lastName">User Last Name</form:label>
           </td>
           <td>
               <form:input path="lastName"/>
           </td>
           <td>
               <form:label path="email">User email</form:label>
           </td>
           <td>
               <form:input path="email"/>
           </td>
       </tr>
       <tr>
           <td>
               <form:label path="password">User password</form:label>
           </td>
           <td>
               <form:input path="password"/>
           </td>
       </tr>
   </table>
   <input type="submit" value="Save"/>
</form:form>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>