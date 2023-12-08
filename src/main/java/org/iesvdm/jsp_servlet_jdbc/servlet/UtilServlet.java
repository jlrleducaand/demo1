package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.http.HttpServletRequest;
import org.iesvdm.jsp_servlet_jdbc.model.Partido;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;

public class UtilServlet {

    public static Optional<Partido> validaGrabar(HttpServletRequest request) {

        //CÓDIGO DE VALIDACIÓN
        //int socioID = -1;

        Date fecha = null;
        String equipo1 = null;
        int puntos_equipo1 = -1;
        String equipo2 = null;
        int puntos_equipo2 = -1;
        String tipo_partido = null;
        try {

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                fecha = new Date(dateFormat.parse(request.getParameter("fecha")).getTime());

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("La cadena no se puede convertir en fecha");
            }

            Objects.requireNonNull(request.getParameter("equipo1"));
            if (request.getParameter("equipo1").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            equipo1 = request.getParameter("equipo1");
            puntos_equipo1 = Integer.parseInt(request.getParameter("puntos_equipo1"));

            Objects.requireNonNull(request.getParameter("equipo2"));
            if (request.getParameter("equipo2").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            equipo2 = request.getParameter("equipo2");
            puntos_equipo2 = Integer.parseInt(request.getParameter("puntos_equipo2"));

            // no puede ser nulo porque es un select
            if (request.getParameter("tipo_partido").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            tipo_partido = request.getParameter("tipo_partido");


            return Optional.of(new Partido(-1, fecha, equipo1, puntos_equipo1, equipo2, puntos_equipo2,  tipo_partido));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();

    }
    public static Optional<Integer> validaBorrar(HttpServletRequest request) {

        //CÓDIGO DE VALIDACIÓN
        boolean valida = true;
        int partidoID = -1;

        try {
            partidoID = Integer.parseInt(request.getParameter("codigo")); // viene del formulario

            return Optional.of(partidoID);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();

    }
    public static Optional<Integer> validaEditar(HttpServletRequest request) {

        //CÓDIGO DE VALIDACIÓN
        boolean valida = true;
        int partidoID = -1;

        try {
            partidoID = Integer.parseInt(request.getParameter("codigo")); // viene del formulario

            return Optional.of(partidoID);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();

    }

    public static Optional<Partido> validaEditarPost(HttpServletRequest request) {

        //CÓDIGO DE VALIDACIÓN
        boolean valida = true;
        int socioID = Integer.parseInt(request.getParameter("codigo"));

        Date fecha = null;
        String equipo1 = null;
        int puntos_equipo1 = -1;
        String equipo2 = null;
        int puntos_equipo2 = -1;
        String tipo_partido = null;
        try {


            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                fecha = new Date(dateFormat.parse(request.getParameter("fecha")).getTime());

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("La cadena no se puede convertir en fecha");
            }

            Objects.requireNonNull(request.getParameter("equipo1"));
            if (request.getParameter("equipo1").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            equipo1 = request.getParameter("equipo1");
            puntos_equipo1 = Integer.parseInt(request.getParameter("puntos_equipo1"));

            Objects.requireNonNull(request.getParameter("equipo2"));
            if (request.getParameter("equipo2").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            equipo2 = request.getParameter("equipo2");
            puntos_equipo2 = Integer.parseInt(request.getParameter("puntos_equipo2"));

            // no puede ser nulo porque es un select
            if (request.getParameter("tipo_partido").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            tipo_partido = request.getParameter("tipo_partido");


            return Optional.of(new Partido(socioID, fecha, equipo1, puntos_equipo1, equipo2, puntos_equipo2,  tipo_partido));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();

    }
}
