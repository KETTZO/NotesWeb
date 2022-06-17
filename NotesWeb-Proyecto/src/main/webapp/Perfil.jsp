<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Perfil</title>
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
   <link rel="stylesheet" href="Perfil_style.css">
   <script src="EditarPerfil.js"></script>
</head>
<body style="background: url('Fondo.jpg') no-repeat; background-size: cover;">
    <form action="TraerNotas_Servlet" method="POST">

        <input type="submit" value="Notas" class="MenuNotas">

    </form>
    <h1 class="Empresa">Notesweb</h1>
    <h3 class="NUsuario">${usuario} </h3>

    <form autocomplete="off" action="EditarEmpleado_Servlet" method="POST" class="container">

        <h2 class="" style="height: 23px; font-family: 'Roboto Condensed', sans-serif; color: rgb(68, 185, 221);">${usuario}</h2>
        <hr style="height: 2px; color: black; border: black;" >
        <div class="row">
             <div class="form-group col">
                <input class="form-control" type="text" id="nombre" name="nombre" value="${nombreusuario}" required placeholder="Nombre" pattern="^[a-zA-ZÃ-Ã¿\u00f1\u00d1]+(\s*[a-zA-ZÃ-Ã¿\u00f1\u00d1]*)*[a-zA-ZÃ-Ã¿\u00f1\u00d1]+$" title="solo ingrese letras">
            </div>
            <div class="form-group col">
                <input class="form-control" type="text" id="apellido" name="apellido" value="${ApellidoU}" required placeholder="Apellido" pattern="^[a-zA-ZÃ-Ã¿\u00f1\u00d1]+(\s*[a-zA-ZÃ-Ã¿\u00f1\u00d1]*){2}$" title="Solo ingrese letras">
            </div>
        </div>
        <input class="form-control" type="email" id="correo" name="correo" value= "${CorreoU}" required placeholder="Correo electrónico">
        <div class="row">
            <div class="form-group col">
                <input class="form-control" type="password" id="contra" name="contra" value="${ContraseñaU}" required placeholder="Contraseñaa" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
            </div>
            <div class="form-group col">
                <input class="form-control" type="password" id="contra2" name="contra2" required placeholder="Confirmar contraseÃ±a" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
            </div>
        </div>
        <p>Edad: ${edad}</p>
        
        <label for="fecha" style="color: #888888;">Fecha de nacimiento</label>
        <div class="datepicker">
        <input type="date" id="currentDate" style="color: #888888;" value="${fecha}"  name="fecha"  min="1900-01-01" required>
        </div>
        <input type="submit"  value="Editar" class="BtnSiRegistro" onclick="ClickRegistro()">

        </form>


    <input type="checkbox" id="active">
      <label for="active" class="menu-btn"><span></span></label>
      <label for="active" class="close"></label>
  <div class="wrapper">
  <ul>
      <% String username = request.getParameter("usuario");
          session.setAttribute("username", username); %>
  <!--<li><a href="TraerNotas_Servlet">NOTAS</a></li>-->

  <li><a href="Escribir.jsp">ESCRIBIR</a></li>
  <li><a href="#">PERFIL</a></li>
  <li><a href="#"></a></li>
  <li><a href="InicioSesion.html">CERRAR SESION</a></li>
  </ul>
  </div>
</body>

</html>