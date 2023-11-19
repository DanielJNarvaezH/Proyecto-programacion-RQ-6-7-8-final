/**
 * Clase que representa las estadísticas de un equipo en un Torneo
 * @author Área de programación UQ - Daniel Narvaez, Diego Flores, Esteban Maya
 * @since 2023-11
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

public class Estadistica {
    private int ganado;
    private int perdido;
    private int empatado;
    private int puntaje;

    public Estadistica() {

        ganado = 0;
        perdido = 0;
        empatado = 0;
        puntaje = 0;

    }

    public int getGanado() {
        return ganado;
    }

    public int getPerdido() {
        return perdido;
    }

    public int getEmpatado() {
        return empatado;
    }


    public int getPuntaje() {
        return puntaje;
    }


    /**
     * Actualiza las estadísticas del equipo después de un partido.
     * 
     * @param isGanado   Indica si el equipo ganó el partido.
     * @param isEmpatado Indica si el partido terminó en empate.
     * @param isPerdido  Indica si el equipo perdió el partido.
     */
    public void actualizarEstadisticas(boolean isGanado, boolean isEmpatado, boolean isPerdido) {
        if (isGanado) {
            ganado++;
        } else if (isEmpatado) {
            empatado++;
        } else if (isPerdido) {
            perdido++;
        }

        puntaje = ganado * 3 + empatado * 2 + perdido;
    }

    /**
     * Devuelve una representación en cadena de la estadística.
     * 
     * @return Cadena que representa la estadística.
     */
    @Override
    public String toString() {
        return puntaje + "." + "\nSus victorias fueron: " + ganado + "."
                + "\nSus empates fueron: " + empatado + "." + "\nSus derrotas fueron: " + perdido + ".\n";
    }

}
