<%-- 
    Document   : ErrorJSP
    Created on : 21 may. 2022, 22:25:09
    Author     : misagamer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script> 
            alert("Asegurese de ingresar bien todos los datos");
            window.location.href = "index.html"
        </script>
    </head>
    <body>
    
        <c:redirect url="/home.html"/>
        
    </body>
</html>
