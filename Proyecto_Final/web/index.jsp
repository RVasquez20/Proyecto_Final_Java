<%-- 
    Document   : index
    Created on : 29/09/2020, 10:02:16 PM
    Author     : rodri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="JS/AllInOne.js"></script>
<link rel="stylesheet" href="CSS/EstilosIndex.css" type="text/css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="fuentes/iconic/css/material-design-iconic-font.min.css">
<title>JSP Page</title>
    </head>
    <body>
        <div class="container-login">
            <div class="wrap-login">
               
                    <form class="login-form validate-form" id="formLogin" action="sr_login" method="POST">
                         <span class="login-form-title">LOGIN</span>
                        <div class="wrap-input100">
                            <br>
                            <label class="input110">Usuario</label>
                            <input type="text" id="txt_usuario" name="txt_usuario" class="input100" placeholder="Escriba su usuario" required>
                            <span class="focus-efecto"></span>
                        </div>
                        <div class="wrap-input100">
                            <label class="input110">Contraseña</label>
                            <input type="password" id="txt_pass" name="txt_pass" class="input100" placeholder="Escriba su Contraseña" required>
                            <span class="focus-efecto"></span>
                         
                        </div>
                         <div class="wrap-input100">
                            <label class="input110">Codigo de Verificacion</label>
                            <input type="text" id="text_cod" name="txt_cod" class="input100" placeholder="Escriba su codigo" required>
                            <span class="focus-efecto"></span>
                         
                        </div>
                        <a style="color: #000000" data-toggle="modal" data-target="#ModalUsuario" href="#ModalUsuario">No tienes cuenta?</a>
                        <div class="container-login-form-btn">
                            <div class="wrap-login-form-btn">
                                <div class="login-form-bgbtn"></div>
                       
                        <input type="submit" value="Ingresar" class="btn login-form-btn" name="Ingresar" id="Ingresar" onclick="return ValidarCodigoLogin();">
                        <!--<input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn-b">-->
                    </div>
                </div>
                    </form>
       
            </div>
        </div>
        <!-- Fin form-->
        <div class="modal" id="ModalUsuario">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
       <h5 id="Titulo" class="input110">NUEVO REGISTRO</h5>
      </div>

      <!-- Modal body -->
      <div class="modal-body" >
     <form action="sr_login" method="post" class="form-group" enctype="multipart/form-data" name="formulario" role="form">
                                    
                                    <div class="wrap-input100">
                                     <label class="input110">Nombre usuario</label>                                    
                                    <input type="text" id="text_usuario" class="input100" name="txt_usuarionuevo" required="">
                                    <span class="focus-efecto"></span>
                                    </div>
                                    
                                    <div class="wrap-input100">
                                    <label class="input110">Nombres</label>                                    
                                    <input type="text" id="text_nombre" class="input100" name="txt_nombre" required="" onkeypress="return text(event);">
                                    <span class="focus-efecto"></span>
                                    </div>
                                    
                                    <div class="wrap-input100">
                                    <label class="input110">Apellidos</label>                               
                                    <input type="text" id="text_apellidos" class="input100" name="txt_apellidos" required="" onkeypress="return text(event);">
                                    <span class="focus-efecto"></span> 
                                    </div>
                                    
                                    <div class="wrap-input100">
                                     <label class="input110">Correo</label>                                  
                                    <input type="email" id="text_correo" class="input100" name="txt_correo" required="">
                                    <span class="focus-efecto"></span>
                                    </div>
                                    
                                    <div class="wrap-input100">
                                    <label class="input110">Password</label>                        
                                    <input type="password" id="text_pass" class="input100" name="txt_passnueva" required="">
                                    <span class="focus-efecto"></span>
                                    </div>
                                    
                                    <div class="wrap-input100">
                                       <label class="input110">Foto De Perfil</label>  
                                     <input type="file" id="imagen" name="archivo" class="col-md-8 btn" onchange="cargarFotodeperfil(this)">
                                <span class="focus-efecto"></span>
                                </div>
                                     
                                     <div class="wrap-input100">
                                    <label class="input110">Codigo de validacion</label>                               
                                    <input type="text" id="text_codigo" class="input100" name="txt_codigo" required="" maxlength="7">
                                    <span class="focus-efecto"></span>
                                    </div>
                                    
                              
                                     <input type="hidden" name="nombre" id="file">
                                     <input id ="Registrar" name="Registrar" value="Registrar" class="btn btn-success" type ="submit" onclick="return ValidarCodigo();">
        <button  class="btn btn-danger" data-dismiss="modal">Close</button>
                     
                        </form>
      </div>


          


    </div>
  </div>
</div>   
<script src="bootstrap/js/bootstrap.min.js"></script>    
    </body>
</html>
