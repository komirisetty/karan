<%-- 
    Document   : usignup
    Created on : Nov 30, 2016, 1:02:12 PM
    Author     : nagesh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>
  
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>user sign up</title>
    
</head>
    
<body>
 
       <p><font color="red">${message}</font></p>

<c:remove var="message" scope="session" /> 
 
       <h2 align="center">Registration</h2>

        
        <form action="uregistration">

          <p align="center">  Name : <input type="text" name="name"><br/><br/>
            
id : <input type="text" name="id"><br/><br/>
            password:<input type="password" name="pwd"><br/><br/>
            confirm password:<input type="password" name="cpwd"><br/><br/>
            phone number:<input type="number" name="pnumber"><br/><br/>
            email:<input type="email" name="email"> : ex:idname@gmail.com<br/><br/>
            
dob:<input type="date" name="dob"> : ex:25-JUL-1993<br/><br/>
            Country:
            <select name="country">  

<option selected="" value="Default">choose one</option> 
 
<option value="AF">Australia</option>
  
<option value="AL">Canada</option> 
 
<option value="INDIA">India</option>  

<option value="AS">Russia</option> 
 
<option value="AD">USA</option> 
 
</select><br/><br/>
         
   Sex:
            <input type="radio" name="msex" value="Male" />Male 

<input type="radio" name="fsex" value="Female" />Female<br/><br/>
Language: 
<input type="checkbox" name="en" value="en" />English 
<input type="checkbox" name="nonen" value="noen" />
Non English<br/><br/>

<input type="submit" value="register"></p>
</form>
  
  </body>
</html>
