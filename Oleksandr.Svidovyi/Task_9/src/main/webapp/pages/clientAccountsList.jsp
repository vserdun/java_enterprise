<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Bank service</title>
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
          <span class="th">Currency</span>
      </div>
  <c:forEach items="${clientAccounts}" var="account">
      <form:form class="tr" modelAttribute="accountAttribute"  action="deleteAccount" method="get">
          <span class="td"><c:out value="${account.id}"/></span>
          <span class="td"><c:out value="${account.balance}"/></span>
          <span class="td"><c:out value="${account.currency}"/></span>
          <span class="td">
             <input id="id" name="id" type="hidden" value="${account.id}"/>
             <input id="balance" name="balance" type="hidden" value="${account.balance}"/>
             <input id="currency" name="currency" type="hidden" value="${account.currency}"/>
             <input type="submit" value="Delete"/>
          </span>
      </form:form>
  </c:forEach>
  </div>

  <input type="button" onclick="location.href='add';" value="Add account"/>
  <p><a href="<c:url value="/clients/list" />">Back to clients list</a></p>
</body>
</html>