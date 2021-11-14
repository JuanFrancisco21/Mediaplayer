package Utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Dialog {
	/**
	 * Clase para mostrar ventanas emergentes con información.
	 * 
	 * @author JF
	 *
	 */
	public static void showError(String title, String header, String description) {
		showDialog(Alert.AlertType.ERROR, title, header, description);
	}

	public static void showWarning(String title, String header, String description) {
		showDialog(Alert.AlertType.WARNING, title, header, description);
		
	}

	public static Boolean showConfirm(String title, String header, String description) {
		Boolean result=showDialog(Alert.AlertType.CONFIRMATION, title, header, description);
		return result;
	}

	public static Boolean showDialog(Alert.AlertType type, String title, String header, String description) {
		Boolean result=false;
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(description);
		Optional<ButtonType> action=alert.showAndWait();
		if (action.get() == ButtonType.OK) {
		    result=true;
		}
		return result;
	}
	
}
