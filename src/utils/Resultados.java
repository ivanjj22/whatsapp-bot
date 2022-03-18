/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author PC2
 */
public class Resultados {

    private ArrayList<String> msg = new ArrayList<>();
    private static Resultados instance = null;

    // static method to create instance of Singleton class 
    public static Resultados getInstance() {
        if (instance == null) {
            instance = new Resultados();
        }
        return instance;
    }

    public ArrayList<String> getMsg() {
        return msg;
    }

    public void setMsg(ArrayList<String> msg) {
        this.msg = msg;
    }

}
