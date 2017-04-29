<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<form action="/blog/dowrite" method="post">
    <table>

        <tbody>
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" name="author"/>
            </td>
        </tr>
        <tr>
            <td>
                标题
            </td>
            <td>
                <input type="text" name="title"/>
            </td>
        </tr>
        <tr>
            <td>
                内容
            </td>
            <td>
                <input type="text" name="content"/>
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
