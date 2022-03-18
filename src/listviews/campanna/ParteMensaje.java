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
public class ParteMensaje {

    private int codpartemensaje;
    private int codmensaje;
    private String parte;
    private int orden;

    public ParteMensaje() {
    }

    public ParteMensaje(int codpartemensaje, int codmensaje, String parte, int orden) {
        this.codpartemensaje = codpartemensaje;
        this.codmensaje = codmensaje;
        this.parte = parte;
        this.orden = orden;
    }
    
    /**
     * Get the value of codpartemensaje
     *
     * @return the value of codpartemensaje
     */
    public int getCodpartemensaje() {
        return codpartemensaje;
    }

    /**
     * Set the value of codpartemensaje
     *
     * @param codpartemensaje new value of codpartemensaje
     */
    public void setCodpartemensaje(int codpartemensaje) {
        this.codpartemensaje = codpartemensaje;
    }

    /**
     * Get the value of orden
     *
     * @return the value of orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * Set the value of orden
     *
     * @param orden new value of orden
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * Get the value of parte
     *
     * @return the value of parte
     */
    public String getParte() {
        return parte;
    }

    /**
     * Set the value of parte
     *
     * @param parte new value of parte
     */
    public void setParte(String parte) {
        this.parte = parte;
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

}
