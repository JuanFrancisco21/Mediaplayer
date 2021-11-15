package Controller;

import java.net.URL;
import java.util.ResourceBundle;


import Model.User;
import Model.DAO.UserDAO;
import Utils.Dialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController implements Initializable {

	@FXML
	private Button sing_in;
	@FXML
	private Button sing_up;
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private Text loginStatusLabel;
	@FXML
	private Text loginText;
	@FXML
	private TextField username;
	@FXML
	private TextField email;
	@FXML
	private TextField password;
	@FXML
	private TextField password2;
	@FXML 
	private Label label3;
	@FXML 
	private Label label4;
	
	private User usuario;

	/**
	 * Al iniciarse comprueba si existe la bbdd y si no existe la crea.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Utils.Conexion.checkBBDD();
	}	
	/**
	 * Verifica que el usuario es correcto.
	 */
	public Boolean CheckUser() {
		Boolean result = false;
		if (username.getText()!=null && username.getText()!="" && username.getText()!=" ") {
    		try { 
    			this.usuario = UserDAO.List_User_By_Name(username.getText());
    			if (this.usuario.getName().equals(username.getText()) && 
    				this.usuario.getPassword().equals(password.getText())) {
    				loginStatusLabel.setText("");
    				result = true;
				}else if(!this.usuario.getName().equals(this.username.getText())) {
	    			loginStatusLabel.setText("NOMBRE ERRONEO");
				}else if(!this.usuario.getPassword().equals(this.password.getText())) {
	    			loginStatusLabel.setText("CONTRASEÑA ERRONEA");
				}
    		} catch (Exception e) {
    			Dialog.showError("Error Login", "Error al Chekear Usuario", "Usuario no válido");
    		}
		}else if(!this.save.isVisible()){
			loginStatusLabel.setText("CREDENCIALES ERRONEOS");
		}
		
		return result;
	}
	/**
	 * Cambia de vista al checkear un usuario válido.
	 */
	@FXML
	public void LogUser() {
		if (CheckUser()) {
    		try { 
    			MainController.initController(usuario);
    			App.setRoot("Main");
    			
    			
    		} catch (Exception e) {
    			Dialog.showError("Error Login", "Error al Entrar", "Usuario no válido");
    		}
		}
	}
	/**
	 * Metodo para mostrar componentes para registrar un usario.
	 */
	@FXML
	public void LoadSingup() {
    		try { 
    			this.sing_in.setVisible(false);
    			this.sing_up.setVisible(false);
    			this.loginText.setText("Sing Up");
    			loginStatusLabel.setText("");
    			this.save.setVisible(true);
    			this.cancel.setVisible(true);
    			this.password2.setVisible(true);
    			this.label3.setVisible(true);
    			this.email.setVisible(true);
    			this.label4.setVisible(true);
    			this.username.setText("");
    			this.password.setText("");
    			this.password2.setText("");
    			
    			
    		} catch (Exception e) {
    			Dialog.showError("Error Register", "Error ", "Usuario no válido");
    		}
	}
	
	/**
	 * Método que crea y guarda usuario válido.
	 * @return true si se a creado, false en caso de no crearlo.
	 */
	public Boolean CreateUser() {
		Boolean result = false;
		if (username.getText().length()>2 && password.getText() != null && password2.getText() != null) {
			try {
				this.usuario = UserDAO.List_User_By_Name(username.getText());

				if (this.usuario.getId()==-1 && this.password.getText().equals(this.password2.getText()) && 
					this.password2.getText().length()>=4 && this.email.getText().length()>=4) {
					loginStatusLabel.setText("");
					UserDAO u=new UserDAO(new User(username.getText(),email.getText(),password.getText()));
					u.insert_update();
					result = true;
				}else if (this.usuario.getId()!=-1) {
					loginStatusLabel.setText("USUARIO EXISTENTE");

				}else if (this.email.getText().length()<=4) {
					loginStatusLabel.setText("CORREO ERRONEO");

				}else if (password.getText().length()>=4 || password2.getText().length()>=4 ||
						   this.password.getText().equals(this.password2.getText())) {
					loginStatusLabel.setText("CONTRASEÑAS INCORRECTAS");

				}else {
					loginStatusLabel.setText("CREDENCIALES ERRONEOS");
				}
			} catch (Exception e) {
				Dialog.showError("Error Registro", "Error al Crear Usuario", "Usuario no válido");
			}
		} else {
			loginStatusLabel.setText("CREDENCIALES ERRONEOS");
		}
		return result;
	}
	
	/**
	 * Método para mostrar componentes para logearse en la app.
	 */
	@FXML
	public void Return() {
		try {
			this.sing_in.setVisible(true);
			this.sing_up.setVisible(true);
			this.loginText.setText("Welcome");
			loginStatusLabel.setText("");
			this.save.setVisible(false);
			this.cancel.setVisible(false);
			this.password2.setVisible(false);
			this.label3.setVisible(false);
			this.email.setVisible(false);
			this.label4.setVisible(false);
			this.username.setText("");
			this.email.setText("");
			this.password.setText("");
			this.password2.setText("");

		} catch (Exception e) {
			Dialog.showError("Error Register", "Error al Entrar", "");
		}
	}
	
	/**
	 * Al registrar usuario cambia los componentes para poder logearse.
	 */
	@FXML
	public void ReturnLogin() {
		
			try { 
				if(CreateUser()) {
    			Return();
				}
    		} catch (Exception e) {
    			Dialog.showError("Error Register", "Error al Entrar", "");
    		}
		
	}

}
