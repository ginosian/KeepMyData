<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>

    <form:form action="${root}/signup" method="post">
      <c:set var="root" value="${pageContext.request.contextPath}"/>
      <table align="center" style="width: 100%; background-color: rgb(235, 248, 252); height: 100%; font-size:16px;">
        <tbody>
        <tr>
          <td colspan="3" style="table-layout: fixed; text-align: center;">
            <p>User First Name:</p>
            <p><input maxlength="40" name="name" style="height:40px; width:25%; resize: none" type="text" /></p>
            <p>User Login:</p>
            <p><input maxlength="40" name="username" style="height:40px; width:25%; resize: none" type="email" /></p>
            <p>Password:</p>
            <p><input maxlength="15" name="password" style="height:40px; width:25%; resize: none" type="password" /></p><br/>
            <p><input style="width:25%; font-size:24px; position:relative; white-space:normal" type="submit" value="Create" /></p>
          </td>
        </tr>
        </tbody>
      </table>
    </form:form>
</body>
</html>
