<%-- 
    Document   : upload
    Created on : Dec 7, 2016, 11:41:41 AM
    Author     : nagesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Page</title>
    </head>
    <body>
   <h3> Choose File to Upload in Server </h3>
            <form action="upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="upload" />
            </form>       
    </body>
</html>
