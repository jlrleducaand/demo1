package org.iesvdm.jsp_servlet_jdbc.model;

import java.util.Objects;
import java.util.Date;

/**
 * POJO o BEAN QUE REPRESENTA LA TABLA socio
 */
public class Juego {

    private int id;
    private Date fecha;
    private String equipo1;
    private Integer puntosEquipo1;
    private String equipo2;
    private Integer puntosEquipo2;
    private String tipoPartido;

    public Juego() {
    }

    // Constructor con todos los campos excepto el id, ya que es autoincremental
    public Juego(Date fecha, String equipo1, Integer puntosEquipo1, String equipo2,
                   Integer puntosEquipo2, String tipoPartido) {
        this.fecha = fecha;
        this.equipo1 = equipo1;
        this.puntosEquipo1 = puntosEquipo1;
        this.equipo2 = equipo2;
        this.puntosEquipo2 = puntosEquipo2;
        this.tipoPartido = tipoPartido;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public Integer getPuntosEquipo1() {
        return puntosEquipo1;
    }

    public void setPuntosEquipo1(Integer puntosEquipo1) {
        this.puntosEquipo1 = puntosEquipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public Integer getPuntosEquipo2() {
        return puntosEquipo2;
    }

    public void setPuntosEquipo2(Integer puntosEquipo2) {
        this.puntosEquipo2 = puntosEquipo2;
    }

    public String getTipoPartido() {
        return tipoPartido;
    }

    public void setTipoPartido(String tipoPartido) {
        this.tipoPartido = tipoPartido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Juego)) return false;
        Juego juego = (Juego) o;
        return getId() == juego.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", equipo1='" + equipo1 + '\'' +
                ", puntosEquipo1=" + puntosEquipo1 +
                ", equipo2='" + equipo2 + '\'' +
                ", puntosEquipo2=" + puntosEquipo2 +
                ", tipoPartido='" + tipoPartido + '\'' +
                '}';
    }
}
