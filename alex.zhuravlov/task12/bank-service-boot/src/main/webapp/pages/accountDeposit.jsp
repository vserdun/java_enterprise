<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
   <title>Deposit</title>
</head>
<body>
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

</body>
</html>