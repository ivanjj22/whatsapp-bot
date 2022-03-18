/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listviews.cuenta;

import javafx.scene.control.ListCell;

public class CuentaCell extends ListCell<Cuenta> {

    @Override
    public void updateItem(Cuenta item, boolean empty) {
        super.updateItem(item, empty);

        int index = this.getIndex();
        String name = null;

        // Format name
        if (item == null || empty) {
        } else {
            name = (index + 1) + ". "
                    + item.getNumero();
        }
        this.setText(name);
        setGraphic(null);
    }
}
