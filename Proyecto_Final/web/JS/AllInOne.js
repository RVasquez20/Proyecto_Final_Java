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
 

 function LimpiarEmpleados() {
    $("#txt_id").val(0);
       $("#txt_nombres").val('');
       $("#txt_apellidos").val('');
       $("#txt_direccion").val('');
       $("#txt_telefono").val('');
       $("#txt_DPI").val('');
       $("#drop_genero").val(0);
       $("#txt_fn").val('');
      $("#txt_fechaInicio").val('');
        $("#lbl_fechaIngreso").hide();
                        $("#txt_fechaIngreso").hide();
       $("#drop_puesto").val(0);
        $("#btn_agregar").show();
    $("#btn_modificar").hide();
    $("#btn_eliminar").hide();
};

function LimpiarMarcas() {
    $("#txt_id_Marcas").val(0);
    $("#txt_Marca").val('');

    $("#btn_agregar").show();
    $("#btn_modificar").hide();
    $("#btn_eliminar").hide();
   
};

function LimpiarPuestos() {
     $("#txt_id").val(0);
    $("#txt_puestos").val('');
     $("#btn_agregar").show();
    $("#btn_modificar").hide();
    $("#btn_eliminar").hide();
};

function LimpiarComprasDetalle() {
    $("#txt_id").val(0);
    $("#drop_Producto").val(0);
    $("#txt_PrecioUnitario").val('');
    $("#txt_Cantidad").val('');

    $("#txt_id_Compra").val(0);
    $("#ListaProveedores").val(0);
    $("#txt_No_Orden").val('');
    $("#txt_Fecha_Orden").val('');
    
 $("#lbl_Fecha_Ingreso").hide();
                        $("#txt_Fecha_Ingreso").hide();
    $("#btn_agregar").show();
                        $("#btn_modificar").hide();
                        $("#btn_eliminar").hide();
};

function LimpiarVentasDetalle() {
    $("#txt_id_Ventas").val(0);
       $("#txt_idventas").val(0);
       $("#drop_Producto").val(0);
       $("#txt_PrecioUnitario").val('');
       $("#txt_Cantidad").val('');
       $("#txt_nofactura").val('');
       $("#txt_serie").val('');
       $("#txt_fechafactura").val('');
       $("#txt_idcliente").val(0);
       $("#txt_idempleado").val(0);
       $("#btn_agregar").show();
    $("#btn_modificar").hide();
    $("#btn_eliminar").hide();
    
 $("#lbl_fechaingreso").hide();
                        $("#txt_fechaingreso").hide();
};

function LimpiarProductos() {
     $("#txt_id").val(0);
    $("#box_marcas").val(0);
   $("#txt_producto").val('');
   $("#txt_descripcion").val('');
   $("#txt_preciocosto").val('');
   $("#txt_precioventa").val('');
   $("#txt_exitencias").val('');
   $("#txt_Imagen").val('');
     $("#btn_agregar").show();
      $("#lbl_fecha").hide();
                        $("#txt_FechaIngreso").hide();
     $("#btn_actualizar").hide();
                        $("#btn_modificar").hide();
                        $("#btn_eliminar").hide();
                        
};




function LimpiarVentasDetalleprod() {
    $("#txt_id_Ventas").val(0);

       $("#drop_Producto").val(0);
       $("#txt_PrecioUnitario").val('');
       $("#txt_Cantidad").val('');

       $("#btn_agregar").show();
    $("#btn_modificarp").hide();
    $("#btn_eliminarp").hide();
    

};
