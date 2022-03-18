/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listviews.cuenta;

/**
 *
 * @author PC2
 */
public class ItemMensaje {

    private Integer codpersonacampanna;
    private String telefono;
    private Integer codtelefono;
    private String mensaje;

    public ItemMensaje(Integer codpersonacampanna, String telefono, String mensaje, Integer codtelefono) {
        this.codpersonacampanna = codpersonacampanna;
        this.telefono = telefono;
        this.mensaje = mensaje;
        this.codtelefono = codtelefono;
    }

    public Integer getCodtelefono() {
        return codtelefono;
    }

    public void setCodtelefono(Integer codtelefono) {
        this.codtelefono = codtelefono;
    }

    public Integer getCodpersonacampanna() {
        return codpersonacampanna;
    }

    public void setCodpersonacampanna(Integer codpersonacampanna) {
        this.codpersonacampanna = codpersonacampanna;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
