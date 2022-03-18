/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;

/**
 *
 * @author PC2
 */
public class ItemImagen {

    private int index;
    private ImageView image_view;
    private JFXButton button;
    private Imagen imagen;

    public ItemImagen() {
    }

    /**
     * Get the value of index
     *
     * @return the value of index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set the value of index
     *
     * @param index new value of index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public ImageView getImage_view() {
        return image_view;
    }

    public void setImage_view(ImageView image_view) {
        this.image_view = image_view;
    }

    public JFXButton getButton() {
        return button;
    }

    public void setButton(JFXButton button) {
        this.button = button;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

}
