/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import enums.ItemImagen;
import java.util.ArrayList;

/**
 *
 * @author PC2
 */
public class Imagenes {

    private ArrayList<ItemImagen> array_imagenes = new ArrayList<>();

    private static Imagenes instance = null;

    // static method to create instance of singleton class 
    public static Imagenes getInstance() {
        if (instance == null) {
            instance = new Imagenes();
        }
        return instance;
    }

    public ArrayList<ItemImagen> getArray_imagenes() {
        return array_imagenes;
    }

    public void setArray_imagenes(ArrayList<ItemImagen> array_imagenes) {
        this.array_imagenes = array_imagenes;
    }

}
