/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.CheckChrome;
import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;
import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import static config.Config.*;
import javafx.application.Platform;
import javafx.stage.WindowEvent;
import store.Cuentas;

/**
 *
 * @author
 */
public class Main extends Application {

    private Rollbar rollbar;

    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    public Main() {
        Config config = withAccessToken("667187afab7247d4b41600f335302bd1")
                .environment(ENV)
                .codeVersion(VERSION)
                .build();
        this.rollbar = Rollbar.init(config);
        CheckChrome.cerrarProcesos(new String[]{"chromedriver", "chromedriver (32 bits)"});
        CheckChrome.check();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        //we gonna remove the borderless thingie.  
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(false);
        stage.setTitle("WA Manager");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/WAM_logo.png")));
        stage.setResizable(true);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.out.println("Saliendo...");
                Cuentas.getInstance().cerrarCuentas();
                CheckChrome.cerrarProcesos(new String[]{"chromedriver", "chromedriver (32 bits)"});
                Platform.exit();
                System.exit(0);
            }
        });

        //grab your root here
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //sorry about that - Windows defender issue.
        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        CheckChrome.check();
        launch(args);
    }

}
