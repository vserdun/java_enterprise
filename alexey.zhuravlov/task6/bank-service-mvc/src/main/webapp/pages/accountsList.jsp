<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
   <title>Simple library app</title>
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
         <span class="th">Id</span>
         <span class="th">Balance</span>
         <span class="th">User</span>
     </div>
 <c:forEach items="${accounts}" var="account">
     <form:form class="tr" modelAttribute="accountAttribute" action="" method="post">
         <span class="td"><c:out value="${account.accountId}"/></span>
         <span class="td"><c:out value="${account.accountBalance}"/></span>
         <span class="td"><c:out value="${account.accountUser}"/></span>
         <span class="td">
            <input type="button" onclick="location.href='edit?accountId=${account.accountId}';" value="Edit account"/>
         </span>
         <span class="td">
                     <input type="button" onclick="location.href='deleteAccount?accountId=${account.accountId}';" value="Delete account"/>
                  </span>
     </form:form>
 </c:forEach>
 </div>
 <input type="button" onclick="location.href='add';" value="Add new account"/>
</body>
</html>