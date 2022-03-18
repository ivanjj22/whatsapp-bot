/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animatefx.animation.BounceInRight;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controls.TimeSpinner;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import enums.Imagen;
import enums.ItemImagen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import listviews.campanna.Mensaje;
import listviews.campanna.ParteMensaje;
import listviews.cuenta.Cuenta;
import listviews.cuenta.CuentaCellFactory;
import listviews.cuenta.ItemMensaje;
import models.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.controlsfx.control.Notifications;
import utils.FxDialogs;
import store.Cuentas;
import tasks.Masivo;
import utils.Resultados;
import utils.UserSession;

/**
 *
 * @author proxc
 */
public class HomeController implements Initializable {

    @FXML
    private Label label_email;

    @FXML
    private Label label_nombres;

    @FXML
    private ImageView image_user;

    @FXML
    private JFXButton btn_cuentas;

    @FXML
    private JFXButton btn_home;

    @FXML
    private JFXButton btn_mensajes;

    @FXML
    private JFXButton btn_masivos;

    @FXML
    private JFXButton btn_resultados;

    @FXML
    private JFXButton btn_conexion;

    @FXML
    private JFXButton btn_nueva_cuenta;

    @FXML
    private JFXButton btn_nueva_imagen;

    @FXML
    private JFXButton btn_importar_numeros;

    @FXML
    private JFXButton btn_enviar_mensaje;

    @FXML
    private JFXButton btn_enviar_mensajes_masivos;

    @FXML
    private JFXButton btn_detener_mensajes_masivos;

    @FXML
    private AnchorPane pane_home;

    @FXML
    private AnchorPane pane_cuentas;

    @FXML
    private AnchorPane pane_mensajes;

    @FXML
    private AnchorPane pane_masivos;

    @FXML
    private AnchorPane pane_resultados;

    @FXML
    private AnchorPane pane_conexion;

    @FXML
    private AnchorPane pane_main;

    //@FXML
    //JFXListView<Cuenta> cuentas_list;
    @FXML
    private TableView<Cuenta> table_cuentas;

    @FXML
    private TableColumn table_cuentas_column_cuenta;

    @FXML
    private TableColumn table_cuentas_column_nombre;

    @FXML
    private TableColumn table_cuentas_column_estado;

    @FXML
    private JFXListView<Cuenta> cuentas_list_mensajes;

    @FXML
    private JFXListView<Cuenta> cuentas_list_mensajes_masivos;

    @FXML
    private JFXCheckBox checkBox_masivos_ciclo;

    @FXML
    private JFXCheckBox checkBox_limite_envios;

    @FXML
    private JFXComboBox combo_unidad_tiempo;

    @FXML
    private Spinner inp_tiempo_espera;

    @FXML
    private Label label_nombre_campanna;

//    @FXML
//    JFXListView<Campanna> lista_campannas_masivos;
    @FXML
    private JFXTextArea mensaje_text;
    Cuenta cuenta_seleccionada;

    @FXML
    private Spinner inp_intercambiar_wa;

    @FXML
    private JFXTextField inp_wp_ciclo;

    @FXML
    private Spinner inp_limite_envios;

    @FXML
    private JFXSpinner spinner_enviar_mensaje;

    @FXML
    private JFXTextArea mensaje_text_masivo;

    @FXML
    private JFXButton btn_salir;

    @FXML
    private JFXNodesList nodesList;

    @FXML
    private JFXCheckBox checkBox_imagen_primero;

    @FXML
    private TextArea textarea_resultados;

    private ContextMenu cm;
    private UserSession userSession = UserSession.getInstace(null, null);
    private Integer cont_cuentas = 0;
    private Cuentas cuentas = Cuentas.getInstance();

    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";

    private Double actual_x_images;

    private ArrayList<ItemImagen> array_imagenes = new ArrayList<>();

    private int cont_imagenes;

    private Thread thead_masivos;

    private Resultados resultados = Resultados.getInstance();

    private TimeSpinner spinnerhinicio;

    private TimeSpinner spinnerhfin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        JFXListView<Label> list = new JFXListView<Label>();
        list.setMaxHeight(170);
        list.setMinWidth(550);
        list.setLayoutX(28);
        list.setLayoutY(135);
        for (int i = 0; i < 20; i++) {
            list.getItems().add(new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. " + i));
        }
        list.getStyleClass().add("mylistview");
        pane_conexion.getChildren().add(list);

//        JFXButton ssbutton1 = new JFXButton();
//        ssbutton1.setStyle("-fx-background-color:  #7a7a7a;");
//        Tooltip tooltipb1 = new Tooltip();
//        tooltipb1.setText("Opciones");
//        GlyphsDude.setIcon(ssbutton1, FontAwesomeIcon.COGS);
//
//        //ssbutton1.setGraphic(ic1);
//        ssbutton1.setTooltip(tooltipb1);
//        ssbutton1.setButtonType(ButtonType.RAISED);
//        ssbutton1.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
//
//        JFXButton btn_cambiarcampanna = new JFXButton();
//        GlyphsDude.setIcon(btn_cambiarcampanna, FontAwesomeIcon.PHONE_SQUARE);
//
//        btn_cambiarcampanna.setButtonType(ButtonType.RAISED);
//        btn_cambiarcampanna.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
//        btn_cambiarcampanna.setStyle("-fx-background-color:  #7a7a7a;");
//
////        JFXButton ssbutton3 = new JFXButton("O2");
////        ssbutton3.setButtonType(ButtonType.RAISED);
////        ssbutton3.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
//        nodesList.setRotate(-180);
//        nodesList.setSpacing(10);
//        // init nodes
//        nodesList.addAnimatedNode(ssbutton1);
//        nodesList.addAnimatedNode(btn_cambiarcampanna);
////        nodesList.addAnimatedNode(ssbutton3);
//
//        btn_cambiarcampanna.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Stage stage = new Stage();
//                Parent root;
//                try {
//                    root = FXMLLoader.load(getClass().getResource("/fxml/modal_campanna.fxml"));
//                    Scene scene = new Scene(root);
//                    stage.setScene(scene);
//                    stage.setTitle("Cambiar campaña");
//                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/repair-tools.png")));
//                    stage.setResizable(false);
//                    stage.initModality(Modality.APPLICATION_MODAL);
//                    stage.initOwner(((Node) event.getSource()).getScene().getWindow());
//                    stage.show();
////                    JFXComboBox<Campanna> combo = (JFXComboBox) scene.lookup("#combo_campannas");
////
////                    combo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
////                        @Override
////                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
////                            campanna_seleccionada = (Campanna) newValue;
////                            //label_nombre_campanna.setText("Campaña: " + campanna_seleccionada.getNombre());
////                            try {
////                                traermensaje();
////                            } catch (SQLException ex) {
////                                System.err.println("Error al traer mensaje" + ex.getMessage());
////                            }
////
////                            ArrayList<ItemMensaje> telefonos = new ArrayList<>();
////                            try {
////                                telefonos = traerNumeros();
////                                FxDialogs.showInformation("Info", "Se han encontrado " + telefonos.size() + " telefonos pendientes por enviar mensaje");
////                            } catch (SQLException ex) {
////                                System.err.println("Error al traer los numeros: " + ex.getMessage());
////                            }
////
////                            stage.close();
////                        }
////                    });
////
////                    combo.setCellFactory(
////                            new Callback<ListView<Campanna>, ListCell<Campanna>>() {
////                        @Override
////                        public ListCell<Campanna> call(ListView<Campanna> param) {
////                            final ListCell<Campanna> cell = new ListCell<Campanna>() {
////                                @Override
////                                public void updateItem(Campanna item, boolean empty) {
////                                    super.updateItem(item, empty);
////                                    if (item == null || empty) {
////                                        setGraphic(null);
////                                    } else {
////                                        setText(item.getCodcampanna() + ". " + item.getNombre());
////                                    }
////                                }
////                            };
////                            return cell;
////                        }
////                    });
////
////                    //Traer las campañas
////                    Statement s = connection.createStatement();
////                    ResultSet rs = s.executeQuery("select * from campannas where codtipocampanna = 5 and estado = 1");
////                    while (rs.next()) {
////                        Campanna c = new Campanna(rs.getInt("codcampanna"), rs.getString("nombre"));
////                        combo.getItems().add(c);
////                    }
////                    rs.close();
//
//                } catch (Exception ex) {
//                    System.err.println("Error: " + ex.getMessage());
//                }
//
//            }
//        });
        // TODO
        try {
            User user = userSession.getUser();
            //label_email.setText(user.getEmail());
            //label_nombres.setText(user.getNombres());

            //URL url2 = new URL(user.getPhoto_url());
            //image = ImageIO.read(url2);
            //File file = new File("profile.png");
            //ImageIO.write(image, "png", file);
            //Image image2 = new Image(file.toURI().toString());
            //image_user.setImage(image2);
            //System.out.println(image_user);
            //image_user.setImage(new Image(user.getPhoto_url()));
            cm = (ContextMenu) table_cuentas.getContextMenu();

            table_cuentas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.SECONDARY && table_cuentas.getSelectionModel().getSelectedItem() != null) {
                        cm.show(table_cuentas, event.getScreenX(), event.getScreenY());
                    }
                }
            });

            table_cuentas.setRowFactory(tv -> {
                TableRow<Cuenta> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        Cuenta c = row.getItem();
                        if (!c.isWithsession()) {
                            c.crearCuenta();
                            table_cuentas.refresh();
                        } else {
                            //FxDialogs.showInformation("Info", "La cuenta ya se encuentra activa");
                            Notifications.create()
                                    .title("Atención")
                                    .text("La cuenta ya se encuentra activa")
                                    .position(Pos.TOP_CENTER)
                                    .owner(pane_main)
                                    .hideAfter(Duration.seconds(8))
                                    .showInformation();
                        }
                    }
                });
                return row;
            });

            cm.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    MenuItem mi = ((MenuItem) event.getTarget());
                    if (mi.getText().equals("Abrir")) {
                        Cuenta c = table_cuentas.getSelectionModel().getSelectedItem();
                        if (!c.isWithsession()) {
                            c.crearCuenta();
                            table_cuentas.refresh();
                        } else {
                            //FxDialogs.showInformation("Info", "La cuenta ya se encuentra activa");
                            Notifications.create()
                                    .title("Atención")
                                    .text("La cuenta ya se encuentra activa")
                                    .position(Pos.TOP_CENTER)
                                    .owner(pane_main)
                                    .hideAfter(Duration.seconds(8))
                                    .showInformation();
                        }
                    }

                    if (mi.getText().equals("Eliminar")) {
                        Cuenta c = table_cuentas.getSelectionModel().getSelectedItem();
                        c.cerrar();
                        int i = table_cuentas.getSelectionModel().getSelectedIndex();
                        table_cuentas.getItems().remove(table_cuentas.getSelectionModel().getSelectedIndex());
                        cuentas.getCuentas().remove(i);
                        cuentas_list_mensajes.getItems().remove(i);
//                        cuentas_list_mensajes_masivos.getItems().remove(i);
                        table_cuentas.refresh();
                    }
                }
            });

            //pabel home
            mensaje_text.setVisible(false);
            btn_enviar_mensaje.setVisible(false);
            spinner_enviar_mensaje.setVisible(false);

            cuentas_list_mensajes.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (cuentas_list_mensajes.getSelectionModel().getSelectedItem() != null) {
                        mensaje_text.setVisible(true);
                        btn_enviar_mensaje.setVisible(true);
                        cuenta_seleccionada = cuentas_list_mensajes.getSelectionModel().getSelectedItem();
                    }
                }
            });
            btn_enviar_mensaje.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Map.Entry<Integer, Cuenta> entry : cuentas.getCuentas().entrySet()) {
                        Integer key = entry.getKey();
                        Cuenta value = entry.getValue();
                        if (value.getNumero().equals(cuenta_seleccionada.getNumero())) {
                            if (!value.isWithsession()) {
                                FxDialogs.showError("Error", "Verifique que lacuenta esté abierta");
                                return;
                            }
                            String numero = FxDialogs.showTextInput("Ingrese numero", "#", "");
                            String mensaje = mensaje_text.getText();
                            ItemMensaje item = new ItemMensaje(null, numero, mensaje, null);

                            try {
                                value.enviarMensaje(item, null, true);
                            } catch (Exception ex) {
                                FxDialogs.showException("Error", "", ex);
                            }
                            break;
                        }
                    }
                }
            });

            //panel cuentas
            //cuentas_list.setCellFactory(new CuentaCellFactory());
            //GlyphsDude.setIcon(btn_nueva_cuenta, FontAwesomeIcon.PLUS);
            Text icon_btn_nueva_cuenta = GlyphsDude.createIcon(FontAwesomeIcon.PLUS, "40.0");
            icon_btn_nueva_cuenta.setFill(Color.WHITE);
            btn_nueva_cuenta.setGraphic(icon_btn_nueva_cuenta);

            table_cuentas_column_cuenta.setCellValueFactory(new PropertyValueFactory<>("numero"));
            table_cuentas_column_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            table_cuentas_column_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            //Panel mensajes
            cuentas_list_mensajes.setCellFactory(new CuentaCellFactory());

            //Panel Resultados
            //Acciones
            btn_resultados.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pane_cuentas.setVisible(false);
                    pane_home.setVisible(false);
                    pane_mensajes.setVisible(false);
                    pane_masivos.setVisible(false);
                    new FadeIn(pane_resultados).play();
                    pane_resultados.setVisible(true);
                    pane_conexion.setVisible(false);
                }
            });

            //Panel conexion
            Text icon_btn_nueva_imagen = GlyphsDude.createIcon(FontAwesomeIcon.PLUS, "40.0");
            icon_btn_nueva_imagen.setFill(Color.WHITE);
            btn_nueva_imagen.setGraphic(icon_btn_nueva_imagen);

            Text icon_btn_importar_numeros = GlyphsDude.createIcon(FontAwesomeIcon.FILE_EXCEL_ALT, "20.0");
            icon_btn_importar_numeros.setFill(Color.WHITE);
            btn_importar_numeros.setGraphic(icon_btn_importar_numeros);

            //Acciones
            btn_conexion.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pane_cuentas.setVisible(false);
                    pane_home.setVisible(false);
                    pane_mensajes.setVisible(false);
                    pane_masivos.setVisible(false);
                    pane_resultados.setVisible(false);
                    new FadeIn(pane_conexion).play();
                    pane_conexion.setVisible(true);
                }
            });

            btn_importar_numeros.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Seleccione archivo a importar");
                    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Excel file", "*.xls", "*.xlsx"));

                    File file = fileChooser.showOpenDialog(null);

                    if (file != null) {
                        try {
                            Workbook workbook = WorkbookFactory.create(file);
                            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
                            DataFormatter dataFormatter = new DataFormatter();

                            HashMap<Integer, HashMap<String, String>> datos = new HashMap<>();
                            ArrayList<String> propiedades = new ArrayList<>();

                            while (sheetIterator.hasNext()) {
                                Sheet sheet = sheetIterator.next();
                                System.out.println("Leyendo hoja: " + sheet.getSheetName());

                                int cont_filas = 0;
                                for (Row row : sheet) {
                                    HashMap<String, String> fila = new HashMap<>();
                                    for (Cell cell : row) {
                                        String cellValue = dataFormatter.formatCellValue(cell);
                                        if (row.getRowNum() == 0) {
                                            propiedades.add(cellValue);
                                        } else {
                                            System.out.println("indice: " + cell.getColumnIndex());
                                        }
                                    }
                                    cont_filas++;
                                    //datos.put(cont_filas, fila);
                                }
                            }
//                            for (ArrayList<String> filas : datos) {
//                                for (String fila : filas) {
//                                    System.out.println(fila);
//                                }
//                            }
                            workbook.close();
                        } catch (Exception ex) {
                            FxDialogs.showException("Error", "Error al leer el archivo", ex);
                        }
                    } else {
                        Notifications.create()
                                .title("Advertencia")
                                .text("Ningun archivo seleccionado")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showWarning();
                    }
                }
            });

            actual_x_images = btn_nueva_imagen.getLayoutX();
            btn_nueva_imagen.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (cont_imagenes >= 3) {
                        FxDialogs.showError("Error", "Solo se permiten 3 imagenes");
                        return;
                    }

                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Seleccione");
                    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg"));

                    List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
                    if (selectedFiles != null) {
                        int suma = selectedFiles.size() + cont_imagenes;
                        if (selectedFiles.size() > 3 || suma > 3) {
                            Notifications.create()
                                    .title("Error")
                                    .text("Solo se permiten máximo 3 imagenes")
                                    .position(Pos.TOP_CENTER)
                                    .owner(pane_main)
                                    .hideAfter(Duration.seconds(8))
                                    .showError();
                            return;
                        }

                        for (int i = 0; i < selectedFiles.size(); i++) {
                            File file = selectedFiles.get(i);
                            agregarImagen(file, true);
                        }
                    } else {
                        Notifications.create()
                                .title("Advertencia")
                                .text("Ningun archivo seleccionado")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showWarning();
                    }
                }
            });

            //Panel Masivos
//            cuentas_list_mensajes_masivos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//            cuentas_list_mensajes_masivos.setCellFactory(new CuentaCellFactory());
            //mensaje_text_masivo.setDisable(true);
            //mensaje_text_masivo.setWrapText(true);
            //inp_wp_ciclo.setDisable(true);
//            combo_unidad_tiempo.getItems().add("Min");
//            combo_unidad_tiempo.getItems().add("Seg");
            //Acciones
            Text icon_btn_enviar_mensajes_masivos = GlyphsDude.createIcon(FontAwesomeIcon.PLAY);
            icon_btn_enviar_mensajes_masivos.setFill(Color.WHITE);
            btn_enviar_mensajes_masivos.setGraphic(icon_btn_enviar_mensajes_masivos);

            Text icon_btn_detener_mensajes_masivos = GlyphsDude.createIcon(FontAwesomeIcon.STOP);
            icon_btn_detener_mensajes_masivos.setFill(Color.WHITE);
            btn_detener_mensajes_masivos.setGraphic(icon_btn_detener_mensajes_masivos);

            btn_masivos.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pane_cuentas.setVisible(false);
                    pane_home.setVisible(false);
                    pane_mensajes.setVisible(false);
                    new FadeIn(pane_masivos).play();
                    pane_masivos.setVisible(true);
                    pane_resultados.setVisible(false);
                    pane_conexion.setVisible(false);
                }
            });

//            checkBox_masivos_ciclo.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    if (checkBox_masivos_ciclo.isSelected()) {
//                        inp_wp_ciclo.setDisable(false);
//                    } else {
//                        inp_wp_ciclo.setDisable(true);
//                    }
//                }
//            });
//            checkBox_limite_envios.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    if (checkBox_limite_envios.isSelected()) {
//                        inp_limite_envios.setDisable(false);
//                    } else {
//                        inp_limite_envios.setDisable(true);
//                    }
//                }
//            });
//            lista_campannas_masivos.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    campanna_seleccionada = lista_campannas_masivos.getSelectionModel().getSelectedItem();
//                    if (campanna_seleccionada != null) {
//                        try {
//                            traermensaje();
//                        } catch (SQLException ex) {
//                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }
//            });
            btn_detener_mensajes_masivos.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (thead_masivos != null) {
                        thead_masivos.stop();
                    }
                }
            });

            btn_enviar_mensajes_masivos.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DateFormat dateFormath = new SimpleDateFormat("HH");
                    DateFormat dateFormatm = new SimpleDateFormat("mm");
                    DateFormat dateFormats = new SimpleDateFormat("ss");
                    Date hora_actual = new Date();

                    DateTimeFormatter formatterh = DateTimeFormatter.ofPattern("HH");
                    DateTimeFormatter formatterm = DateTimeFormatter.ofPattern("mm");
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("ss");

                    if ((Integer.parseInt(formatterh.format(spinnerhinicio.getValue())) > Integer.parseInt(dateFormath.format(hora_actual)))) {
                        Notifications.create()
                                .title("Error")
                                .text("La hora de inicio seleccionada es mayor a la hora actual")
                                .owner(pane_main)
                                .position(Pos.TOP_CENTER)
                                .hideAfter(Duration.seconds(8))
                                .showError();
                        return;
                    }

                    if ((Integer.parseInt(formatterh.format(spinnerhinicio.getValue())) > Integer.parseInt(formatterh.format(spinnerhfin.getValue())))) {
                        Notifications.create()
                                .title("Error")
                                .text("La hora de inicio seleccionada es mayor a la hora de fin")
                                .owner(pane_main)
                                .position(Pos.TOP_CENTER)
                                .hideAfter(Duration.seconds(8))
                                .showError();
                        return;
                    }

//                    ObservableList ol = cuentas_list_mensajes_masivos.getSelectionModel().getSelectedItems();
                    if (!NumberUtils.isNumber(String.valueOf(inp_intercambiar_wa.getValue()))) {
                        Notifications.create()
                                .title("Error")
                                .text("Ingrese el numero de mensajes a enviar por cada cuenta")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showError();
                        return;
                    }

                    Integer targetSize = Integer.parseInt(String.valueOf(inp_intercambiar_wa.getValue()));

//                    if (ol.size() <= 1) {
//                        FxDialogs.showError(null, "Ninguna cuenta seleccionada");
//                        return;
//                    }
//                    if (combo_unidad_tiempo.getSelectionModel().getSelectedItem() == null) {
//                        FxDialogs.showError(null, "Seleccione unidad de tiempo");
//                        return;
//                    }
                    if (!NumberUtils.isNumber(String.valueOf(inp_tiempo_espera.getValue()))) {
                        Notifications.create()
                                .title("Error")
                                .text("Tiempo de espera no valido")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showError();
                        return;
                    }

                    if (String.valueOf(inp_tiempo_espera.getValue()) == null || String.valueOf(inp_tiempo_espera.getValue()).isEmpty()) {
                        Notifications.create()
                                .title("Error")
                                .text("Ingrese el valor de unidad de tiempo")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showError();
                        return;
                    }

                    if (!NumberUtils.isNumber(String.valueOf(inp_limite_envios.getValue()))) {
                        Notifications.create()
                                .title("Error")
                                .text("Ingrese un limite de envios valido")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showError();
                        return;
                    }

                    Integer tiempo_espera = Integer.parseInt(String.valueOf(inp_tiempo_espera.getValue())) * 1000;

                    ArrayList<ItemMensaje> telefonos = new ArrayList<>();

                    if (telefonos.size() == 0) {
                        Notifications.create()
                                .title("Error")
                                .text("No se encontraron numeros para enviar mensajes")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showError();
                        return;
                    }

                    if (targetSize > telefonos.size()) {
                        Notifications.create()
                                .title("Error")
                                .text("Número de mensajes a intercambiar es mayor a la cantidad de mensajes a enviar.")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showError();
                        return;
                    }

                    ArrayList<Cuenta> seleccionadas = new ArrayList<>();
                    boolean cuentas_listas = true;

                    for (Map.Entry<Integer, Cuenta> entryc : cuentas.getCuentas().entrySet()) {
                        Cuenta c = entryc.getValue();
                        if (!c.isWithsession()) {
                            cuentas_listas = false;
                            Notifications.create()
                                    .title("Error")
                                    .text("La cuenta: " + c.getNumero() + " no está activa.")
                                    .position(Pos.TOP_CENTER)
                                    .owner(pane_main)
                                    .hideAfter(Duration.seconds(8))
                                    .showError();
                            break;
                        }
                        seleccionadas.add(c);
                    }
                    if (!cuentas_listas) {
                        return;
                    }

                    String confirmacion = FxDialogs.showConfirm("¿Enviar mensajes a " + telefonos.size() + " numeros?", "¿Está seguro que desea continuar?", new String[]{"Si", "Cancelar"});
                    if (!confirmacion.equals("Si")) {
                        return;
                    }

                    Integer limite_envios = Integer.parseInt(String.valueOf(inp_limite_envios.getValue()));
                    ItemImagen im_enviar = null;
                    boolean imap = checkBox_imagen_primero.isSelected();

                    Masivo task = new Masivo(
                            telefonos,
                            seleccionadas,
                            array_imagenes,
                            im_enviar,
                            imap,
                            tiempo_espera,
                            targetSize,
                            limite_envios,
                            spinnerhinicio.getValue(),
                            spinnerhfin.getValue()
                    );
                    resultados.getMsg().clear();
                    JFXProgressBar bar = new JFXProgressBar();
                    task.setOnFailed(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent event) {
                            try {
                                btn_enviar_mensajes_masivos.setDisable(false);
                                Notifications.create()
                                        .title("Error")
                                        .text("Error al enviar")
                                        .position(Pos.TOP_CENTER)
                                        .owner(pane_main)
                                        .showError();

                                pane_masivos.getChildren().remove(bar);
                                pane_masivos.setVisible(false);
                                new FadeIn(pane_resultados).play();
                                pane_resultados.setVisible(true);

                                ArrayList<String> result = resultados.getMsg();
                                for (String string : result) {
                                    textarea_resultados.appendText(string + "\n");
                                }
                                resultados.getMsg().clear();

                            } catch (Exception e) {
                                FxDialogs.showException("Error", "", e);
                            }
                        }
                    });

                    task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent event) {
                            try {
                                btn_enviar_mensajes_masivos.setDisable(false);
                                Notifications.create()
                                        .title("Terminado")
                                        .text("Proceso terminado")
                                        .position(Pos.TOP_CENTER)
                                        .owner(pane_main)
                                        .showInformation();

                                pane_masivos.getChildren().remove(bar);

                                pane_masivos.setVisible(false);
                                new FadeIn(pane_resultados).play();
                                pane_resultados.setVisible(true);

                                ArrayList<String> result = resultados.getMsg();
                                for (String string : result) {
                                    textarea_resultados.appendText(string + "\n");
                                }
                                resultados.getMsg().clear();

                            } catch (Exception e) {
                                FxDialogs.showException("Error", "", e);
                            }
                        }
                    });

                    bar.setPrefHeight(15);
                    bar.setPrefWidth(380);
                    bar.setLayoutX(29);
                    bar.setLayoutY(390);
                    bar.progressProperty().bind(task.progressProperty());
                    pane_masivos.getChildren().add(bar);
                    thead_masivos = new Thread(task);
                    thead_masivos.start();

                }
            });

            btn_cuentas.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new FadeIn(pane_cuentas).play();
                    pane_cuentas.setVisible(true);
                    pane_home.setVisible(false);
                    pane_mensajes.setVisible(false);
                    pane_masivos.setVisible(false);
                    pane_resultados.setVisible(false);
                    pane_conexion.setVisible(false);
                }
            });
            btn_nueva_cuenta.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String numero = FxDialogs.showTextInput("Ingrese el numero de la cuenta", "#", "");
                    if (numero.trim() != null && !numero.trim().isEmpty()) {
                        agregarCuenta(numero, true);
                    } else {
                        FxDialogs.showError(null, "Ingrese un número");
                    }
                }
            });

            btn_home.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pane_cuentas.setVisible(false);
                    new FadeIn(pane_home).play();
                    pane_home.setVisible(true);
                    pane_mensajes.setVisible(false);
                    pane_masivos.setVisible(false);
                    pane_resultados.setVisible(false);
                    pane_conexion.setVisible(false);
                }
            });

            btn_mensajes.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pane_cuentas.setVisible(false);
                    pane_home.setVisible(false);
                    new FadeIn(pane_mensajes).play();
                    pane_mensajes.setVisible(true);
                    pane_masivos.setVisible(false);
                    pane_resultados.setVisible(false);
                    pane_conexion.setVisible(false);
                }
            });

            btn_salir.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        userSession.clearUserSession();
                        Cuentas.getInstance().cerrarCuentas();
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
//                            //stage.setMaximized(true);
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                        FxDialogs.showException("Error", "", e);
                    }
                }
            });

            //Ocultar los otros panels
            pane_home.setVisible(true);
            pane_conexion.setVisible(false);
            pane_cuentas.setVisible(false);
            pane_mensajes.setVisible(false);
            pane_masivos.setVisible(false);
            pane_resultados.setVisible(false);

            this.cargarcuentas();
            this.cargarImagenes();

            spinnerhinicio = new TimeSpinner();

            //DateTimeFormatter formatterhinicio = DateTimeFormatter.ofPattern("hh:mm a");
            //spinnerhinicio.valueProperty().addListener((obs, oldTime, newTime)
            //        -> System.out.println(formatterhinicio.format(newTime)));
            spinnerhfin = new TimeSpinner();

            //DateTimeFormatter formatterhfin = DateTimeFormatter.ofPattern("hh:mm a");
            //spinnerhinicio.valueProperty().addListener((obs, oldTime, newTime)
            //        -> System.out.println(formatterhfin.format(newTime)));
            spinnerhinicio.setLayoutY(65);
            spinnerhinicio.setLayoutX(110);
            pane_masivos.getChildren().add(spinnerhinicio);

            spinnerhfin.setLayoutY(101);
            spinnerhfin.setLayoutX(110);
            pane_masivos.getChildren().add(spinnerhfin);

        } catch (Exception e) {
            FxDialogs.showException("Error", "", e);
        }

    }

    @FXML
    private void HandleEvents(MouseEvent event) {

    }

    public void cargarImagenes() {
        File carpeta = new File(System.getProperty("user.dir") + "\\assets\\");
        for (File fichero : carpeta.listFiles()) {
            agregarImagen(fichero, false);
        }
    }

    public void agregarImagen(File file, boolean nueva) {
        BufferedImage imagen = null;
        try {
            //Leer imagen
            String ext = FilenameUtils.getExtension(file.toString());
            String nombre = FilenameUtils.getBaseName(file.toString());
            imagen = ImageIO.read(new File(file.toString()));

            //Guardar imagen
            String ruta_guardar = System.getProperty("user.dir") + "\\assets\\" + nombre + "." + ext;
            if (nueva) {
                File outputfile = new File(ruta_guardar);
                ImageIO.write(imagen, ext, outputfile);
            }

            //Mostrar imagen
            ImageView iv = new ImageView();
            if (actual_x_images == btn_nueva_imagen.getLayoutX()) {
                actual_x_images = btn_nueva_imagen.getLayoutX() + (100 + 10);
            } else {
                actual_x_images = actual_x_images + (100 + 10);
            }
            iv.setImage(new Image(file.toURI().toString()));
            String iv_id = java.util.UUID.randomUUID().toString();
            iv.setVisible(true);
            iv.setLayoutX(actual_x_images);
            iv.setLayoutY(btn_nueva_imagen.getLayoutY());
            iv.setFitHeight(100);
            iv.setFitWidth(100);
            iv.setId(iv_id);
            pane_conexion.getChildren().add(iv);
            if (nueva) {
                new BounceInRight(iv).play();
            }

            //Boton eliminar
            JFXButton btn_eliminar_img = new JFXButton("");
            String btn_id = java.util.UUID.randomUUID().toString();
            Text icon_btn_eliminar_img = GlyphsDude.createIcon(FontAwesomeIcon.TIMES, "20.0");
            icon_btn_eliminar_img.setFill(Color.WHITE);
            btn_eliminar_img.setButtonType(ButtonType.FLAT);
            btn_eliminar_img.setGraphic(icon_btn_eliminar_img);
            btn_eliminar_img.setVisible(true);
            btn_eliminar_img.setLayoutX(iv.getLayoutX() + (iv.getFitWidth() - 31));
            btn_eliminar_img.setLayoutY(iv.getLayoutY() + (iv.getFitHeight() - 29.2));
            btn_eliminar_img.setStyle("-fx-background-color:  #ef4747;");
            btn_eliminar_img.setId(btn_id);

            //Crear el objeto de imagen
            Imagen obj_img = new Imagen();
            obj_img.setId(cont_imagenes);
            obj_img.setNombre(nombre);
            obj_img.setExtension(ext);
            obj_img.setRuta(ruta_guardar);
            obj_img.setIdIv(iv_id);
            obj_img.setIdBtn(btn_id);

            btn_eliminar_img.setUserData(obj_img);
            btn_eliminar_img.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Node node = (Node) event.getSource();
                        Scene scene = (Scene) node.getScene();
                        JFXButton el = (JFXButton) event.getTarget();
                        Imagen data = (Imagen) el.getUserData();

                        //Obtener los elementos a eliminar
                        JFXButton be = (JFXButton) scene.lookup("#" + data.getIdBtn());
                        ImageView ive = (ImageView) scene.lookup("#" + data.getIdIv());

                        //eliminar la imagen y el boton del panel
                        pane_conexion.getChildren().remove(be);
                        pane_conexion.getChildren().remove(ive);

                        //eliminar la imagen del almacenamiento
                        File imagen_borrar = new File(data.getRuta());
                        imagen_borrar.delete();

                        //actualizar el ancho
                        actual_x_images = actual_x_images - (100 + 10);

                        array_imagenes.remove(data.getId());
                        cont_imagenes--;

                        int cont_existentes = 0;
                        for (int i = 0; i < array_imagenes.size(); i++) {
                            ItemImagen im = array_imagenes.get(i);
                            ImageView i_m = im.getImage_view();
                            JFXButton b_m = im.getButton();

                            Imagen data_ac = (Imagen) b_m.getUserData();

                            //Crear el nuevo objeto de imagen
                            Imagen obj_img_new = new Imagen();
                            obj_img_new.setId(cont_existentes);
                            obj_img_new.setNombre(data_ac.getNombre());
                            obj_img_new.setExtension(data_ac.getExtension());
                            obj_img_new.setRuta(data_ac.getRuta());
                            obj_img_new.setIdIv(data_ac.getIdIv());
                            obj_img_new.setIdBtn(data_ac.getIdBtn());
                            b_m.setUserData(obj_img_new);

                            double x_i_m = i_m.getLayoutX();
                            double x_b_m = b_m.getLayoutX();

                            if (data.getId() == 0 || cont_existentes >= data.getId()) {
                                x_i_m = x_i_m - (100 + 10);
                                x_b_m = x_b_m - (i_m.getFitWidth() + 10);
                            } else {
                                System.out.println("el");
                            }

                            i_m.setLayoutX(x_i_m);
                            b_m.setLayoutX(x_b_m);

                            im.setButton(b_m);
                            im.setImage_view(i_m);
                            im.setIndex(cont_existentes);
                            array_imagenes.set(i, im);
                            cont_existentes++;
                        }

                        Notifications.create()
                                .title("Éxito")
                                .text("Imagen eliminada")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showInformation();
                    } catch (Exception e) {
                        Notifications.create()
                                .title("Error")
                                .text("Imagen ho ha podido ser eliminada")
                                .position(Pos.TOP_CENTER)
                                .owner(pane_main)
                                .hideAfter(Duration.seconds(8))
                                .showError();
                    }

                }
            });

            pane_conexion.getChildren().add(btn_eliminar_img);
            if (nueva) {
                new FadeIn(btn_eliminar_img).play();
            }

            ItemImagen array_nodes = new ItemImagen();
            array_nodes.setButton(btn_eliminar_img);
            array_nodes.setImage_view(iv);
            array_nodes.setIndex(cont_imagenes);
            array_nodes.setImagen(obj_img);
            array_imagenes.add(array_nodes);
        } catch (Exception ex) {
            FxDialogs.showException("Error", "", ex);
        }
        cont_imagenes++;
    }

    public void agregarCuenta(String numero, boolean abrir) {
        try {
            Cuenta cuenta = new Cuenta(numero);
            if (abrir) {
                cuenta.setWithsession(true);
                cuenta.crearCuenta();
            } else {
                cuenta.setWithsession(false);
            }

            cuentas.getCuentas().put(cont_cuentas, cuenta);
            cont_cuentas++;
            table_cuentas.getItems().add(cuenta);
            cuentas_list_mensajes.getItems().add(cuenta);
            Cuentas.save(numero);
        } catch (Exception e) {
            FxDialogs.showException("Error", "", e);
        }
    }

    public void cargarcuentas() {
        ArrayList cuentas = Cuentas.load();
        //System.out.println(cuentas.size());
        for (int i = 0; i < cuentas.size(); i++) {
            String cuenta = (String) cuentas.get(i);
            this.agregarCuenta(cuenta, false);
        }
    }

}
