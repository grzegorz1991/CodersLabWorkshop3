<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Include header --%>
<%@ include file="/header.jsp" %>

<div class="container">
  <h1>Użytkownik ${user.id}</h1>
  <table class="table">
    <tr>
      <th>ID</th>
      <th>Email</th>
      <th>Username</th>
    </tr>
    <tr>
      <td>${user.id}</td>
      <td>${user.email}</td>
      <td>${user.userName}</td>
    </tr>
  </table>
</div>
<a href="${pageContext.request.contextPath}/user/list">&emsp; Powrót do listy</a>
</div>
<%-- Include footer --%>
<%@ include file="/footer.jsp" %>