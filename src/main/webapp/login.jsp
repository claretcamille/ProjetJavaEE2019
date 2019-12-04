<%-- 
    Document   : login
    Created on : 4 déc. 2019, 14:09:01
    Author     : Aymeric Prévost
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div style="color:red">${errorMessage}</div>
        <form action="LoginPage" method="POST">
            login : <input name='loginParam'><br>
            password : <input name='passwordParam' type='password'><br>
            <input type='submit' name='action' value='login'>
	</form>
    </body>
</html>
