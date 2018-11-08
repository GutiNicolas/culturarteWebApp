<%-- 
    Document   : login
    Created on : Oct 12, 2018, 12:16:03 PM
    Author     : nicolasgutierrez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Log In | CultuRarte App</title>
        <% 
            String error;
            if(request.getParameter("error") != null){
               error= request.getParameter("error"); 
            }
            else
                error="";
        %>       
        <link rel="stylesheet" type="text/css" href="ESTILOS/login.css" />
        <link rel="stylesheet" type="text/css" href="ESTILOS/bootstrap.css" />
        <script src="SCRIPTS/jquery-3.3.1.min.js" ></script>
    </head>
        <%
        if(error.equals("nu"))
            out.print("<body onload=\"alertar('USUARIO NO ENCONTRADO')\" >");
        else if(error.equals("pm"))
            out.print("<body onload=\"alertar('CONTRASENIA ERRONEA')\" >");
        else if(session.getAttribute("nickusuario") != null){
            out.print("<body onload=\"alertar('BIEVENIDO "+ session.getAttribute("nickusuario")+"')\" >");
            response.sendRedirect("Propuesta");
        }
        else
            out.print("<body>");
        %>
    <div id="home">
           <div class="container centra3">
               <form class="formx" id="login_form" action="/culturarteWebApp/Login" method="post" onsubmit="return verificar()">
                   
                   <h4 class="pintar">Iniciar sesión</h4>
                    <input type="text" id="nick" class="largito" name="nick" placeholder="Nickname o email"/>                 
                    <span id="error_nick" class="error"><br>Debes ingresar un nick</span>                    
                    
                    
                    <input type="password" class="largito" id="pass" name="pass" placeholder="Password"/>
                    <span id="error_pass" class="error"><br>Debes ingresar la clave</span>                  
                    <br/>
                    <input type="checkbox" name="rememberMe" id="rememberMe" value="rememberMe"/><span class="pintar">  Recordarme</span>
                    <br/>
                    <input class="btn btn-primary largito" type="submit" value="Login" id="bttacept"/>  
                    <br>
                    <div class="controlnick pintar largito" id="controlnick"></div>                   
               </form>
           </div>             
    </div>
    
        <script type="text/javascript">
        function verificar()
{
        var correct = true;

        var name = $('#nick').val();
        if(name == ''){ 
                $('#error_nick').show();
                correct = false;
        } else 
                $('#error_name').hide();

        var pass = $('#pass').val();
        if(pass == '') {
                $('#error_pass').show();
                correct = false;
        } else
                $('#error_pass').hide();
        var cont=$('#controlnick').text();
        if(cont != ''){
            document.getElementById("nick").focus();
            correct = false;
        }

              // alert("contrasenia no valida");
        return correct;
}
    </script>
    
    <script type="text/javascript">
        //ajax
         $(document).ready(function(){
                $('#nick').keyup(function(){
                    var nick= $('#nick').val();                                    
                    $.ajax({
                        type: 'POST',
                        url: 'ControlLogin',
                        data: {nick: nick},
                        success: function(result3){    
                            document.getElementById("controlnick").value=null;
                            $('#controlnick').html(result3);
                        }                       
                    });  
                });         
            });
    </script>
        
        <script src="SCRIPTS/bootstrap.js">
    </body>
</html>
