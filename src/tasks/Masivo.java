/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import enums.ItemImagen;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javafx.concurrent.Task;
import listviews.cuenta.Cuenta;
import listviews.cuenta.ItemMensaje;
import utils.Resultados;

/**
 *
 * @author PC2
 */
public class Masivo extends Task {

    ArrayList<ItemMensaje> telefonos;
    ArrayList<Cuenta> seleccionadas;
    ArrayList<ItemImagen> array_imagenes;
    ItemImagen im_enviar;
    boolean imap;
    Integer tiempo_espera;
    Integer targetSize;
    Integer limite_envios;
    Resultados resultados = Resultados.getInstance();
    LocalTime hinicio;
    LocalTime hfin;

    public Masivo(ArrayList<ItemMensaje> telefonos, ArrayList<Cuenta> seleccionadas, ArrayList<ItemImagen> array_imagenes, ItemImagen im_enviar, boolean imap, Integer tiempo_espera, Integer targetSize, Integer limite_envios, LocalTime hinicio, LocalTime hfin) {
        this.telefonos = telefonos;
        this.seleccionadas = seleccionadas;
        this.array_imagenes = array_imagenes;
        this.im_enviar = im_enviar;
        this.imap = imap;
        this.tiempo_espera = tiempo_espera;
        this.targetSize = targetSize;
        this.limite_envios = limite_envios;
        this.hinicio = hinicio;
        this.hfin = hfin;
    }

    @Override
    protected Object call() throws Exception {
        Date hora_actual;
        DateFormat dateFormath = new SimpleDateFormat("HH");
        DateFormat dateFormatm = new SimpleDateFormat("mm");
        DateFormat dateFormats = new SimpleDateFormat("ss");

        DateTimeFormatter formatterh = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter formatterm = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("ss");

        int i = 0;
        int cont_enviadas = 0;
        int indexcuenta = 0;
        int max = (limite_envios == 0) ? telefonos.size() : limite_envios;

        while (i < telefonos.size()) {
            hora_actual = new Date();
            if ((Integer.parseInt(dateFormath.format(hora_actual)) >= Integer.parseInt(formatterh.format(hfin))
                    && Integer.parseInt(dateFormatm.format(hora_actual)) >= Integer.parseInt(formatterm.format(hfin))
                    && Integer.parseInt(dateFormats.format(hora_actual)) >= Integer.parseInt(formatters.format(hfin)))) {
                resultados.getMsg().add("----------------------------------");
                resultados.getMsg().add("Tiempo cumplido");
                break;
            }

            Cuenta seleccionada = seleccionadas.get(indexcuenta);
            if (array_imagenes.size() > 0) {
                int rnd = new Random().nextInt(array_imagenes.size());
                im_enviar = array_imagenes.get(rnd);
            }
            resultados.getMsg().add("----------------------------------");
            if (imap && array_imagenes.size() > 0) {
                boolean enviado = seleccionada.enviarImagen(telefonos.get(i), im_enviar, true);
                if (enviado && im_enviar != null) {
                    Thread.sleep(5000);
                    seleccionada.enviarMensaje(telefonos.get(i), im_enviar, false);
                }
            } else {
                boolean enviado = seleccionada.enviarMensaje(telefonos.get(i), im_enviar, true);
                if (enviado && im_enviar != null) {
                    Thread.sleep(5000);
                    seleccionada.enviarImagen(telefonos.get(i), im_enviar, false);
                }
            }

            resultados.getMsg().add("----------------------------------");
            long start = System.currentTimeMillis();

            //if (i != (telefonos.size() - 1)) {
            Thread.sleep(tiempo_espera);
            resultados.getMsg().add("Esperando... tiempo en seg = " + ((System.currentTimeMillis() - start) * 0.001));
            //}

            cont_enviadas++;
            if (cont_enviadas == targetSize) {
                cont_enviadas = 0;
                Integer sumindex = indexcuenta + 1;
                if (sumindex >= seleccionadas.size()) {
                    indexcuenta = 0;
                } else {
                    indexcuenta = sumindex;
                }
            }
            i++;

            updateProgress(i, max);

            if (limite_envios > 0 && i == limite_envios) {
                break;
            }
            im_enviar = null;
        }

        return null;
    }

}
