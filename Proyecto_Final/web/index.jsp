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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container mt-4 col-lg-4">
            <div class="card col-sm-10">
                <div class="card-body">
                    <form class="form-sign" action="sr_login" method="POST">
                        <div class="form-group text-center">
                            <h3>Login</h3>
                            <img src="upload/2551513.jpg" alt="" height="70" width="170"/>
                            <label>Bienvenido</label>
                        </div>
                        <div class="form-group">
                            <label>Usuario</label>
                            <input type="text" id="txt_usuario" name="txt_usuario" class="form-control" placeholder="Escriba su usuario" required>
                        </div>
                        <div class="form-group">
                            <label>Contraseña</label>
                            <input type="password" id="txt_pass" name="txt_pass" class="form-control" placeholder="Escriba su Contraseña" required>
                         <a style="color: #000000" data-toggle="modal" data-target="#ModalUsuario" href="#ModalUsuario">No tienes cuenta?</a>
                        </div>
                        <input type="submit" value="Ingresar" class="btn btn-primary btn-b" name="Ingresar" id="Ingresar"/>
                        <!--<input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn-b">-->
                    </form>
                </div>
            </div>
        </div>
        <!-- Fin form-->
         <div id="ModalUsuario" class="modal fade register" role="dialog">
             <button type="button" class="close" data-dismiss="modal">×</button>
            <div class="register-full" role="document">
                <div class="register-right">
                    <div class=" form-group">
                        <br>
                        <br>
                        <h5 style="color: #ffffff; text-align: center" id="Titulo">NUEVO REGISTRO</h5>
                        <hidden class="modal-dialog" data-dismiss="modal"/>    
                    </div>
                    <div class="form-register">
                        <form action="sr_login" method="post">
                            <div class="fiels-grid container">
                               
                                <div class="styled-input agile-styled-input-top">
                                    <input type="text" id="text_usuario" name="txt_usuario" required="">
                                    <label style="color: #ffffff">Nombre usuario</label>
                                </div>
                                <div class="styled-input agile-styled-input-top">
                                    <input type="text" id="text_nombre" name="txt_nombre" required="" onkeypress="return text(event);">
                                    <label style="color: #ffffff">Nombres</label>
                                </div>
                                <div class="styled-input agile-styled-input-top">
                                    <input type="text" id="text_apellidos" name="txt_apellidos" required="" onkeypress="return text(event);">
                                    <label style="color: #ffffff">Apellidos</label>
                                </div>
                                <div class="styled-input agile-styled-input-top">
                                    <input type="email" id="text_correo" name="txt_correo" required="">
                                    <label style="color: #ffffff">Correo</label>
                                </div>
                                <div class="styled-input agile-styled-input-top">
                                    <input type="password" id="text_pass" name="txt_pass" required="">
                                    <label style="color: #ffffff">Password</label>
                                </div>
                                <div class="styled-input agile-styled-input-top">
                                    <input type="text" id="text_codigo" name="txt_codigo" required="" maxlength="7">
                                    <label style="color: #ffffff">Codigo de validacion</label>
                                </div>
                                <div class="form-group container" style="margin: 10px 90px">
                                    <input style="margin: 0px 70px" type ="submit" id ="Registrar" name="Registrar" value="Registrar" class="btn btn-outline-light" onclick="return ValidarUsuario();">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
                        
    </body>
</html>
