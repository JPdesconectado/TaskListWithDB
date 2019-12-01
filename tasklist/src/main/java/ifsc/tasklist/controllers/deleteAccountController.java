package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import ifsc.tasklist.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class deleteAccountController implements Initializable{

	@FXML
	GridPane gridpane;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	JFXButton btOk;
	
	@FXML
	TextField user;
	
	public void delete(ActionEvent e) throws IOException {
	
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("loadingdelete.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		Button btn = (Button) e.getSource();
		Scene scene2 = btn.getScene();
		Stage stage2 = (Stage) scene2.getWindow();
		stage2.close();
		
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user.setText(BeginController.name);
	}
}
