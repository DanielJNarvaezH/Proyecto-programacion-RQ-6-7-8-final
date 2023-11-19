/**
 * Clase para probar el funcionamiento del Torneo
 * @author Área de programación UQ - Daniel Narvaez, Diego Flores, Esteban Maya
 * @since 2023-11
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class TorneoTest {
        /**
         * Instancia para el manejo de logs
         */
        private static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());

        /**
         * Verificar que la clase Torneo almacene y recupere los datos
         * 
         */
        @Test
        public void datosCompletos() {
                LOG.info("Inicio de prueba datos completos...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL
                Torneo torneo = new Torneo("Copa Piston", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 1),
                                LocalDate.of(2023, 9, 15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                TipoTorneoGenero.MIXTO);

                // Recuperación y verificación de datos
                assertEquals("Copa Piston", torneo.getNombre());
                assertEquals(LocalDate.of(2023, 10, 1), torneo.getFechaInicio());
                assertEquals(LocalDate.of(2023, 8, 1), torneo.getFechaInicioInscripciones());
                assertEquals(LocalDate.of(2023, 9, 15), torneo.getFechaCierreInscripciones());
                assertEquals((byte) 24, torneo.getNumeroParticipantes());
                assertEquals((byte) 0, torneo.getLimiteEdad());
                assertEquals(0, torneo.getValorInscripcion());
                assertEquals(TipoTorneo.LOCAL, torneo.getTipoTorneo());

                LOG.info("Fin de prueba datos completos...");
        }

        /**
         * Verificar que la clase Torneo valide que se ingrese los datos
         * 
         */
        @Test
        public void datosNulos() {
                LOG.info("Inicio de prueba datos nulos...");
                // Almacenar los datos de prueba null|null|null|null|24|0|0|null|LOCAL
                assertThrows(Throwable.class,
                                () -> new Torneo(null, null, null, null, (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                                TipoTorneoGenero.HOMBRES));

                LOG.info("Fin de prueba datos nulos...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de número de
         * participantes negativo
         * 
         */
        @Test
        public void participantesNegativos() {
                LOG.info("Inicio de prueba número de participantes negativo...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-08-01|2023-09-15|-24|0|0|LOCAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Piston", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01),
                                                LocalDate.of(2023, 10, 15), (byte) -24, (byte) 0, 0, TipoTorneo.LOCAL,
                                                TipoTorneoGenero.MUJERES));

                LOG.info("Fin de prueba  número de participantes negativo...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de limites de edades
         * negativo
         * 
         */
        @Test
        public void limiteEdadesNegativo() {
                LOG.info("Inicio de prueba limites de edades negativo...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-08-01|2023-09-15|24|-1|0|LOCAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Piston", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01),
                                                LocalDate.of(2023, 10, 15), (byte) 24, (byte) -1, 0, TipoTorneo.LOCAL,
                                                TipoTorneoGenero.HOMBRES));

                LOG.info("Fin de prueba  limites de edades negativo...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de valor de inscripción
         * negativa
         * 
         */
        @Test
        public void inscripcionNegativa() {
                LOG.info("Inicio de prueba inscripción negativa...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|-1|LOCAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Chamoy", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01),
                                                LocalDate.of(2023, 10, 15), (byte) 24, (byte) 0, -1, TipoTorneo.LOCAL,
                                                TipoTorneoGenero.MIXTO));

                LOG.info("Fin de prueba inscripción negativa...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de inscripciones
         * posteriores a la
         * fecha de inicio del torneo
         * 
         */
        @Test
        public void inscripcionTardia() {
                LOG.info("Inicio de prueba inscripción tardia...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Chamoy", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 11, 01),
                                                LocalDate.of(2023, 11, 15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                                TipoTorneoGenero.MUJERES));

                LOG.info("Fin de prueba inscripción tardia...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de inicio inscripciones
         * posteriores a
         * la fecha de cierre de inscripciones
         * 
         */
        @Test
        public void cierreInscripcionAnteriorInicio() {
                LOG.info("Inicio de prueba Cierre inscripción anterior al inicio...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Piston", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 15),
                                                LocalDate.of(2023, 8, 1), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                                TipoTorneoGenero.HOMBRES));

                LOG.info("Fin de prueba Cierre inscripción anterior al inicio...");

        }

        /**
         * Verifica que un equipo con un jugador de género correcto se pueda agregar al
         * torneo.
         */
        @Test
        public void verificarGeneroAgregarEquipoCorrecto() {
                LOG.info("Inicio de prueba verificarGeneroAgregarEquipoCorrecto...");

                // Crear un jugador con género MUJER
                Jugador jugador1 = new Jugador("Pabla", "Narvaez", "jcdxa@email", "634540912",
                                LocalDate.of(2023, 10, 31),
                                TipoGenero.MUJER);

                // Crear representante y estadísticas para el equipo
                Persona representante1 = new Persona("Pabla", "Narvaez", "jcdxa@email", "634540912");
                Estadistica estadistica1 = new Estadistica();

                // Crear un equipo llamado "rastastas" con el jugador y registrarlo en el torneo
                Equipo equipo1 = new Equipo("rastastas", representante1, estadistica1);
                equipo1.registrarJugador(jugador1);

                // Crear un torneo con género de torneo configurado como MUJERES
                Torneo torneo1 = new Torneo("Copa Piston", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                TipoTorneoGenero.MUJERES);

                // Registrar el equipo en el torneo
                torneo1.registrarEquipo(equipo1);

                // Verificar que el equipo se haya registrado correctamente
                assertEquals(1, torneo1.getEquipos().size());

                LOG.info("Fin de prueba verificarGeneroAgregarEquipoCorrecto...");
        }

        /**
         * Verifica que se lance una excepción al intentar agregar un equipo con un
         * jugador de género incorrecto al torneo.
         */
        @Test
        public void verificarErrorGeneroAgregarEquipo() {
                LOG.info("Inicio de prueba verificarErrorGeneroAgregarEquipo...");

                // Crear un jugador con género HOMBRE
                Jugador jugador1 = new Jugador("Nombre1", "Apellido1", "correo1@email.com", "1234567890",
                                LocalDate.of(2000, 1, 1), TipoGenero.HOMBRE);

                // Crear representante y estadísticas para el equipo
                Persona representante1 = new Persona("Representante", "ApellidoRepresentante",
                                "correoRepresentante@email.com",
                                "0987654321");

                // Crear un equipo llamado "Equipo1" con el jugador y registrar el equipo en el
                // torneo
                Equipo equipo1 = new Equipo("Equipo1", representante1, new Estadistica());
                equipo1.registrarJugador(jugador1);

                // Crear un torneo con género de torneo configurado como MUJERES
                Torneo torneo1 = new Torneo("Torneo1", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                                LocalDate.now().plusDays(15), (byte) 10, (byte) 25, 100, TipoTorneo.LOCAL,
                                TipoTorneoGenero.MUJERES);

                // Verificar que agregar el equipo lance una excepción
                assertThrows(Throwable.class, () -> torneo1.registrarEquipo(equipo1));

                LOG.info("Fin de prueba verificarErrorGeneroAgregarEquipo");
        }

        /**
         * Verifica que se registren correctamente los jueces con datos completos en el
         * torneo.
         */
        @Test
        public void verificarRegistrarJuezDatosCompletos() {
                LOG.info("Inicio de prueba verificarRegistrarJuezDatosCompletos...");

                // Crear tres jueces con datos completos
                Juez juez = new Juez("moi", "miapellidouwu", "lacasadelolo@email.com", "3002900000", "34");
                Juez juez2 = new Juez("tilin", "de las nieves", "canova34@example.com", "3018462246", "92");
                Juez juez3 = new Juez("mamberroi", "de luque", "ewe@example.com", "3005902500", "1233");

                // Crear un torneo
                Torneo torneo = new Torneo("Copa Chamoy", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                TipoTorneoGenero.MUJERES);

                // Registrar los jueces en el torneo
                torneo.registrarJuez(juez);
                torneo.registrarJuez(juez2);
                torneo.registrarJuez(juez3);

                // Verificar que se hayan registrado los tres jueces correctamente
                assertEquals(3, torneo.getJueces().size());

                LOG.info("Fin de prueba verificarRegistrarJuezDatosCompletos");
        }

        /**
         * Verifica que no sea posible agregar un juez repetido en el torneo.
         */
        @Test
        public void verificarAgregarJuezRepetido() {
                LOG.info("Inicio de prueba verificarAgregarJuezRepetido...");

                // Crear dos jueces con datos completos
                Juez juez = new Juez("moi", "miapellidouwu", "lacasadelolo@email.com", "3002900000", "34");
                Juez juez2 = new Juez("moi", "miapellidouwu", "lacasadelolo@email.com", "3002900000", "34");

                // Crear un torneo
                Torneo torneo = new Torneo("Copa venezuela", LocalDate.now().plusMonths(1),
                                LocalDate.now().minusDays(15),
                                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                TipoTorneoGenero.MUJERES);

                // Registrar el primer juez en el torneo
                torneo.registrarJuez(juez);

                // Verificar que no sea posible registrar el segundo juez repetido
                assertThrows(Throwable.class, () -> torneo.registrarJuez(juez2));

                LOG.info("Fin de prueba verificarAgregarJuezRepetido");
        }

        /**
         * Verifica que se obtenga correctamente la lista de enfrentamientos de un
         * equipo por nombre.
         */
        @Test
        public void verificarListaEnfrentamientosEquipoPorNombre() {
                LOG.info("Inicio de prueba verificarListaEnfrentamientosEquipoPorNombre...");

                // Crear representantes, estadísticas y lugar
                var representante1 = new Persona("Sancho", "Panza", "SanchoPanza@gmail.com", "3847834783");
                var representante2 = new Persona("Claudia", "Panzo", "ClaudiaPanza@gmail.com", "33433834783");
                var representante3 = new Persona("Paco", "Roco", "PacoRoco@gmail.com", "334267783");
                var representante4 = new Persona("Poac", "soco", "Pacoasdsa@gmail.com", "334234283");
                var estadistica1 = new Estadistica();
                var estadistica2 = new Estadistica();
                var estadistica3 = new Estadistica();
                var estadistica4 = new Estadistica();
                var lugar = new Lugar("Camp nou", "Pereira");

                // Crear equipos
                var visitante1 = new Equipo("Los tilines", representante1, estadistica1);
                var local1 = new Equipo("Los reprobados", representante2, estadistica2);
                var visitante2 = new Equipo("Los chuchuwa", representante3, estadistica3);
                var local2 = new Equipo("Los tarará", representante4, estadistica4);

                // Crear enfrentamientos
                var enfrentamiento1 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante1, local1);
                var enfrentamiento2 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante2, local1);
                var enfrentamiento3 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante1, local2);
                var enfrentamiento4 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante2, local2);

                // Crear torneo
                Torneo torneo = new Torneo("Copa Piston", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                TipoTorneoGenero.HOMBRES);

                // Registrar enfrentamientos en el torneo
                torneo.registrarEnfrentamiento(enfrentamiento1);
                torneo.registrarEnfrentamiento(enfrentamiento2);
                torneo.registrarEnfrentamiento(enfrentamiento3);
                torneo.registrarEnfrentamiento(enfrentamiento4);

                // Verificar que se obtenga la lista correcta de enfrentamientos para el equipo
                // "Los tilines"
                assertEquals(2, torneo.obtenerListaEnfrentamientosEquipoPorNombre("Los tilines").size());

                LOG.info("Fin de prueba verificarListaEnfrentamientosEquipoPorNombre...");
        }

        /**
         * Verifica que se obtenga correctamente la lista de enfrentamientos de un juez
         * por licencia.
         */
        @Test
        public void validarListaEnfrentamientosJuezPorLicencia() {
                LOG.info("Inicio de prueba verificarListaEnfrentamientosJuezPorLicencia...");

                // Crear representantes, estadísticas y lugar
                var representante1 = new Persona("Sancho", "Panza", "SanchoPanza@gmail.com", "3847834783");
                var representante2 = new Persona("Claudia", "Panzo", "ClaudiaPanza@gmail.com", "33433834783");
                var representante3 = new Persona("Paco", "Roco", "PacoRoco@gmail.com", "334267783");
                var representante4 = new Persona("Poac", "soco", "Pacoasdsa@gmail.com", "334234283");
                var estadistica1 = new Estadistica();
                var estadistica2 = new Estadistica();
                var estadistica3 = new Estadistica();
                var estadistica4 = new Estadistica();
                var lugar = new Lugar("Camp nou", "Pereira");

                // Crear equipos
                var visitante1 = new Equipo("Los tilines", representante1, estadistica1);
                var local1 = new Equipo("Los reprobados", representante2, estadistica2);
                var visitante2 = new Equipo("Los chuchuwa", representante3, estadistica3);
                var local2 = new Equipo("Los tarará", representante4, estadistica4);

                // Crear enfrentamientos
                var enfrentamiento1 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante1, local1);
                var enfrentamiento2 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante2, local1);
                var enfrentamiento3 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante1, local2);
                var enfrentamiento4 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante2, local2);

                // Crear jueces
                var juez1 = new Juez("Camilo", "Tolon", "CamiloTolon@gmail.com", "3043891162", "4321");
                var juez2 = new Juez("Tra", "Tri", "CamiloTolon@gmail.com", "3043891162", "1234");

                // Registrar jueces en los enfrentamientos
                enfrentamiento1.registrarJuez(juez1);
                enfrentamiento1.registrarJuez(juez2);
                enfrentamiento2.registrarJuez(juez1);
                enfrentamiento2.registrarJuez(juez2);
                enfrentamiento3.registrarJuez(juez2);
                enfrentamiento4.registrarJuez(juez2);

                // Crear torneo
                Torneo torneo = new Torneo("Copa Piston", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                TipoTorneoGenero.HOMBRES);

                // Registrar enfrentamientos en el torneo
                torneo.registrarEnfrentamiento(enfrentamiento1);
                torneo.registrarEnfrentamiento(enfrentamiento2);
                torneo.registrarEnfrentamiento(enfrentamiento3);
                torneo.registrarEnfrentamiento(enfrentamiento4);

                // Verificar que se obtenga la lista correcta de enfrentamientos para el juez
                // con licencia "4321"
                assertEquals(2, torneo.obtenerListaEnfrentamientosJuezPorLicencia("4321").size());

                LOG.info("Fin de prueba verificarListaEnfrentamientosJuezPorLicencia...");
        }

        /**
         * Verifica que se agregue un enfrentamiento correctamente a la lista de
         * enfrentamientos del torneo.
         */
        @Test
        public void verificarAgregarEnfrentamientoAListaEnfrentamientos() {
                LOG.info("Inicio de prueba verificarAgregarEnfrentamientoAListaEnfrentamientos...");

                // Crear representantes, estadísticas y lugar
                var representante1 = new Persona("Sancho", "Panza", "SanchoPanza@gmail.com", "3847834783");
                var representante2 = new Persona("Claudia", "Panzo", "ClaudiaPanza@gmail.com", "33433834783");
                var representante3 = new Persona("Paco", "Roco", "PacoRoco@gmail.com", "334267783");
                var representante4 = new Persona("Poac", "soco", "Pacoasdsa@gmail.com", "334234283");
                var estadistica1 = new Estadistica();
                var estadistica2 = new Estadistica();
                var estadistica3 = new Estadistica();
                var estadistica4 = new Estadistica();
                var lugar = new Lugar("Camp nou", "Pereira");

                // Crear equipos
                var visitante1 = new Equipo("Los tilines", representante1, estadistica1);
                var local1 = new Equipo("Los reprobados", representante2, estadistica2);
                var visitante2 = new Equipo("Los chuchuwa", representante3, estadistica3);
                var local2 = new Equipo("Los tarará", representante4, estadistica4);

                // Crear enfrentamientos
                var enfrentamiento1 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante1, local1);
                var enfrentamiento2 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante2, local1);
                var enfrentamiento3 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante1, local2);
                var enfrentamiento4 = new Enfrentamiento(LocalDate.now(), LocalTime.now(), lugar, visitante2, local2);

                // Crear torneo
                Torneo torneo = new Torneo("Copa Piston", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                TipoTorneoGenero.HOMBRES);

                // Registrar enfrentamientos en el torneo
                torneo.registrarEnfrentamiento(enfrentamiento1);
                torneo.registrarEnfrentamiento(enfrentamiento2);
                torneo.registrarEnfrentamiento(enfrentamiento3);
                torneo.registrarEnfrentamiento(enfrentamiento4);

                // Verificar que se agregue correctamente el enfrentamiento a la lista del
                // torneo
                assertEquals(4, torneo.getEnfrentamientos().size());

                LOG.info("Fin de prueba verificarAgregarEnfrentamientoAListaEnfrentamientos...");
        }

        /**
         * Verifica el buen funcionamiento de
         * obtenerListaEquiposClasificacionResultadosEnfrentamiento al registrar
         * enfrentamientos.
         * Asegura que la lista devuelta tenga el tamaño esperado y que los puntajes de
         * los equipos se actualicen correctamente.
         */
        @Test
        public void VerificarBuenFuncionamientoObtenerListaEquiposClasificacionResultadosEnfrentamiento() {
                LOG.info("Inicio de prueba VerificarBuenFuncionamientoObtenerListaEquiposClasificacionResultadosEnfrentamiento");

                // Crear torneo con fechas y límites
                Torneo torneo1 = new Torneo("Copa Piston", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                TipoTorneoGenero.MIXTO);

                // Crear representante, lugar y equipos
                var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
                var lugar = new Lugar("granada", "Avenida Alamos");
                var visitante1 = new Equipo("Real Madrid", representante, new Estadistica());
                var local1 = new Equipo("Barcelona", representante, new Estadistica());

                // Registrar equipos en el torneo
                torneo1.registrarEquipo(visitante1);
                torneo1.registrarEquipo(local1);

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

                // Verificar que la lista devuelta tenga el tamaño esperado
                assertEquals(2, torneo1.obtenerListaEquiposClasificacionResultadosEnfrentamiento().size());

                // Verificar que los puntajes de los equipos se actualicen correctamente
                assertEquals(5, visitante1.getEstadistica().getPuntaje());
                assertEquals(7, local1.getEstadistica().getPuntaje());

                LOG.info("Fin de prueba VerificarBuenFuncionamientoObtenerListaEquiposClasificacionResultadosEnfrentamiento");
        }

}
