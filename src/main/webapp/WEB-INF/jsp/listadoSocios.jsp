<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado Socios</title>
  </head>
  <body>
    <h1>Listado de Socios</h1>
	<%
		//CARGA DEL DRIVER Y PREPARACIÓN DE LA CONEXIÓN CON LA BBDD
		//						v---------UTILIZAMOS LA VERSIÓN MODERNA DE LLAMADA AL DRIVER, no deprecado
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/juego","root", "1234");

		//UTILIZAR STATEMENT SÓLO EN QUERIES NO PARAMETRIZADAS.
		Statement s = conexion.createStatement();
 		ResultSet listado = s.executeQuery ("SELECT * FROM juego");

		while (listado.next()) {
			out.println(listado.getString("id") + " " + listado.getString ("fecha") + "<br>");
    }
		listado.close();
		s.close();
		conexion.close();
	%>
  </body>
</html>