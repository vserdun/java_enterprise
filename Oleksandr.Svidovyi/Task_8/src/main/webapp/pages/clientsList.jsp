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
          <span class="th">FirstName</span>
          <span class="th">SecondName</span>
      </div>
  <c:forEach items="${clients}" var="client">
      <form:form class="tr" modelAttribute="clientAttribute">
          <span class="td"><c:out value="${client.id}"/></span>
          <span class="td"><c:out value="${client.firstName}"/></span>
          <span class="td"><c:out value="${client.secondName}"/></span>
          <span class="td">
             <input type="button" onclick="location.href='deleteClient?id=${client.id}';" value="Delete"/>
          </span>
           <span class="td">
              <input type="button" onclick="location.href='${client.id}/account/list';" value="Check accounts"/>
           </span>
      </form:form>
  </c:forEach>
  </div>

  <input type="button" onclick="location.href='add';" value="Add new client"/>
</body>
</html>