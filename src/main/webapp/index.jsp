<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
Redirigiendo. Por favor Espere
<%
    response.sendRedirect("ListarPartidosServlet");
%>
</body>
</html>