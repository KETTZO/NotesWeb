<%@page import="java.util.ArrayList"%>
<%@page import="com.notesweb.notesweb.proyecto.Notas"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://fonts.googleapis.com/css2?family=Oswald&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <title>Notas</title>
    <fonts>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap" rel="stylesheet">
    </fonts>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="Notas_style.css">
    
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/datetime/1.1.2/css/dataTables.dateTime.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.4.0/css/select.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.2.3/css/buttons.dataTables.min.css">
<!--
    <script type="text/javascript">
        $(document).ready( function (){
            $('#Notas').DataTable();
        });
    </script>-->
    
</head>
<body style="background: url('Fondo.jpg') no-repeat; background-size: cover;">
    
    <% ArrayList<Notas> ListaNotas = (ArrayList<Notas>) request.getAttribute("ListaNotas"); %>
    
<h1 class="Empresa">Notesweb</h1>
<h3 class="NUsuario"><%= request.getSession().getAttribute("usuario") %></h3>


<div class="TablaN">
<table border="0" cellspacing="5" cellpadding="5">
    <tbody><tr>
        <td>Fecha minima:</td>
        <td><input type="text" id="min" name="min"></td>
    </tr>
    <tr>
        <td>Fecha Maxima:</td>
        <td><input type="text" id="max" name="max"></td>
    </tr>
</tbody></table>


    <table id="Notas" class="display">
        <thead>
            <tr>
                <th>ID</th>
                <th>Contenido</th>
                <th>Fecha</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items=${ListaNotas} var="notas">
            <tr>
                <td>${notas.ID}</td>
                <td>${notas.texto}</td>
                <td>${notas.creacion}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>



    

    
            
            <form action="EditarNota_Servlet" method="POST">
                <input type="hidden" name="IDNota" id="IDNota" value=''>
                <input type="submit" value="Editar" onclick="sacarID()">
            </form>
            <form action="EliminarNota_Servlet" method="POST">
                <input type="hidden" name="IDNotaE" id="IDNotaE" value=''>
                <input type="submit" value="Eliminar" onclick="sacarID1()">
            </form>
            
</div>




<input type="checkbox" id="active">
      <label for="active" class="menu-btn"><span></span></label>
      <label for="active" class="close"></label>
  <div class="wrapper">
  <ul>
  
  <li><a href="Escribir.jsp">ESCRIBIR</a></li>
  <li><a href="Perfil.jsp">PERFIL</a></li>
  <li><a href="#"></a></li>
  <li><a href="InicioSesion.html">CERRAR SESION</a></li>
  </ul>
  </div>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.0/js/jquery.dataTables.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.2/moment.min.js"></script>
  <script src="https://cdn.datatables.net/datetime/1.1.2/js/dataTables.dateTime.min.js"></script>
  <script src="https://cdn.datatables.net/select/1.4.0/js/dataTables.select.min.js"></script>
  <script src="https://cdn.datatables.net/1.12.0/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>
  <script src="Notas.js"></script>
  <script type="text/javascript"> 
var ID1;
var minDate, maxDate;
 
 // Custom filtering function which will search data in column four between two values
 $.fn.dataTable.ext.search.push(
     function( settings, data, dataIndex ) {
         var min = minDate.val();
         var max = maxDate.val();
         var date = new Date( data[4] );
  
         if (
             ( min === null && max === null ) ||
             ( min === null && date <= max ) ||
             ( min <= date   && max === null ) ||
             ( min <= date   && date <= max )
         ) {
             return true;
         }
         return false;
     }
 );
  
 $(document).ready(function() {
     // Create date inputs
     
     minDate = new DateTime($('#min'), {
         format: 'MMMM Do YYYY'
     });
     maxDate = new DateTime($('#max'), {
         format: 'MMMM Do YYYY'
     });
  
     // DataTables initialisation
     var table = $('#Notas').DataTable( {
    select: true,
   
    select :{
        items:'row',
        style:'single'
    }
} );
  
     // Refilter the table
     $('#min, #max').on('change', function () {
         table.draw();
     });
     

     $('#Notas tbody').on( 'click', 'td', function () {
var allrow = table.row(this).data();
var thisRowCell1 = allrow[0];
ID1 = thisRowCell1;

} );

 });
 function  sacarID(){
     document.getElementById("IDNota").value = ID1;
 }
 
 function sacarID1(){
document.getElementById("IDNotaE").value = ID1;
}
      </script>


</body>
</html>

