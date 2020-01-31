<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
   <title>Deposit</title>
      <link rel="stylesheet"
      		 href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<form:form modelAttribute="deposit" action="depositAccount" method="post">
   <table>
       <tr>
            <td>
                <form:hidden path="accountId"/>
            </td>
       </tr>
       <tr>
           <td>
               <form:label path="amount">Amount</form:label>
           </td>
           <td>
               <form:input path="amount"/>
           </td>
       </tr>
   </table>
   <input type="submit" value="Save"/>
</form:form>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>