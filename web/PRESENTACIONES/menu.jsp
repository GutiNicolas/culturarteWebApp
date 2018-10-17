<%-- 
    Document   : menu
    Created on : Oct 12, 2018, 12:15:46 PM
    Author     : nicolasgutierrez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">      
        <link rel="stylesheet" type="text/css" href="ESTILOS/bootstrap.css" />
        <script src="SCRIPTS/jquery-3.3.1.min.js" ></script>
        <script src="SCRIPTS/bootstrap.js"></script>
        
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark " style="background-color:#1b1c1b;">
            <a class="navbar-brand" href="/culturarteWebApp/Propuesta" style="color: #f2d5a9;">              
                Bienvenido <%= session.getAttribute("nickusuario") %>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">                                      
                    <li class="nav-item">
                        <a class="nav-link" style="color: #f2d5a9;" href="/culturarteWebApp/Propuesta">Consultar propuestas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="color: #f2d5a9;" href="/culturarteWebApp/Colaborar">Registrar colaboración</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="color: #f2d5a9;" href="/culturarteWebApp/Pagos">Pagar colaboración</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="color: #f2d5a9;" href="/culturarteWebApp/Logout">Cerrar sesión</a>
                    </li>
                </ul>
               
            </div>
        </nav>
    </body>
</html>
