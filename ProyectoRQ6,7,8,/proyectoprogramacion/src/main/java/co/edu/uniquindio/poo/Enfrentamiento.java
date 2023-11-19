/**
 * Clase que representa un Enfrentamiento entre dos equipos en un Torneo
 * @author Área de programación UQ - Daniel Narvaez, Diego Flores, Esteban Maya
 * @since 2023-11
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;
import java.time.format.DateTimeFormatter;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

import java.time.LocalDate;
import java.time.LocalTime;

public class Enfrentamiento {

    private LocalDate fecha;
    private LocalTime hora;
    private String resultadoEnfrentamiento;
    private Lugar lugar;
    private EstadoEnfrentamiento estadoEnfrentamiento;
    private final Collection<Juez> jueces;
    private final Equipo visitante;
    private final Equipo local;
    private int puntosVisitanteEnfrentamiento;
    private int puntosLocalEnfrentamiento;

    public Enfrentamiento(LocalDate fecha, LocalTime hora, Lugar lugar, Equipo visitante,
            Equipo local) {
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.visitante = visitante;
        this.local = local;
        this.jueces = new LinkedList<>();

    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public Equipo getLocal() {
        return local;
    }

    public int getPuntosVisitanteEnfrentamiento() {
        return puntosVisitanteEnfrentamiento;
    }

    public int getPuntosLocalEnfrentamiento() {
        return puntosLocalEnfrentamiento;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public EstadoEnfrentamiento getEstadoEnfrentamiento() {
        return estadoEnfrentamiento;
    }

    public String getResultadoEnfrentamiento() {
        return resultadoEnfrentamiento;
    }

    public Collection<Juez> getJueces() {
        return Collections.unmodifiableCollection(jueces);
    }

    public void setFechaHora(LocalDate fecha, LocalTime hora) {
        LocalDate fechaActual = LocalDate.now();
        ASSERTION.assertion(fecha.isAfter(fechaActual));
        this.fecha = fecha;
        this.hora = hora;

    }

    /**
     * Establece los puntos obtenidos por cada equipo en el enfrentamiento.
     * 
     * @param puntosEquipo1Enfrentamiento Puntos obtenidos por el primer equipo.
     * @param puntosEquipo2Enfrentamiento Puntos obtenidos por el segundo equipo.
     */
    public void setPuntosVisitanteLocalEnfrentamiento(int puntosVisitanteEnfrentamiento,
            int puntosLocalEnfrentamiento) {
        this.puntosVisitanteEnfrentamiento = puntosVisitanteEnfrentamiento;
        this.puntosLocalEnfrentamiento = puntosLocalEnfrentamiento;
    }

    /**
     * Permite registrar un juez en el torneo.
     * 
     * @param juez Juez a ser registrado.
     */
    public void registrarJuez(Juez juez) {
        validarJuezExiste(juez);
        jueces.add(juez);
    }

    /**
     * Valida si un juez ya está registrado en el torneo.
     * 
     * @param juez El juez que se desea validar.
     * @throws AssertionError Si el juez ya está registrado en el torneo.
     */
    private void validarJuezExiste(Juez juez) {
        boolean existeJuez = buscarJuezPorLicencia(juez.getLicenciaJuez()).isPresent();
        ASSERTION.assertion(!existeJuez, "El juez ya esta registrado");
    }

    /**
     * Busca un juez por su licencia.
     * 
     * @param licenciaJuez Licencia del juez a buscar.
     * @return Un Optional que puede contener al juez si se encuentra, o estar vacío
     *         si no se encuentra.
     */
    private Optional<Juez> buscarJuezPorLicencia(String licenciaJuez) {
        Predicate<Juez> condicion = juez -> juez.getLicenciaJuez().equals(licenciaJuez);
        return jueces.stream().filter(condicion).findAny();
    }

    /**
     * Aplaza el enfrentamiento a una nueva fecha y hora.
     * 
     * @param fecha Nueva fecha del enfrentamiento.
     * @param hora  Nueva hora del enfrentamiento.
     */
    public void aplazarEnfrentamiento(LocalDate fecha, LocalTime hora) {
        ASSERTION.assertion(fecha.isAfter(this.fecha));
        this.fecha = fecha;
        this.hora = hora;
        estadoEnfrentamiento = EstadoEnfrentamiento.APLAZADO;
    }

    /**
     * Lleva a cabo el enfrentamiento actualizando el estado de los equipos y su
     * estadística.
     */
    public void llevarACaboEnfrentamiento() {

        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");

        if (fechaActual.equals(fecha) && horaActual.format(formatter).equals(hora.format(formatter))) {

            estadoEnfrentamiento = EstadoEnfrentamiento.EN_JUEGO;
            resultadoEnfrentamiento = "El resultado es: ";
            estadoEnfrentamiento = EstadoEnfrentamiento.FINALIZADO;
            if (puntosVisitanteEnfrentamiento > puntosLocalEnfrentamiento) {

                visitante.getEstadistica().actualizarEstadisticas(true, false, false);
                local.getEstadistica().actualizarEstadisticas(false, false, true);
                resultadoEnfrentamiento += "El equipo 1 ganó con " + puntosVisitanteEnfrentamiento
                        + " puntos y el equipo 2 perdió con " + puntosLocalEnfrentamiento + " puntos";

            } else if (puntosVisitanteEnfrentamiento < puntosLocalEnfrentamiento) {

                visitante.getEstadistica().actualizarEstadisticas(false, false, true);
                local.getEstadistica().actualizarEstadisticas(true, false, false);
                resultadoEnfrentamiento += "El equipo 1 perdió con " + puntosVisitanteEnfrentamiento
                        + " puntos y el equipo 2 ganó con " + puntosLocalEnfrentamiento + " puntos";

            } else if (puntosVisitanteEnfrentamiento == puntosLocalEnfrentamiento) {

                visitante.getEstadistica().actualizarEstadisticas(false, true, false);
                local.getEstadistica().actualizarEstadisticas(false, true, false);
                resultadoEnfrentamiento += "El equipo 1 empató con " + puntosVisitanteEnfrentamiento
                        + " el equipo 2 con " + puntosLocalEnfrentamiento + " puntos";

            }
        } else {
            estadoEnfrentamiento = EstadoEnfrentamiento.PENDIENTE;
        }

    }

}
