<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
       <title>Task 6 - Simple Bank Service</title>
    </head>
    <body>
       <style>
           DIV.table
           {
               display:table; width: 80%;
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
               display: table-cell;
               font-weight: bold;
           }
       </style>

       <div class="table">
           <div class="tr">
                <span class="th">Id</span>
                <span class="th">Amount</span>
           </div>
           <c:forEach items="${accounts}" var="account">
               <form:form class="tr" modelAttribute="accountAttribute" action="" method="post">
               <span class="td"><c:out value="${account.id}"/></span>
                    <span class="td"><c:out value="${account.amount}"/></span>
                    <span class="td">
                        <input type="button" onclick="location.href='edit?id=${account.id}';" value="Edit account"/>
                    </span>
                    <span class="td">
                        <input type="button" onclick="location.href='deleteAccount?id=${account.id}';" value="Delete account"/>
                    </span>
               </form:form>
            </c:forEach>
        </div>
        <p><a href="<c:url value="/" />">Main Page</a></p>
        <p><a href="<c:url value="/users/list" />">Users</a></p>
    </body>
</html>