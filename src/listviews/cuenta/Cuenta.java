/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listviews.cuenta;

import enums.ItemImagen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import models.User;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CopiarImagen;
import utils.FxDialogs;
import store.Cuentas;
import utils.Resultados;
import utils.UserSession;

/**
 *
 * @author PC2
 */
public class Cuenta {

    private String nombre;

    private String numero;

    private String estado = "Inactiva";

    public WebDriver driver;

    private boolean withsession;

    public String WHATSAPP_LINK = "https://api.whatsapp.com/send";

    public final String WHATSAPP_WEB = "https://web.whatsapp.com";

    //Selenium Driver Location
    public static final String DRIVER_LOCATION = System.getProperty("user.dir") + "\\chromedriver.exe";
    public static final String DRIVER_NAME = "webdriver.chrome.driver";

    public HashMap<String, String> storagevalues = new HashMap<String, String>();

    private Resultados resultados = Resultados.getInstance();

    public User user = UserSession.getInstace(null, null).getUser();

    public int cantidad_tabs = 0;

    public Cuenta(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isWithsession() {
        return withsession;
    }

    public void setWithsession(boolean withsession) {
        this.withsession = withsession;
        if (this.withsession) {
            this.setEstado("Activa");
        } else {
            this.setEstado("Inactiva");
        }
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public HashMap<String, String> getStoragevalues() {
        return storagevalues;
    }

    public void setStoragevalues(HashMap<String, String> storagevalues) {
        this.storagevalues = storagevalues;
    }

    public void cerrar() {
        try {
            if (this.isWithsession()) {
                this.driver.quit();
                this.setWithsession(false);
            }
            Cuentas.delete(this.getNumero());
        } catch (Exception e) {
            FxDialogs.showException("error", "", e);
        }
    }

    public String getVersion() {
        String browser_version = null;
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browsername = cap.getBrowserName();
        // This block to find out IE Version number
        if ("internet explorer".equalsIgnoreCase(browsername)) {
            String uAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
            //uAgent return as "MSIE 8.0 Windows" for IE8
            if (uAgent.contains("MSIE") && uAgent.contains("Windows")) {
                browser_version = uAgent.substring(uAgent.indexOf("MSIE") + 5, uAgent.indexOf("Windows") - 2);
            } else if (uAgent.contains("Trident/7.0")) {
                browser_version = "11.0";
            } else {
                browser_version = "0.0";
            }
        } else {
            //Browser version for Firefox and Chrome
            browser_version = cap.getVersion();// .split(".")[0];
        }
        String browserversion = browser_version.substring(0, browser_version.indexOf("."));
        return browserversion;
    }

    public void crearCuenta() {
        try {
            System.setProperty(DRIVER_NAME, DRIVER_LOCATION);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-default-apps");
            chromeOptions.addArguments("--start-maximized");
            this.driver = new ChromeDriver(chromeOptions);
            this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            this.driver.get(WHATSAPP_WEB);
            this.setWithsession(true);
        } catch (Exception e) {
            FxDialogs.showException("error", "", e);
        }
    }

    public boolean enviarMensaje(ItemMensaje item, ItemImagen imagen, boolean primero) throws InterruptedException, SQLException {

        boolean result = false;
        int intentos_click = 0;
        int intentos_enviar = 0;

        String numero_destino = "57" + item.getTelefono();
        String url = this.WHATSAPP_LINK + "?phone=" + numero_destino;
        resultados.getMsg().add("Enviando mensaje");
        resultados.getMsg().add("Cuenta origen: " + this.getNumero());
        resultados.getMsg().add("Cuenta destino: " + numero_destino);
        resultados.getMsg().add("Mensaje: " + item.getMensaje());

        String url_msg = url + "&text=" + item.getMensaje();
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(5000);
        this.driver.get(url_msg);
        //Thread.sleep(1000);
        //Clic en el boton enviar
        while (intentos_click < 3) {
            try {
                if (intentos_click > 0) {
                    resultados.getMsg().add("Reintentando dar click en enviar...");
                }

                WebElement btn_abrir = (new WebDriverWait(this.driver, 30))
                        .until(ExpectedConditions.elementToBeClickable(By.id("action-button")));
                ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", btn_abrir);
                break;
            } catch (Exception ex) {
                resultados.getMsg().add("***Error al dar clic en enviar");
            }
            intentos_click++;
        }
        Thread.sleep(5000);
        //Poner el mensaje y clic en enviar
        while (intentos_enviar < 3) {
            try {
                //List<WebElement> list = this.driver.findElements(By.className("_2S1VP"));
                //WebElement selectedElement = list.get(0);
                //selectedElement.sendKeys(mensaje);
                if (intentos_enviar > 0) {
                    resultados.getMsg().add("Reintentando enviar el mensaje...");
                }

                WebElement btn_enviar = (new WebDriverWait(this.driver, 30))
                        .until(ExpectedConditions.elementToBeClickable(By.className("_35EW6")));
                ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", btn_enviar);

                resultados.getMsg().add("Mensaje enviado con exito!");
                result = true;
                intentos_enviar = 3;
            } catch (Exception e) {
                if (primero) {
                    driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
                    boolean invalido = driver.findElements(By.className("_1CnF3")).size() > 0;
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    if (invalido) {
                        resultados.getMsg().add("*** Mensaje no enviado: numero invalido");

                        result = false;
                        break;
                    }
                }

                result = false;
                resultados.getMsg().add("*** Error al enviar el mensaje: " + e.getMessage());
            }
            intentos_enviar++;
        }

        return result;
    }

    public boolean enviarImagen(ItemMensaje item, ItemImagen imagen, boolean primero) throws InterruptedException, SQLException {
        boolean result = false;
        int intentos = 0;

        String numero_destino = "57" + item.getTelefono();
        String url = this.WHATSAPP_LINK + "?phone=" + numero_destino;

        resultados.getMsg().add("Enviando imagen");
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.get(url);

        String ruta_guardar = imagen.getImagen().getRuta();
        BufferedImage imagen_leer = null;
        try {
            imagen_leer = ImageIO.read(new File(ruta_guardar));
            CopiarImagen ci = new CopiarImagen(imagen_leer);
        } catch (IOException ex) {
            resultados.getMsg().add("***Error al copiar imagen: " + ex.getMessage());
        }

        //Clic en el boton enviar
        try {
            WebElement btn_abrir_imagen = (new WebDriverWait(this.driver, 60))
                    .until(ExpectedConditions.elementToBeClickable(By.id("action-button")));
            ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", btn_abrir_imagen);
        } catch (Exception e) {
            resultados.getMsg().add("***Error al dar clic en enviar imagen");
        }

        while (intentos < 3) {
            try {
                if (intentos > 0) {
                    resultados.getMsg().add("Reintentando enviar imagen...");
                }

                WebDriverWait wait_paste = new WebDriverWait(driver, 60);
                wait_paste.until(ExpectedConditions.visibilityOfElementLocated(By.className("_39LWd")));

                Actions actions = new Actions(driver);
                actions.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "v")).build().perform();

                WebElement btn_enviar_img = (new WebDriverWait(this.driver, 60))
                        .until(ExpectedConditions.elementToBeClickable(By.className("_3hV1n")));
                ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", btn_enviar_img);

                WebDriverWait last = new WebDriverWait(driver, 60);
                last.until(ExpectedConditions.elementToBeClickable(By.className("_2S1VP")));

                result = true;
                break;
            } catch (Exception e) {
                if (primero) {
                    driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
                    boolean invalido = driver.findElements(By.className("_1CnF3")).size() > 0;
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    if (invalido) {
                        resultados.getMsg().add("*** Mensaje no enviado: " + numero_destino + " numero invalido");

                        result = false;
                        break;
                    }
                }
                resultados.getMsg().add("***Error al pegar imagen:");
                result = false;
            }
        }
        return result;
    }

    public void abrirPestanna() {
        cantidad_tabs++;
        ((JavascriptExecutor) this.driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(this.driver.getWindowHandles());
        this.driver.switchTo().window(tabs.get(cantidad_tabs));
    }

    public void cerrarPestanna() {
        ((JavascriptExecutor) this.driver).executeScript("window.close()");
    }
}
