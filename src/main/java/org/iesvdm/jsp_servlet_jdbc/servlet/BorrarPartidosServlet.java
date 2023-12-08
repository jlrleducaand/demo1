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
@WebServlet(name = "BorrarPartidosServlet", value = "/BorrarPartidosServlet")
public class BorrarPartidosServlet extends HttpServlet {

    //EL SERVLET TIENE INSTANCIADO EL DAO PARA ACCESO A BBDD A LA TABLA SOCIO
    //                                  |
    //                                  V
    private PartidoDAO partidoDAO = new PartidoDAOImpl();

    //HTML5 SÓLO SOPORTA GET Y POST
    //FRENTE A API REST UTLIZANDO CÓDIGO DE CLIENTE JS HTTP: GET, POST, PUT, DELETE, PATCH


    //MÉTODO PARA RUTAS POST /BorrarPartidosServlet
    //PARA LA RUTA POST /BorrarPartidosServlet HAY 2 OPCIONES DE REDIRECCIÓN INTERNA A JSP
    //1a CASO DE QUE SE VALIDE CORRECTAMENTE --> listadoPartidosB.jsp   con el elemento eliminado del listado
    //2o CASO DE QUE NO SE VALIDE CORRECTAMENTE --> listadoPartidosB.jsp CON INFORME DE ERROR  Y listado sin eliminar el elemento
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        //CÓDIGO DE VALIDACIÓN ENCAPSULADO EN UN MÉTODO DE UTILERÍA
        // SI OK ==> OPTIONAL CON SOCIO                 |
        // SI FAIL ==> EMPTY OPTIONAL                   |
        //                                              V
        Optional<Integer> optionalPartido = UtilServlet.validaBorrar(request);

        //SI OPTIONAL CON SOCIO PRESENTE <--> VALIDA OK
        if (optionalPartido.isPresent()) {

            //ACCEDO AL VALOR DE OPTIONAL DE INTEGER
            Integer idPartido = optionalPartido.get();

            //PERSISTO EL SOCIO NUEVO EN BBDD DELETE
            this.partidoDAO.delete(idPartido);

            //CARGO TODO EL LISTADO DE SOCIOS DE BBDD CON EL NUEVO
            List<Partido> listado = this.partidoDAO.getAll();

            //PREPARO ATRIBUTO EN EL ÁMBITO DE REQUEST PARA PASAR A JSP EL LISTADO
            //A RENDERIZAR. UTILIZO EL ÁMBITO DEL REQUEST DADO QUE EN EL FORWARD A
            //LA JSP SIGUE "VIVO" Y NO NECESITO ACCEDER AL ÁMBITO DE SESIÓN QUE REQUERIRÍA
            //DE UN CONTROL DE BORRADO DEL ATRIBUTO DESPUÉS DE SU USO.
            //EN request HAY UN Map<String, Object> DONDE PREPARO EL ATRIBUTO PARA LA VISTA JSP
            //                                  |
            //                                  V
            request.setAttribute("listado", listado);

            //POR ÚLTIMO, REDIRECCIÓN INTERNA PARA LA URL /BorrarPartidosServlet A listadoPArtidosB.jsp
            //                                                                      |
            //                                                                      V
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoPartidosB.jsp");

        } else {

            //El OPTIONAL ESTÁ VACÍO (EMPTY)
            //PREPARO MENSAJE DE ERROR EN EL ÁMBITO DEL REQUEST PARA LA VISTA JSP
            //                                |
            //                                V
            request.setAttribute("error", "Error de validación!");

            //POR ÚLTIMO, REDIRECCIÓN INTERNA PARA LA URL /BorrarPartidosServlet A listadoPartidosB.jsp
            //                                                                      |
            //                                                                      V
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoPartidosB.jsp");
        }
        //TENEMOS SIEMPRE QUE HACER FORWARD CON LOS OBJETOS request Y response
        dispatcher.forward(request,response);

    }

}