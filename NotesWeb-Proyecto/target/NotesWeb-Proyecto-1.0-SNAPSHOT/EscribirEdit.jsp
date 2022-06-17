<%-- 
    Document   : EscribirEdit
    Created on : 25 may. 2022, 16:42:17
    Author     : Chavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Escribir Nota</title>
    <link href="https://fonts.googleapis.com/css2?family=Oswald&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <fonts>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap" rel="stylesheet">
    </fonts>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="Escribir_style.css">
    
</head>
<body style="background: url('Fondo.jpg') no-repeat; background-size: cover;">
    
    <form action="TraerNotas_Servlet" method="POST">

        <input type="submit" value="Notas" class="MenuNotas">

    </form>
    <h1 class="Empresa">Notesweb</h1>
    
    <script>document.getElementById("IDNotaE2").value = Minota;</script>


<form action="EscribirEdit_Servlet" method="POST" class="Notepad">
    <textarea id="NotaE" name="Nota" class="NotaE" rows="10" cols="250" minlength="5" maxlength="500">${textoNota}</textarea>
    <br><br>
    <input type="hidden" name="IDNotaE2" id="IDNotaE2" value=<%= request.getAttribute("IDNota2") %>>
    
    <input type="submit" value="Guardar" class="BtnGuardarNota">
</form>

<h3 class="NUsuario"><%= request.getSession().getAttribute("usuario") %> ${request.getSession().getAttribute("usuario")}</h3>







    <input type="checkbox" id="active">
      <label for="active" class="menu-btn"><span></span></label>
      <label for="active" class="close"></label>
  <div class="wrapper">
  <ul>

  <li><a href="#">ESCRIBIR</a></li>
  <li><a href="Perfil.jsp">PERFIL</a></li>
  <li><a href="#"></a></li>
  <li><a href="InicioSesion.html">CERRAR SESION</a></li>
  </ul>
  </div>
</body>
</html>
