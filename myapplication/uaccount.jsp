<%-- 
    Document   : uaccount
    Created on : Dec 8, 2016, 10:09:56 AM
    Author     : nagesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>user account</title>
    </head>
    <body>
         <p><font color="red">${mes}</font></p>
<c:remove var="mes" scope="session" /> 
        <h1>WELCOME!</h1>
        <a href="upload.jsp">UPLOAD</a><br/><br/><br/>
        <a href="download.jsp">DOWNLOAD</a><br/><br/><br/>
        <a href="delete.jsp">DELETE</a>
    </body>
</html>
