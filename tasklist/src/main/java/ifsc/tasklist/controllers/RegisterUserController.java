package ifsc.tasklist.controllers;

import java.io.File;

import com.jfoenix.controls.JFXButton;
import ifsc.tasklist.dbcontrol.UserDAO;
import ifsc.tasklist.dbentities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RegisterUserController{
	
	@FXML
	TextField txtUser;
	
	@FXML
	TextField txtEmail;
	
	@FXML
	TextField txtPass;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	JFXButton btUp;
	
	@FXML
	JFXButton btCadastrar;
	
	ListView<User> listUser;
	FileChooser fc = new FileChooser();
	Stage janela;
	String valor = null;
	
	@FXML
	void register(ActionEvent e) {
		if (valor == null) {
			User user = new User(txtUser.getText(), txtEmail.getText(), txtPass.getText(), "oi");
			new UserDAO().add(user);
			JFXButton btn = (JFXButton) e.getSource();
			Scene scene = btn.getScene();
			Stage stage = (Stage) scene.getWindow();
			stage.close();
		}else {
		User user = new User(txtUser.getText(), txtEmail.getText(), txtPass.getText(), valor);
		new UserDAO().add(user);
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
		
		System.out.println(user.getUsuario() + user.getSenha());
	}
	}
	
	@FXML
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
	
	@FXML
	public void upImage() {
		janela = (Stage) btUp.getScene().getWindow();
		File file = fc.showOpenDialog(janela);
		if (file != null) {
			valor = file.toURI().toString();
		}else {
			valor = null;
		}
			
	}

}
