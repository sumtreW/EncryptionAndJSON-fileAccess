<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>

<%--此数据中需指定一条数据作为主键，这里是标题，主键不能更改--%>

<form action="/blog/doEdit" method="post">
    <table>

        <tbody>
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" name="author" value="${ablog.author}"/>
            </td>
        </tr>
        <tr>
            <td>
                标题
            </td>
            <td>
                <input type="text" name="title" value="${ablog.title}"/>
            </td>
        </tr>
        <tr>
            <td>
                内容
            </td>
            <td>
                <input type="text" name="content" value="${ablog.content}"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="提交"></td>
        </tr>

        </tbody>

    </table>
</form>


</body>
</html>
