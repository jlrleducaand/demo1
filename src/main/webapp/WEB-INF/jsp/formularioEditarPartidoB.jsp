<%@ page import="org.iesvdm.jsp_servlet_jdbc.model.Partido" %>
<%@ page import="org.iesvdm.jsp_servlet_jdbc.dao.PartidoDAOImpl" %>
<%@ page import="java.util.Optional" %>
<%@ page import="org.iesvdm.jsp_servlet_jdbc.model.Partido" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="estilos.css" />
</head>
<body class="bg-light">
<div class="container bg-white">
    <div class="row border-bottom">
        <div class="col-12 h2">Introduzca los cambios del partido</div>
    </div>
</div>
<%
    PartidoDAOImpl PDAOImpl = new PartidoDAOImpl();
    Optional<Partido> POpt1 = PDAOImpl.find(Integer.parseInt(request.getParameter("codigo")));
%>

<div class="container bg-light">
    <form method="post" action="EditarPartidosServlet">
        <div class="row body mt-2 ">
            <div class="col-md-6 align-self-center">Codigo</div>
            <div class="col-md-6 align-self-center"><input type="number" readonly name="codigo"  value="<%=request.getParameter("codigo")%>"/></div>
        </div>
        <div class="row body mt-2 ">
            <div class="col-md-6 align-self-center">Fecha</div>
            <div class="col-md-6 align-self-center"><input type="date" name="fecha" value="<%=POpt1.get().getFecha()%>" /></div>
        </div>
        <div class="row body mt-2">
            <div class="col-md-6 align-self-center">Equipo1</div>
            <div class="col-md-6 align-self-center"><input type="text" name="equipo1" value="<%=POpt1.get().getEquipo1()%>" ></div>
        </div>
        <div class="row body mt-2">
            <div class="col-md-6 align-self-center">Ptos-Eqp1</div>
            <div class="col-md-6 align-self-center"><input type="number" name="puntos_equipo1" value="<%=POpt1.get().getPuntosEquipo1()%>" /></div>
        </div>
        <div class="row body mt-2">
            <div class="col-md-6 align-self-center">Equipo2</div>
            <div class="col-md-6 align-self-center"><input type="text" name="equipo2" value="<%=POpt1.get().getEquipo2()%>"   /></div>
        </div>
        <div class="row body mt-2">
            <div class="col-md-6 align-self-center">Ptos-Eqp2</div>
            <div class="col-md-6 align-self-center"><input type="number" name="puntos_equipo2" value="<%=POpt1.get().getPuntosEquipo2()%>" /></div>
        </div>
        <div class="row body mt-2">
            <div class="col-md-6 align-self-center">Tipo</div>
            <div class="col-md-6 align-self-center"><select class="col-md-4 "  type="text" name="tipo_partido" value="<%=POpt1.get().getTipoPartido()%>"">
                <option  value= "Oficial">Oficial</option>
                <option  value="Amistoso">Amistoso</option>
            </select></div>
        </div>
        <div class="row mt-2">
            <div class="col-md-6">
                &nbsp;
            </div>
            <div class="col-md-6 align-self-center">
                <input class="btn btn-primary" type="submit" value="Aceptar">
                <a class="btn btn-primary"  href="ListarPartidosServlet">Volver</a>
            </div>

        </div>
    </form>
    <%
        //                                                 v---- RECOGER MENSAJE DE ERROR DEL ÁMBITO request
        String error = (String) request.getAttribute("error");
//            v---- SI ESTÁ PRESENTE INFORMAR DEL ERROR
        if (error != null) {
    %>
    <div class="row mt-2">
        <div class="col-6">
            <div class="alert alert-danger alert-dismissible fade show">
                <strong>Error!</strong> <%=error%>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>
<script src="js/bootstrap.bundle.js" ></script>
</body>
</html>
