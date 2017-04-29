<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<h2>Hello World!</h2>
<div>

        <table border="1" width="100%" cellspacing="0" summary="一个基本完整的table原型结构">
            <caption>
                博客列表
            </caption>



            <colgroup>
                <col class="col1" />
                <col span="2" class="col2" />
            </colgroup>
            <thead>
            <tr>
                <th>id</th>
                <th>作者</th>
                <th>标题</th>
                <th>操作</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <td colspan="4">Copyright &copy; 2011 jzxue.com</td>
            </tr>
            </tfoot>
            <tbody>

            <c:forEach items="${blogs}" var="blogs">
                <tr>
                    <td>${blogs.title}</td>
                    <td>${blogs.author}</td>
                    <td>${blogs.content}</td>
                    <td><a href="/blog/edit?title=${blogs.title}">编辑</a><a href="/blog/del">删除</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <a href="/blog/write">添加博客</a>

</div>


</body>
</html>
