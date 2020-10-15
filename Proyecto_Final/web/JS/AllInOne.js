function cargarFotodeperfil(elemento){
     var file = elemento.files[0]; 
    var objHidden = document.formulario.nombre;
    objHidden.value = file.name;
}

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

};
               
function ValidarCodigo(){

    
    var expresion,usuario;
    
    usuario = document.getElementById("text_codigo").value;
    
    expresion = new RegExp(/^U2020\D[A-Z{2}]/);
    
    if(expresion.test(usuario)){
       return true;
    } else{
        alert("Error codigo de validacion invalido");
        return false;
    }
};
function ValidarCodigoLogin(){

    
    var expresion,usuario;
    
    usuario = document.getElementById("text_cod").value;
    
    expresion = new RegExp(/^U2020\D[A-Z{2}]/);
    
    if(expresion.test(usuario)){
       return true;
    } else{
        alert("Error codigo de validacion invalido");
        return false;
    }
};
var text = function (e){
  var event = e || window.event;
  var key = event.keyCode || event.wchich;
  
  var tecla= String.fromCharCode(key);
  var cadena = /[a-zA-Z\s]/;
  
  if(!tecla.search(cadena)){
      return true;
  } else {
      return false;
  }
};


var decimal = function (e){
  var event = e || window.event;
  var key = event.keyCode || event.wchich;
  
  var tecla= String.fromCharCode(key);
  var cadena = /([0-9+\.])/;
  
  if(!tecla.search(cadena)){
      return true;
  } else {
      return false;
  }
};

var entero = function (e){
  var event = e || window.event;
  var key = event.keyCode || event.wchich;
  
  var tecla= String.fromCharCode(key);
  var cadena = /\d/;
  
  if(!tecla.search(cadena)){
      return true;
  } else {
      return false;
  }
};

  function LimpiarProveedores(){
        $("#txt_id_Proveedores").val(0);
       $("#txt_Proveedor").val('');
       $("#txt_NIT").val('');
       $("#txt_Direccion").val('');
       $("#txt_Telefono").val('');
    };
      function LimpiarCompras(){
        $("#txt_id_Compra").val(0);
       $("#ListaProveedores").val(0);
       $("#txt_No_Orden").val('');
       $("#txt_Fecha_Orden").val('');
       $("#txt_Fecha_Ingreso").val('');
    };
    function LimpiarClientes(){

        $("#txt_id").val(0);
       $("#txt_nombres").val('');
       $("#txt_apellidos").val('');
       $("#txt_nit").val('');
       $("#txt_telefono").val('');
       $("#txt_correo").val('');
       $("#txt_fecha").val('');
       
       
    };
    function LimpiarVentas() {
    $("#txt_id").val(0);
    $("#txt_nofactura").val('');
    $("#txt_serie").val('');
    $("#txt_fechafactura").val('');
    $("#txt_idcliente").val(0);
    $("#txt_idempleado").val('');
    $("#txt_fechaingreso").val('');
};