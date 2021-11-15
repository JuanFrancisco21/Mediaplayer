package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import Utils.Conexion;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	  Parent root = loadFXML("Login");
          scene = new Scene(root, root.prefWidth(0)+100, root.prefHeight(0)+50);
          stage.setScene(scene);
          //stage.setResizable(false); 
          stage.getIcons().add(new Image("https://play-lh.googleusercontent.com/7N4p6FtJ1jsI4vSEYY40HhHcYXTQKV5nEXyle5AMj_FMxU-UqsN_dxFL80kpM81WCQ"));
          stage.setTitle("SoungApp");
          stage.show();
          stage.setOnCloseRequest(event->{
          	try {
              	Conexion.cerrarConexion();
  			} catch (Exception e) {
  				System.out.println("");			
  			}
      	});
    }

    public static void setRoot(String fxml) throws IOException {
    	Parent root = loadFXML(fxml);
        scene.getWindow().setHeight(root.prefHeight(0)+70);
        scene.getWindow().setWidth(root.prefWidth(0)+70);
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}