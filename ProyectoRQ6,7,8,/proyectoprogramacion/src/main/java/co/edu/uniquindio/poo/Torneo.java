/**
 * Clase que agrupa los datos de un Torneo
 * @author Área de programación UQ - Daniel Narvaez, Diego Flores, Esteban Maya
 * @since 2023-11
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;
import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Torneo {
    private final String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaCierreInscripciones;
    private final byte numeroParticipantes;
    private final byte limiteEdad;
    private final int valorInscripcion;
    private final TipoTorneo tipoTorneo;
    private final TipoTorneoGenero tipoTorneoGenero;
    private final Collection<Equipo> equipos;
    private final Collection<Juez> jueces;
    private final Collection<Estadistica> estadisticas;
    private final Collection<Enfrentamiento> enfrentamientos;

    public Torneo(String nombre, LocalDate fechaInicio,
            LocalDate fechaInicioInscripciones,
            LocalDate fechaCierreInscripciones, byte numeroParticipantes,
            byte limiteEdad, int valorInscripcion, TipoTorneo tipoTorneo, TipoTorneoGenero tipoTorneoGenero) {

        ASSERTION.assertion(nombre != null, "El nombre es requerido");
        ASSERTION.assertion(numeroParticipantes >= 0, "El número de participantes no puede ser negativo");
        ASSERTION.assertion(limiteEdad >= 0, "El limite de edad no puede ser negativo");
        ASSERTION.assertion(valorInscripcion >= 0, "El valor de la inscripción no puede ser negativo");

        setFechaInicioInscripciones(fechaInicioInscripciones);
        setFechaCierreInscripciones(fechaCierreInscripciones);
        setFechaInicio(fechaInicio);
        this.numeroParticipantes = numeroParticipantes;
        this.limiteEdad = limiteEdad;
        this.valorInscripcion = valorInscripcion;
        this.tipoTorneo = tipoTorneo;
        this.tipoTorneoGenero = tipoTorneoGenero;
        this.nombre = nombre;
        this.enfrentamientos = new LinkedList<>();

        this.jueces = new LinkedList<>();
        this.equipos = new LinkedList<>();
        this.estadisticas = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }

    public LocalDate getFechaCierreInscripciones() {
        return fechaCierreInscripciones;
    }

    public byte getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public byte getLimiteEdad() {
        return limiteEdad;
    }

    public int getValorInscripcion() {
        return valorInscripcion;
    }

    public TipoTorneo getTipoTorneo() {
        return tipoTorneo;
    }

    public TipoTorneoGenero getTipoTorneoGenero() {
        return tipoTorneoGenero;
    }

    public Collection<Juez> getJueces() {
        return Collections.unmodifiableCollection(jueces);
    }

    public Collection<Estadistica> getEstadisticas() {
        return Collections.unmodifiableCollection(estadisticas);
    }

    public Collection<Equipo> getEquipos() {
        return Collections.unmodifiableCollection(equipos);

    }

    public Collection<Enfrentamiento> getEnfrentamientos() {
        return Collections.unmodifiableCollection(enfrentamientos);
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        ASSERTION.assertion(fechaInicio != null, "La fecha de inicio es requerida");
        ASSERTION.assertion((fechaInicioInscripciones == null || fechaInicio.isAfter(fechaInicioInscripciones)) &&
                (fechaCierreInscripciones == null || fechaInicio.isAfter(fechaCierreInscripciones)),
                "La fecha de inicio no es válida");
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        ASSERTION.assertion(fechaInicioInscripciones != null, "La fecha de inicio de inscripciones es requerida");
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }

    public void setFechaCierreInscripciones(LocalDate fechaCierreInscripciones) {
        ASSERTION.assertion(fechaCierreInscripciones != null, "La fecha de cierre es requerida");
        ASSERTION.assertion(fechaCierreInscripciones.isAfter(fechaInicioInscripciones),
                "La fecha de cierre de inscripciones debe ser posterior a la fecha de inicio de inscripciones");
        this.fechaCierreInscripciones = fechaCierreInscripciones;
    }

    /**
     * Permite registrar un equipo en el torneo
     * 
     * @param equipo Equipo a ser registrado
     * @throws Se genera un error si ya existe un equipo registrado con el mismo
     *            nombre, o en caso de que las inscripciones del torneo no esten
     *            abiertas.
     */
    public void registrarEquipo(Equipo equipo) {
        validarEquipoExiste(equipo);
        validarInscripciopnesAbiertas();
        ASSERTION.assertion(comprobarEquipoCumpleCondicionGenero(equipo),
                "No cumple con la condición de género del torneo");
        equipos.add(equipo);
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
     * Busca un juez por su licencia en la colección de jueces del torneo.
     *
     * @param licenciaJuez Licencia del juez a buscar.
     * @return Optional que contiene al juez encontrado o es vacío si no se
     *         encuentra.
     */
    private Optional<Juez> buscarJuezPorLicencia(String licenciaJuez) {
        Predicate<Juez> condicion = juez -> juez.getLicenciaJuez().equals(licenciaJuez);
        return jueces.stream().filter(condicion).findAny();
    }

    /**
     * Valida que las inscripciones del torneo esten abiertas, en caso de no estarlo
     * genera un assertion error.
     */
    private void validarInscripciopnesAbiertas() {
        boolean inscripcionAbierta = fechaInicioInscripciones.isBefore(LocalDate.now())
                && fechaCierreInscripciones.isAfter(LocalDate.now());
        ASSERTION.assertion(inscripcionAbierta, "Las inscripciones no están abiertas");
    }

    /**
     * Valida que no exista ya un equipo registrado con el mismo nombre, en caso de
     * haberlo genera un assertion error.
     */
    private void validarEquipoExiste(Equipo equipo) {
        boolean existeEquipo = buscarEquipoPorNombre(equipo.getNombre()).isPresent();
        ASSERTION.assertion(!existeEquipo, "El equipo ya esta registrado");
    }

    /**
     * Permite buscar un equipo por su nomnbre entre los equipos registrados en el
     * torneo
     * 
     * @param nombre Nombre del equipo que se está buscando
     * @return Un Optional<Equipo> con el equipo cuyo nombre sea igual al nombre
     *         buscado, o un Optional vacio en caso de no encontrar un equipo con
     *         nombre igual al dado.
     */
    public Optional<Equipo> buscarEquipoPorNombre(String nombre) {
        Predicate<Equipo> condicion = equipo -> equipo.getNombre().equals(nombre);
        return equipos.stream().filter(condicion).findAny();
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las
     * fechas validas de registro,
     * no exista ya un jugador registrado con el mismo nombre y apellido y el
     * jugador no exceda el limite de edad del torneo.
     * 
     * @param nombre  Nombre del equipo en que se desea registrar el jugador
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(String nombre, Jugador jugador) {
        var equipo = buscarEquipoPorNombre(nombre);
        equipo.ifPresent((e) -> registrarJugador(e, jugador));
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las
     * fechas validas de registro,
     * no exista ya un jugado rregistrado con el mismo nombre y apellido y el
     * jugador no exceda el limite de edad del torneo.
     * 
     * @param equipo  Equipo en el que se desea registrar el jugador.
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Equipo equipo, Jugador jugador) {
        ASSERTION.assertion(!LocalDate.now().isAfter(fechaCierreInscripciones),
                "No se pueden registrar jugadores después del a fecha de cierre de inscripciones");
        validarLimiteEdadJugador(jugador);
        validarJugadorExiste(jugador);
        equipo.registrarJugador(jugador);
    }

    /**
     * Permite buscar un jugador basado en su nombre y apellido en todos los equipos
     * registrados en el torneo.
     * 
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador encontrado o un optional vacío en caso de no
     *         haber encontrado un jugador con el nombre y apellido del jugador
     *         buscado.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador) {
        return equipos.stream()
                .map(equipo -> equipo.buscarJugador(jugador))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido,
     * en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion(!existeJugador, "El jugador ya esta registrado");
    }

    /**
     * Valida que no exista se puedan registrar jugadores que al momento del inicio
     * del torneo excedan el limite de edad.
     */
    private void validarLimiteEdadJugador(Jugador jugador) {
        var edadAlInicioTorneo = jugador.calcularEdad(fechaInicio);
        ASSERTION.assertion(limiteEdad == 0 || limiteEdad >= edadAlInicioTorneo,
                "No se pueden registrar jugadores que excedan el limite de edad del torneo");
    }

    /**
     * Obtiene una colección de equipos clasificados por resultados de
     * enfrentamiento.
     * 
     * @return Una colección de equipos ordenada por resultados de enfrentamiento.
     */
    public Collection<Equipo> obtenerListaEquiposClasificacionResultadosEnfrentamiento() {
        return equipos.stream()
                .sorted(Comparator.comparing((Equipo equipo) -> equipo.getEstadistica().getPuntaje()).reversed())
                .toList();
    }

    /**
     * Comprobar si el equipo contiene jugadores del mismo genero que del torneo
     * 
     * @param equipo el equipo que se va a comprobar
     * @return valor booleano, que representara si se cumple o no, con la condición
     *         de género
     */
    private boolean comprobarEquipoCumpleCondicionGenero(Equipo equipo) {

        boolean condicion = false;
        if (tipoTorneoGenero == TipoTorneoGenero.HOMBRES) {
            if (equipo.getJugadores().stream().allMatch(jugador -> jugador.getTipoGenero() == TipoGenero.HOMBRE)) {
                condicion = true;
            }
        } else if (tipoTorneoGenero == TipoTorneoGenero.MUJERES) {
            if (equipo.getJugadores().stream().allMatch(jugador -> jugador.getTipoGenero() == TipoGenero.MUJER)) {
                condicion = true;
            }
        } else {
            condicion = true;

        }
        return condicion;

    }

    /**
     * Obtiene una colección de enfrentamientos en los que participa al menos un
     * juez con la licencia especificada.
     *
     * @param licencia Licencia del juez.
     * @return Colección de enfrentamientos en los que participa el juez con la
     *         licencia especificada.
     */
    public Collection<Enfrentamiento> obtenerListaEnfrentamientosJuezPorLicencia(String licencia) {
        return enfrentamientos.stream()
                .filter(enfrentamiento -> enfrentamiento.getJueces().stream()
                        .anyMatch(juez -> juez.getLicenciaJuez().equals(licencia)))
                .toList();
    }

    /**
     * Registra un enfrentamiento en la colección de enfrentamientos del torneo.
     *
     * @param enfrentamiento Enfrentamiento a registrar.
     * @throws AssertionError Si el enfrentamiento ya está registrado.
     */
    public void registrarEnfrentamiento(Enfrentamiento enfrentamiento) {
        boolean existeEnfrentamiento = buscarEnfrentamiento(enfrentamiento).isPresent();
        ASSERTION.assertion(!existeEnfrentamiento, "El enfrentamiento ya esta registrado");
        enfrentamientos.add(enfrentamiento);
    }

    /**
     * Busca un enfrentamiento en la colección de enfrentamientos del torneo.
     *
     * @param enfrentamiento Enfrentamiento a buscar.
     * @return Optional con el enfrentamiento encontrado, o un Optional vacío si no
     *         se encuentra.
     */
    private Optional<Enfrentamiento> buscarEnfrentamiento(Enfrentamiento enfrentamiento) {
        return enfrentamientos.stream()
                .filter(e -> e.equals(enfrentamiento))
                .findAny();
    }

    /**
     * Obtiene una colección de enfrentamientos en los que participa un equipo con
     * el nombre especificado.
     *
     * @param nombre Nombre del equipo.
     * @return Colección de enfrentamientos en los que participa el equipo con el
     *         nombre especificado.
     */
    public Collection<Enfrentamiento> obtenerListaEnfrentamientosEquipoPorNombre(String nombre) {
        Predicate<Enfrentamiento> condicion1 = enfrentamiento -> enfrentamiento.getVisitante().getNombre()
                .equals(nombre);
        Predicate<Enfrentamiento> condicion2 = enfrentamiento -> enfrentamiento.getLocal().getNombre().equals(nombre);
        return enfrentamientos.stream().filter(condicion1.or(condicion2)).toList();
    }
}
