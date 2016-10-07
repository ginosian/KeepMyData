<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<ul>
    <li>
        <a href="${root}/admin/update_user">Update profile</a></li>
    <li>
        <a href="${root}/admin/home">Create List</a></li>
    <li>
        <a class="active">Crate Catalog</a></li>
    <li>
        <a href="${root}/admin/logout">Logout</a></li>
</ul>
<table align="center" border="0" cellpadding="0" cellspacing="0"
       style="width: 100%; height: 95%; background-color: rgb(235, 248, 252)">
    <form action="upload" method="post" enctype="multipart/form-data">
        <tbody>
        <tr>
            <td style="table-layout: fixed; text-align: center;">
                <p>URL:</p>
                <p><input maxlength="200" name="url" style="height:40px; width:25%; resize: none" type="text"/></p>
                <p>Description:</p>
                <p><input maxlength="200" name="description" style="height:40px; width:25%; resize: none" type="text"/>
                </p>
                <p></p>
                <p><input type="file" name="image" value="Choose image"></p><br/>
                <p><input style="width:25%; font-size:24px; position:relative; white-space:normal" type="submit"
                          value="Save"/></p>
            </td>
        </tr>
        </tbody>
    </form>
</table>
</body>
</html>