
package t01a04_2_bravojose;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class T01A04_2_BravoJose extends Application {
    // Lanzador de la aplicacion
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(final Stage primaryStage) {
        // Establecemos el titulos de la ventana principal, la que llama al dialogo no modal
        primaryStage.setTitle("Dialog");
        // Creamos el nodo raiz que será un grupo
        Group root = new Group();
        // Creamos la escena con nodo raiz el grupo root creado, 400 pixeles de ancho, 300 pixeles de alto, y color de fondo blanco
        Scene scene = new Scene(root, 400, 300, Color.WHITE);

        // Establecemos como la escena activa la escena que acabamos de crear
        primaryStage.setScene(scene);
        // Mostramos el escenario con la escena creada como escena principal
        primaryStage.show();
        
        // Creamos otro escenario que será el dialogo no modal abierto, se llama al constructor de la clase MyDialog pasandole como argumento el escenario de la
        //ventana principal, ya que el dialogo abierto será propiedad del escenario que representa la primera ventana,
        //lo que significa, por ejemplo, que si cerramos la ventana dueña del dialogo, el dialogo tambien se cerrará (se permite porque es no modal)
        Stage myDialog = new MyDialog(primaryStage);
        // Ajusta el tamaño de la ventana myDialog al contenido que tendra la propia ventana
        myDialog.sizeToScene();
        // Mostramos el dialogo no modal
        myDialog.show();
  
    }
}

// Clase que es el dialogo abierto
class MyDialog extends Stage {

    // Constructor (es lo que se llama para crear el dialogo desde el main)
    public MyDialog(Stage owner) {
        // Llamamos al constructor de la clase de la que hereda (la superclase o clase padre), en este caso el constructor de la clase Stage
        super();
        // Especificamos quien es la ventana propietaria de el escenario que estamos creando, es decir, la ventana propietaria del dialogo, 
        //la cual se ha pasado como argumento al constructor, y se le ha pasado el primaryStage (el escenario principal), por lo tanto este sera el propietario del dialogo.
        // initOwner debe realizarse antes de llamar al metodo para mostrar el escenario, si su argumento es null, será la ventana principal, si no se especifica, tiene como valor por defecto null, por lo tanto sera una ventana principal
        //initOwner(null); -> De esta forma el dalogo no tiene dueño y por tanto es un escenario principal, si cerramos la ventana no se cierra el dialogo ya que es independiente
        initOwner(owner);
        // Establecemos titulo
        setTitle("title");
        // Establecemos la opacidad de la ventana en 0.9 (canal alpha), será un poco transparente, por lo que se vera lo que tenga detras en el indice Z
        setOpacity(.90);
        // Creamos el nodo raiz de este escenario (dialogo), que será un grupo
        Group root = new Group();
        // Creamos la escena con nodoraiz el grupo root, 250 pixeles de ancho, 150 pixeles de alto, y color de fondo blanco
        Scene scene = new Scene(root, 250, 150, Color.WHITE);
        // Establecemos esta escena que acabamos de crear como la escena activa del escenario que representa el dialogo
        setScene(scene);

        // Creamos un contenedor con layout GridPane
        GridPane gridpane = new GridPane();
        // Establecemos padding del GridPane
        gridpane.setPadding(new Insets(5));
        // Establecemos la separacion entre columnas (Hgap) y filas (Vgap)
        gridpane.setHgap(5);
        gridpane.setVgap(5);

        // Creamos una etiqueta para pedir el nombre de usuario
        Label userNameLbl = new Label("User Name: ");
        // Añadimos la etiqueta en la columna 0, fila 1 del GridPane (Si cogemos la 0, 0, se queda muy pegado al borde de la pantalla en el borde superior
        gridpane.add(userNameLbl, 0, 1);

        // Creamos una etiqueta para pedir la contraseña
        Label passwordLbl = new Label("Password: ");
        // Añadimos la etiqueta en la columna 0, fila 2 del GridPane
        gridpane.add(passwordLbl, 0, 2);
        
        // Creamos un campo de texto para la introduccion del nombre de usuario, el cual por defecto tendra al usuario Admin
        final TextField userNameFld = new TextField("Admin");
        // Añadimos dicho campo de texto al GridPane en la columna 1, fila 1
        gridpane.add(userNameFld, 1, 1);

        // Creamos el campo de texto para la contraseña
        final PasswordField passwordFld = new PasswordField();
        // Establecemos el texto que contendra dicho campo por defecto
        passwordFld.setText("password");
        // Añadimos el campo para la contraseña al GridPane
        gridpane.add(passwordFld, 1, 2);

        // Creamos el boton de Login
        Button login = new Button("Change");
        // Creamos un manejador para controlar la accion del boton al ser presionado, el cual lo unico que hace es cerrar el dialogo para volver a la ventana principal
        login.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                close();
            }
        });
        // Añadimos el boton al GridPane (contenedor ubicado en el grupo que representa el nodo raiz de la escena)
        gridpane.add(login, 1, 3);
        // Alineamos el boton (horizontalmente) a la derecha dentro de su celda para que aparezca a la misma altura que los campos de texto
        GridPane.setHalignment(login, HPos.RIGHT);
        
        // Añadimos el contenedor donde hemos ubicado los distintos componentes al nodo raiz de la escena
        root.getChildren().add(gridpane);
    }
}