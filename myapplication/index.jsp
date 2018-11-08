<%-- 
    Document   : index
    Created on : Nov 30, 2016, 10:12:45 AM
    Author     : nagesh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>
      
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
cloud storage</title>
  
  </head>
   
 <body>
   
     <p><font color="green">${mess}</font></p>

<c:remove var="mess" scope="session" /> 
      
  <h1 align="center">
MY CLOUD STORAGE</h1><br/><br><br/>
     
   <a href="usignin.jsp">USER SIGN IN</a>   
login into your account<br/><br/><br/>
 
       <a href="usignup.jsp">USER REGISTRATION</a>   
    new user <br/><br/><br/>
  
      <a href="adlogin.jsp">ADMIN</a>  
 administrator<br/><br/>
 
   </body>

</html>
