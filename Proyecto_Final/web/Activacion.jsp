<%-- 
    Document   : Activacion
    Created on : 22/10/2020, 07:01:49 PM
    Author     : rodri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
       
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="JS/AllInOne.js"></script>
<link rel="stylesheet" href="CSS/EstilosIndex.css" type="text/css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="fuentes/iconic/css/material-design-iconic-font.min.css">

  
<title>Activacion</title>
    </head>
    <body>

        <div class="container-login">
            <div class="wrap-login">
               
                    <form class="login-form validate-form" id="formLogin" action="sr_login" method="POST">
                         <span class="login-form-title">Activacion De Cuenta</span>
                     
                         <div class="wrap-input100">
                            <label class="input110">Codigo de Verificacion</label>
                            <input type="text" id="text_codac" name="txt_codac" class="input100" placeholder="Escriba su codigo" required>
                            <span class="focus-efecto"></span>
                         
                        </div>
                       
                        <div class="container-login-form-btn">
                            <div class="wrap-login-form-btn">
                                <div class="login-form-bgbtn"></div>
                       
                        <input type="submit" value="Activar" class="btn login-form-btn" name="Activar" id="Activar" >
                        
                        <!--<input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn-b">-->
                    </div>
                </div>
                    </form>
       
            </div>
        </div>
   
 
    </body>
</html>