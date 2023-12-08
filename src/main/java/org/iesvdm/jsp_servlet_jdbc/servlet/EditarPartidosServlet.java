package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.PartidoDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.PartidoDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Partido;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//PLANTILLA DE CÓDIGO PARA SERVLETs EN INTELLIJ
//https://www.jetbrains.com/help/idea/creating-and-configuring-web-application-elements.html

//1A APROX. PATRÓN MVC -> M(dao, model y bbdd), V(jsp) & C(servlet)

//                      v--NOMBRE DEL SERVLET           v--RUTAS QUE ATIENDE, PUEDE SER UN ARRAY {"/GrabarPartidosServlet", "/grabar-socio"}
@WebServlet(name = "EditarPartidosServlet", value = "/EditarPartidosServlet")
public class EditarPartidosServlet extends HttpServlet  {


    private PartidoDAO partidoDAO = new PartidoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarPartidoB.jsp");

        dispatcher.forward(request, response); // DISPARA LA REDIRECCIÓN INTERNA EN EL SERVIDOR A UNA JSP O VISTA.

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        Optional<Partido> optionalPartido = UtilServlet.validaEditarPost(request);
        //SI OPTIONAL CON SOCIO PRESENTE <--> VALIDA OK
        if (optionalPartido.isPresent() ) {

            //ACCEDO AL VALOR DE OPTIONAL DE SOCIO
            Partido partido = optionalPartido.get();

            //PERSISTO EL SOCIO ACTUALIZADO EN BBDD
            this.partidoDAO.update(partido);


            //CARGO TODO EL LISTADO DE SOCIOS DE BBDD CON EL NUEVO
            List<Partido> listado = this.partidoDAO.getAll();

            request.setAttribute("listado", listado);

            //PARA LANZAR UN MODAL Y UN EFECTO SCROLL EN LA VISTA JSP
            request.setAttribute("newSocioID", partido.getId());

            //Redireccioname a:
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoPartidosB.jsp");

        } else {

            request.setAttribute("error", "Error de validación!");

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarPartidoB.jsp");
        }
        dispatcher.forward(request,response);
    }

}