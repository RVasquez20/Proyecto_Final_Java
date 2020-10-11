function cargarArchivo(elemento){
   
    
  /*  var arr=elemento.value.split('\\');
    console.log(arr);
    var file = elemento.files[0]; 
    var objHidden = document.formulario.nombre;
    objHidden.value = arr[arr.length-1];
    document.getElementById("nombrep").value=arr[arr.length-1];
    document.formulario.target = "null";*/
     
    var file = elemento.files[0]; 
    var objHidden = document.formulario.nombre;
    objHidden.value = file.name;

}
function Alerta(){

    alert("Proceso terminado");
    setTimeout("location.href='productos.jsp", 3000);
  
}



