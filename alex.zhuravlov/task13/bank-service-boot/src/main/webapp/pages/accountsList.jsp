<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
   <title>Accounts list</title>
      <link rel="stylesheet"
      		 href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
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
<jsp:include page="navbar.jsp"/>
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
         <span class="td"><c:out value="${account.user.userId}"/></span>
         <span class="td">
            <c:choose>
                <c:when test="${transfer == true}">
                    <input type="button" onclick="location.href='deposit?accountId=${account.accountId}';" value="Deposit"/>
                </c:when>
                <c:otherwise>
                    <input type="button" onclick="location.href='deposit?accountId=${account.accountId}';" value="Deposit" disabled/>
                </c:otherwise>
            </c:choose>
         </span>
         <span class="td">
          <c:choose>
            <c:when test="${transfer == true}">
                <input type="button" onclick="location.href='withdraw?accountId=${account.accountId}';" value="Withdraw" />
            </c:when>
            <c:otherwise>
                <input type="button" onclick="location.href='withdraw?accountId=${account.accountId}';" value="Withdraw" disabled/>
            </c:otherwise>
          </c:choose>
         </span>
         <span class="td">
              <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
                    <input type="button" onclick="location.href='deleteAccount?accountId=${account.accountId}';" value="Delete account"/>
              </sec:authorize>
         </span>
         <span class="td">
              <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
                    <input type="button" onclick="location.href='addCardForm';" value="Add card"/>
              </sec:authorize>
         </span>
     </form:form>
 </c:forEach>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
         <input type="button" onclick="location.href='/users/addAccount?userId=${currentUserId}';" value="Add my account"/>
    </sec:authorize>

 </div>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
     <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>