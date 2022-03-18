/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import static config.Config.*;

/**
 *
 * @author PC2
 */
public class CheckInternet {

    public static boolean check() throws IOException {
        try {
            final URL url = new URL(HTTP_URL);
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            FxDialogs.showError(null, "Error: revise su conexión a internet");
            //System.exit(0);
            return false;
        }
    }

}
