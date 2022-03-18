/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listviews.cuenta;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
 
public class CuentaCellFactory implements Callback<ListView<Cuenta>, ListCell<Cuenta>>
{
    @Override
    public ListCell<Cuenta> call(ListView<Cuenta> listview) 
    {
        return new CuentaCell();
    }
}