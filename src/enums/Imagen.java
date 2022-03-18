/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author PC2
 */
public class Imagen {

    private int id;
    private String ruta;
    private String extension;
    private String nombre;
    private String idIv;
    private String idBtn;

    public Imagen() {
    }

    /**
     * Get the value of idBtn
     *
     * @return the value of idBtn
     */
    public String getIdBtn() {
        return idBtn;
    }

    /**
     * Set the value of idBtn
     *
     * @param idBtn new value of idBtn
     */
    public void setIdBtn(String idBtn) {
        this.idBtn = idBtn;
    }

    /**
     * Get the value of idIv
     *
     * @return the value of idIv
     */
    public String getIdIv() {
        return idIv;
    }

    /**
     * Set the value of idIv
     *
     * @param idIv new value of idIv
     */
    public void setIdIv(String idIv) {
        this.idIv = idIv;
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

    /**
     * Get the value of extension
     *
     * @return the value of extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Set the value of extension
     *
     * @param extension new value of extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Get the value of ruta
     *
     * @return the value of ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Set the value of ruta
     *
     * @param ruta new value of ruta
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

}
