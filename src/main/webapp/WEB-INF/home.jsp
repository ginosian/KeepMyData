<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        a:hover:not(.active) {
            background-color: #111;
        }

        .active {
            background-color: #4CAF50;
        }        </style>
</head>
<body>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<ul>
    <li>
        <a href="${root}/admin/update_user">Update profile</a></li>
    <li>
        <a class="active">Catalog List</a></li>
    <li>
        <a href="${root}/admin/create_catalog">Create Catalog</a></li>
    <li>
        <a href="${root}/admin/logout">Logout</a></li>
</ul>
<table align="center" border="0" cellpadding="0" cellspacing="0"
       style="width: 100%; height: 95%; background-color: rgb(235, 248, 252)">
    <tbody>
    <tr>
        <td>Url</td>
        <td>Description</td>
        <td>Image</td>
    </tr>
    <tr>
        <td colspan="3">${noCatalogs}</td>
    </tr>
    <tr>
        <td colspan="3">
            <c:forEach items="${catalogs}" var="catalog">
                <h3>${catalog.getUrl()}</h3>
                <h3>${catalog.getDescription()}</h3>
                <h3>${catalog}</h3>
                <p></p>
            </c:forEach>
        </td>

    </tr>
    </tbody>
</table>
</body>
</html>
