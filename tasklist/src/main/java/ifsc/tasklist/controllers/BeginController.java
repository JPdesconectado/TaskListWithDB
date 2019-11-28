package ifsc.tasklist.controllers;

import java.io.IOException;
import java.util.List;
import com.jfoenix.controls.JFXButton;
import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.UserDAO;
import ifsc.tasklist.dbentities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BeginController{
	static String name;
	
	@FXML
	GridPane gridpane;
	
	@FXML
	TextField txtUser;
	
	@FXML
	TextField txtPass;
	
	@FXML
	JFXButton btBegin;
	
	@FXML
	JFXButton btCadastrar;
	
	@FXML
	private void register() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registeruser.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void begin(ActionEvent e) throws IOException {
		List<User> users = new UserDAO().getAll();
		for (int i = 0; i < users.size(); i++) {
			
			if (users.get(i).getUsuario().equals(txtUser.getText()) && users.get(i).getSenha().equals(txtPass.getText())) {
				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
				Parent parent = fxmlLoader.load();
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setScene(scene);
				name = txtUser.getText();
				stage.show();
				JFXButton btn = (JFXButton) e.getSource();
				Scene scene2 = btn.getScene();
				Stage stage2 = (Stage) scene2.getWindow();
				stage2.close();
				return;
			}
			
		}
		System.out.println("Usuário ou Senha Inválida, tente novamente.");
		
	}

}
