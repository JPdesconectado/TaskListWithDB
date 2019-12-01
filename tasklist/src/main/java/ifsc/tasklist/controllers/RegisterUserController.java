package ifsc.tasklist.controllers;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import ifsc.tasklist.dbcontrol.UserDAO;
import ifsc.tasklist.dbentities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
	
	FileChooser fc = new FileChooser();
	Stage janela;
	String valor = null;
	
	@FXML
	void register(ActionEvent e) throws UnknownHostException, IOException {
		List<User> users = new UserDAO().getAll();
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsuario().equals(txtUser.getText())) {
				System.out.println("Usuário já cadastrado.");
				return;
			}
		}
		if(txtUser.getText().isBlank()) {
			System.err.println("Usuário Obrigatório.");
			return;
		}
		if(txtPass.getText().isBlank()) {
			System.err.println("Senha Obrigatória.");
			return;
		}
		
		if (valor == null) {
			User user = new User(txtUser.getText(), txtEmail.getText(), txtPass.getText(), "file:/C:/Users/Shino/Documents/GitHub/TaskListWithDB/tasklist/src/main/resources/ifsc/images/sil.jpg");
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
