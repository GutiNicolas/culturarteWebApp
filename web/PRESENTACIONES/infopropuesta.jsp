<%-- 
    Document   : infopropuesta
    Created on : Oct 12, 2018, 12:16:38 PM
    Author     : nicolasgutierrez
--%>

<%@page import="java.util.Collection"%>
<%@page import="Logica.dtPropuesta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <%@include file="/PRESENTACIONES/menu.jsp"%>
        <link rel="stylesheet" type="text/css" href="ESTILOS/infopropuesta.css" />
        <title>cultuRarte App</title>
    </head>
    <body style="background-color: #202020">
        <div id="home" class="home">
            <div class="container cont3" style="position:absolute; width:100%; height:100%">
                <%
                if (request.getAttribute("propuesta") != null) {
                    dtPropuesta prop = (dtPropuesta) request.getAttribute("propuesta");
                %>
                <br>
                    <img src="IMAGENES/defaultprop.jpg" class="imgprop" alt="" />
                    <h4 class="landing-text"><%=prop.getTitulo()%></h4>
                    <%
                    if((prop.getEstado().equals("Publicada") || prop.getEstado().equals("En financiacion")) && prop.colabora((String) session.getAttribute("nickusuario"))==false){
                    %>
                    <div class="porqueria">
                        <a class="btn btn-outline-dark btn-sm landing-text" href="/culturarteWebApp/Colaborar?titulo=<%=prop.getTitulo()%>">
                            Colaborar
                        </a>
                    </div>
                            <br>
                    <%}%>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-3 col-sm-4 col-xs-12" style="min-width: 50px">
                            <h6>Fecha: <%=prop.getFechaRealizacion().getFecha()%></h6><br>
                            <h6>Lugar: <%=prop.getLugar()%></h6><br>
                            <h6>Precio de entradas: <%=prop.getPrecioentrada()%></h6><br>
                            <h6>Monto a recaudar: <%=prop.getMontorequerido()%></h6><br>
                            <h6>Tipo de retorno: <%=prop.getRetorno()%></h6><br>
                            <h6>Estado: <%=prop.getEstado()%></h6><br>
                            <h6>Colaboradores: <%if(request.getAttribute("colaboradores") != null){
                                Collection<String> cols=(Collection<String>) request.getAttribute("colaboradores");
                                for(String c: cols){%>
                                    <%=c%>
                                <%}
                            }%></h6><br>
                            <h6>Monto recaudado: <%=prop.getMontoTotal()%></h6><br>                           
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </body>
</html>