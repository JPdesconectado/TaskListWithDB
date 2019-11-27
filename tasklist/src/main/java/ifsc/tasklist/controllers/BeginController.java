package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import ifsc.tasklist.App;
import ifsc.tasklist.dbentities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BeginController implements Initializable{
	private Thread updateDaemon;
	
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
	JFXListView<User> listUser;
	
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
	private void begin() throws IOException {
		for (int i = 0; i < listUser.getItems().size(); i++) {
			if (listUser.getItems().get(i).getUsuario().equals(txtUser.getText()) && listUser.getItems().get(i).getSenha().equals(txtPass.getText())) {
				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
				Parent parent = fxmlLoader.load();
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
				break;
			}else {
				System.out.println("Usuário ou Senha Inválido, tente novamente.");
			}
			
		}
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateDaemon = new Thread(new UpdateDaemonUser(listUser));
		updateDaemon.start();
	}

}
