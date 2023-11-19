/**
 * Clase para probar el funcionamiento de los Enfrentamientos
 * @author Área de programación UQ - Daniel Narvaez, Diego Flores, Esteban Maya
 * @since 2023-11
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class EnfrentamientoTest {
        private static final Logger LOG = Logger.getLogger(EquipoTest.class.getName());

        /**
         * Verifica que el estado de un enfrentamiento se establezca correctamente como
         * FINALIZADO y que las estadísticas de los equipos se actualicen
         * después de llevar a cabo un enfrentamiento con puntajes específicos.
         */
        @Test
        public void llevarACaboEnfrentamiento() {
                LOG.info("Inicio de prueba llevarACaboEnfrentamiento...");

                // Crear representante, lugar y equipos
                var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
                var lugar = new Lugar("granada", "Avenida Alamos");
                var visitante1 = new Equipo("Real Madrid", representante, new Estadistica());
                var local1 = new Equipo("Barcelona", representante, new Estadistica());

                // Crear enfrentamientos con fechas y horas actuales
                Enfrentamiento enfrentamiento1 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante1,
                                local1);
                Enfrentamiento enfrentamiento2 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante1,
                                local1);
                Enfrentamiento enfrentamiento3 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante1,
                                local1);

                // Establecer puntajes específicos para los enfrentamientos
                enfrentamiento1.setPuntosVisitanteLocalEnfrentamiento(13, 8);
                enfrentamiento2.setPuntosVisitanteLocalEnfrentamiento(1, 2);
                enfrentamiento3.setPuntosVisitanteLocalEnfrentamiento(1, 2);

                // Llevar a cabo los enfrentamientos
                enfrentamiento1.llevarACaboEnfrentamiento();
                enfrentamiento2.llevarACaboEnfrentamiento();
                enfrentamiento3.llevarACaboEnfrentamiento();

                // Verificar que el estado de los enfrentamientos sea FINALIZADO
                assertEquals(EstadoEnfrentamiento.FINALIZADO, enfrentamiento1.getEstadoEnfrentamiento());
                assertEquals(EstadoEnfrentamiento.FINALIZADO, enfrentamiento2.getEstadoEnfrentamiento());
                assertEquals(EstadoEnfrentamiento.FINALIZADO, enfrentamiento3.getEstadoEnfrentamiento());

                // Verificar que las estadísticas de los equipos se actualicen correctamente
                assertEquals(1, visitante1.getEstadistica().getGanado());
                assertEquals(2, visitante1.getEstadistica().getPerdido());
                assertEquals(0, visitante1.getEstadistica().getEmpatado());
                assertEquals(2, local1.getEstadistica().getGanado());
                assertEquals(1, local1.getEstadistica().getPerdido());
                assertEquals(0, local1.getEstadistica().getEmpatado());

                LOG.info("Fin de prueba llevarACaboEnfrentamiento...");
        }

        /**
         * Verifica que el estado de un enfrentamiento se establezca correctamente como
         * PENDIENTE después de llevarse a cabo.
         */
        @Test
        public void verificarEstadoEnfrentamientoPendiente() {
                LOG.info("Inicio de prueba verificarEstadoEnfrentamientoPendiente");

                // Crear lugar, representante y equipos
                Lugar lugar = new Lugar("uq", "direccion uq");
                Persona representante1 = new Persona("quiso", "bonie", "quiso123@example.com", "3054578458");
                Equipo visitante1 = new Equipo("Los cacos", representante1, new Estadistica());
                Equipo local1 = new Equipo("Los cacas", representante1, new Estadistica());

                // Crear enfrentamiento con fecha actual y hora actual más 1 hora
                Enfrentamiento enfrentamiento = new Enfrentamiento(LocalDate.now(), LocalTime.now().plusHours(1), lugar,
                                visitante1, local1);

                // Llevar a cabo el enfrentamiento
                enfrentamiento.llevarACaboEnfrentamiento();

                // Verificar que el estado del enfrentamiento sea PENDIENTE
                assertEquals(EstadoEnfrentamiento.PENDIENTE, enfrentamiento.getEstadoEnfrentamiento());

                LOG.info("Fin de prueba verificarEstadoEnfrentamientoPendiente");
        }

        /**
         * Verifica que el estado de un enfrentamiento se establezca correctamente como
         * FINALIZADO después de llevarse a cabo.
         */
        @Test
        public void verificarEstadoEnfrentamientoFinalizado() {
                LOG.info("Inicio de prueba verificarEstadoEnfrentamientoFinalizado");

                // Crear lugar, representante y equipos
                Lugar lugar = new Lugar("uq", "direccion uq");
                Persona representante1 = new Persona("quiso", "bonie", "quiso123@example.com", "3054578458");
                Equipo equipo1 = new Equipo("UQ", representante1, new Estadistica());
                Equipo equipo2 = new Equipo("UTP", representante1, new Estadistica());

                // Crear enfrentamiento con fecha actual y hora actual
                Enfrentamiento enfrentamiento = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, equipo1,
                                equipo2);

                // Llevar a cabo el enfrentamiento
                enfrentamiento.llevarACaboEnfrentamiento();

                // Verificar que el estado del enfrentamiento sea FINALIZADO
                assertEquals(EstadoEnfrentamiento.FINALIZADO, enfrentamiento.getEstadoEnfrentamiento());

                LOG.info("Fin de prueba verificarEstadoEnfrentamientoFinalizado");
        }

        /**
         * Verifica que el estado de un enfrentamiento se establezca correctamente como
         * APLAZADO después de aplazarlo.
         */
        @Test
        public void verificarEstadoEnfrentamientoAplazado() {
                LOG.info("Inicio de prueba verificarEstadoEnfrentamientoAplazado");

                // Crear lugar, representante y equipos
                Lugar lugar = new Lugar("uq", "direccion uq");
                Persona representante1 = new Persona("quiso", "bonie", "quiso123@example.com", "3054578458");
                Equipo equipo1 = new Equipo("teresita montes", representante1, new Estadistica());
                Equipo equipo2 = new Equipo("rufino sur", representante1, new Estadistica());

                // Crear enfrentamiento con fecha futura y hora actual
                Enfrentamiento enfrentamiento = new Enfrentamiento(LocalDate.of(2023, 10, 8), LocalTime.now(), lugar,
                                equipo1, equipo2);

                // Aplazar el enfrentamiento a una fecha y hora futuras
                enfrentamiento.aplazarEnfrentamiento(LocalDate.of(2023, 11, 1), LocalTime.now().plusMinutes(5));

                // Verificar que el estado del enfrentamiento sea APLAZADO
                assertEquals(EstadoEnfrentamiento.APLAZADO, enfrentamiento.getEstadoEnfrentamiento());

                LOG.info("Fin de prueba verificarEstadoEnfrentamientoAplazado");
        }

}
