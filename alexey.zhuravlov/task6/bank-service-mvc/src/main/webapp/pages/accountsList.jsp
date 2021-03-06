<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
         <span class="td"><c:out value="${account.userId}"/></span>
         <span class="td">
            <c:if test="${transfer}==enabled">
            <input type="button" onclick="location.href='withdraw?accountId=${account.accountId}';" value="Withdraw" />
            </c:if>
            <c:if test="${transfer}==disabled">
            <input type="button" onclick="location.href='withdraw?accountId=${account.accountId}';" value="Withdraw" disabled/>
            </c:if>
         </span>
         <span class="td">
             <c:if test="${transfer}==enabled">
             <input type="button" onclick="location.href='deposit?accountId=${account.accountId}';" value="Deposit"/>
             </c:if>
             <c:if test="${transfer}==disabled">
             <input type="button" onclick="location.href='deposit?accountId=${account.accountId}';" value="Deposit" disabled/>
             </c:if>
             </span>
         <span class="td">
         <input type="button" onclick="location.href='deleteAccount?accountId=${account.accountId}';" value="Delete account"/>
         </span>
     </form:form>
 </c:forEach>
 </div>
 <p>Go to <a href="<c:url value="/users/list" />">Users list</a></p>
</body>
</html>