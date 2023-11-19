/**
 * Clase que representa un Equipo en un Torneo
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

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Equipo {

    private String nombre;
    private Persona representante;
    private Collection<Jugador> jugadores;
    private Estadistica estadistica;

    public Equipo(String nombre, Persona representante, Estadistica estadistica) {
        ASSERTION.assertion(nombre != null && !nombre.isBlank(), "El nombre es requerido");
        ASSERTION.assertion(representante != null, "El representante es requerido");

        this.nombre = nombre;
        this.representante = representante;
        this.estadistica = estadistica;
        this.jugadores = new LinkedList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public Persona getRepresentante() {
        return representante;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }

    public Collection<Jugador> getJugadores() {
        return Collections.unmodifiableCollection(jugadores);
    }

    /**
     * Registra un jugador en el equipo.
     * 
     * @param jugador Jugador a registrar.
     */
    public void registrarJugador(Jugador jugador) {
        validarJugadorExiste(jugador);
        jugadores.add(jugador);
    }

    /**
     * Busca un jugador en la lista de jugadores del equipo.
     * 
     * @param jugador Jugador a buscar.
     * @return Un Optional que contiene el jugador si es encontrado, o un Optional
     *         vacío si no se encuentra.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador) {
        Predicate<Jugador> nombreIgual = j -> j.getNombre().equals(jugador.getNombre());
        Predicate<Jugador> apellidoIgual = j -> j.getApellido().equals(jugador.getApellido());
        return jugadores.stream().filter(nombreIgual.and(apellidoIgual)).findAny();
    }

    /**
     * Valida que el jugador no esté ya registrado en el equipo.
     * 
     * @param jugador Jugador a validar.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion(!existeJugador, "El jugador ya esta registrado");
    }

    /**
     * Devuelve una representación en cadena de la estadística.
     * 
     * @return Cadena que representa la estadística.
     *
     * @Override
     *           public String toString() {
     *           return getEstadistica().toString();
     *           }
     */

    public String toString() {
        return "\nEl puntaje del equipo " + nombre + " es: " + getEstadistica().toString();

    }

}
