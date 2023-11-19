/**
 * Clase que representa un Juez, hereda atributos y métodos de la clase Persona
 * @author Área de programación UQ - Daniel Narvaez, Diego Flores, Esteban Maya
 * @since 2023-11
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Juez extends Persona {

    private String licenciaJuez;

    public Juez(String nombre, String apellido, String email, String celular, String licenciaJuez) {

        super(nombre, apellido, email, celular);
        ASSERTION.assertion(licenciaJuez != null, "La licencia del juez es requerida");
        this.licenciaJuez = licenciaJuez;

    }

    public String getLicenciaJuez() {
        return licenciaJuez;
    }

}