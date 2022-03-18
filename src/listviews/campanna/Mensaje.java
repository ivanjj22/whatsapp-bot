/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listviews.campanna;

/**
 *
 * @author PC2
 */
public class Mensaje {

    private int codmensaje;
    private String nombre;

    public Mensaje(int codmensaje, String nombre) {
        this.codmensaje = codmensaje;
        this.nombre = nombre;
    }
    
    public Mensaje() {

    }
    
    /**
     * Get the value of codmensaje
     *
     * @return the value of codmensaje
     */
    public int getCodmensaje() {
        return codmensaje;
    }

    /**
     * Set the value of codmensaje
     *
     * @param codmensaje new value of codmensaje
     */
    public void setCodmensaje(int codmensaje) {
        this.codmensaje = codmensaje;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
