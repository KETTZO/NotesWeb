      var today = new Date()

      //Calcula la edad//
       function _calcAge(birthDate, givenDate) {
        givenDate = new Date(today);
        var dt1 = document.getElementById('currentDate').value;
        var birthDate = new Date(dt1);
        var years = (givenDate.getFullYear() - birthDate.getFullYear());

        if (givenDate.getMonth() < birthDate.getMonth() ||
     givenDate.getMonth() == birthDate.getMonth() && givenDate.getDate() < birthDate.getDate()) {
            years--;
        }

        return years;
    }





function ClickEditar(){
    
    
            var date = new Date();
            var currentDate = date.toISOString().slice(0,10);
            //date.getFullYear= date.getFullYear-10;
            var currentTime = date.getHours() + ':' + date.getMinutes();
            
            document.getElementById('currentDate').max = currentDate;
            document.getElementById('currentTime').value = currentTime;
        

    const date= document.getElementById("currentDate")
    const fecha = new Date(date.value);
    const fecha2 = new Date()
    //var fromato = fecha2.toISOString().split('T')[0];
    var diff = Math.floor((fecha2-fecha)/(24*3600*1000));
    if(!date.validity.valid){
        return;
    }
    if(!(diff >= 4380)){
        alert("Necesitas tener al menos 12 años para registrarte");
        return;
    }

    const inputnombre= document.getElementById("nombre");
    const inputapellido= document.getElementById("apellido");
    const inputcorreo= document.getElementById("correo");
    const inputcontra= document.getElementById('contra');
    const inputcontra2= document.getElementById('contra2');

    if(inputcontra.value != inputcontra2.value){
        alert("Las Contraseñas no coinciden");
        return;
    }
    else{
        return;    
    }
    
}
