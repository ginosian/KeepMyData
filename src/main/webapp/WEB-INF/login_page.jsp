<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<form method="post">
    <c:set var="root" value="${pageContext.request.contextPath}"/>

<table align="center"style="width: 100%; height: 70%">
    <tbody>
        <tr>
            <td style="table-layout: fixed; text-align:center; font-size:18px">
                <br>Username:<br/>
                <input maxlength="40" type="email" id="username" name="username" style="font-size:18px; width:25%; resize: none; background-color: #feffbb"/><br />
                <br>Password:<br />
                <input maxlength="15" type="password" id="password" name="password" style="font-size:18px; width:25%; resize: none; background-color: #feffbb"/></p>
                <label><input type="checkbox" name="remember-me"> Remember Me</label>
            </td>
        </tr>
        <tr>
            <td style="table-layout: fixed; text-align: center">
                <input style="width:25%; font-size:20px; position:relative; white-space:normal" type="submit" value="LOGIN"/></td>
        </tr>
        <tr>
            <td colspan="2" style="table-layout: fixed; text-align: center">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p style="color: #c51202">Invalid username or password.</p>
                    </div>
                </c:if>
            </td>
        </tr>
    </tbody>
</table>
</form>

<table align="center"style="width: 100%; height: 30%">
    <tbody>
    <tr>
        <td style="table-layout: fixed; text-align: center">
            <input style="width:25%; font-size:20px; position:relative; white-space:normal" type="submit" onclick="location.href='${root}/signup'" value="SIGN UP"/></td>
    </tr>
    </tbody>
</table>
</html>
