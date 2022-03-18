/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import listviews.cuenta.Cuenta;
import utils.FxDialogs;

public class Cuentas {

    static ArrayList str_cuentas = new ArrayList();

    private static Cuentas instance = null;

    private HashMap<Integer, Cuenta> cuentas = new HashMap<Integer, Cuenta>();

    // static method to create instance of Singleton class 
    public static Cuentas getInstance() {
        if (instance == null) {
            instance = new Cuentas();
        }
        return instance;
    }

    public HashMap<Integer, Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(HashMap<Integer, Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void cerrarCuentas() {
        try {
            if (cuentas.size() > 0) {
                for (Map.Entry<Integer, Cuenta> entry : cuentas.entrySet()) {
                    Cuenta c = entry.getValue();
                    if (c.getDriver() != null) {
                        c.getDriver().quit();
                    }
                }
            }
        } catch (Exception e) {
            FxDialogs.showException("Error", "", e);
        }
    }

    public static ArrayList load() {

        // Wrap all in a try/catch block to trap I/O errors.
        try {
            // Open file to read from, named SavedObj.sav.
            FileInputStream saveFile = new FileInputStream("cuentas.sav");

            // Create an ObjectInputStream to get objects from save file.
            ObjectInputStream save = new ObjectInputStream(saveFile);

            str_cuentas = (ArrayList) save.readObject();

            //System.out.println("\t\t" + cuentas);
            // Close the file.
            save.close(); // This also closes saveFile.

        } catch (Exception exc) {
            str_cuentas = new ArrayList();
            //exc.printStackTrace(); // If there was an error, print the info.
            System.err.println("Error al cargar");
        }
        return str_cuentas;
    }

    public static boolean save(String cuenta) {
        // Create some data objects for us to save.
        if (!str_cuentas.contains(cuenta)) {
            str_cuentas.add(cuenta);
        }
        try {
            // Open a file to write to, named SavedObj.sav.
            FileOutputStream saveFile = new FileOutputStream("cuentas.sav");

            // Create an ObjectOutputStream to put objects into save file.
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            // Now we do the save.
            save.writeObject(str_cuentas);

            // Close the file.
            save.close(); // This also closes saveFile.
            //System.out.println("guardado");
            return true;
        } catch (Exception exc) {
            //exc.printStackTrace(); // If there was an error, print the info.
            System.err.println("Error al guardar");
            return false;
        }
    }

    public static boolean delete(String cuenta) {
        try {
            str_cuentas.remove(cuenta);
            // Open a file to write to, named SavedObj.sav.
            FileOutputStream saveFile = new FileOutputStream("cuentas.sav");

            // Create an ObjectOutputStream to put objects into save file.
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            // Now we do the save.
            save.writeObject(str_cuentas);

            // Close the file.
            save.close(); // This also closes saveFile.
            //System.out.println("eliminado");
            return true;
        } catch (Exception exc) {
            //exc.printStackTrace(); // If there was an error, print the info.
            System.err.println("Error al eliminar");
            return false;
        }
    }
}
