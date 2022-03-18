/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author PC2
 */
public class CheckChrome {

    public static String OSDetector() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "Windows";
        } else if (os.contains("nux") || os.contains("nix")) {
            return "Linux";
        } else if (os.contains("mac")) {
            return "Mac";
        } else if (os.contains("sunos")) {
            return "Solaris";
        } else {
            return "Other";
        }
    }

    public static boolean isProcessRunning(String serviceName) throws Exception {
        Process p = Runtime.getRuntime().exec("tasklist");
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(serviceName)) {
                System.out.println(serviceName + " ejecutandose");
                return true;
            }
        }
        System.out.println(serviceName + " no ejecutandose");
        return false;
    }

    public static void killProcess(String serviceName) throws Exception {
        System.out.println("Cerrando " + serviceName);
        try {
            Runtime.getRuntime().exec("tskill " + serviceName);
        } catch (Exception e) {
            FxDialogs.showException("Error", "Error al cerrar el driver", e);
        }
    }

    public static boolean check() {
        File chrome = new File("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
        if (!chrome.exists()) {
            FxDialogs.showError("Error", "Verifique que tenga instalado Google Chrome");
            return false;
            //System.exit(0);
        }
        return true;
    }

    public static void cerrarProcesos(String[] nombre) {
        try {
            Boolean resultados[] = new Boolean[nombre.length];
            for (int i = 0; i < nombre.length; i++) {
                if (isProcessRunning(nombre[i])) {
                    killProcess(nombre[i]);
                }
//            resultados[i] = cerrarDriver(nombre[i]);
            }

        } catch (Exception e) {
            FxDialogs.showException("Error", "", e);
        }

//        boolean fallido = Arrays.stream(resultados).anyMatch(i -> i == false);
//        if (fallido) {
//            FxDialogs.showError("Error", "Error al cerrar el driver");
//        }
    }

    public static boolean cerrarDriver(String nombre) {
        boolean result = false;
        String osName = System.getProperty("os.name");
        String cmd = "";
        if (osName.toUpperCase().contains("WIN")) {//S.O. Windows
            cmd += "tskill " + nombre;
        } else {//Solo ha sido probado en win y linux
            cmd += "killall " + nombre;
        }
        Process hijo;
        try {
            hijo = Runtime.getRuntime().exec(cmd);
            hijo.waitFor();
            if (hijo.exitValue() == 0) {
                System.out.println("proceso " + nombre + " cerrado con exito");
            } else {
                System.out.println("Incapaz de cerrar proceso " + nombre + ". Exit code: " + hijo.exitValue() + "n");
            }
            result = true;
        } catch (Exception e) {
            result = false;
            //FxDialogs.showException("Error", "", e);
        }
        return result;
    }

}
