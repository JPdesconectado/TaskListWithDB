package ifsc.tasklist.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import ifsc.tasklist.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BeginController {

	@FXML
	GridPane gridpane;
	
	@FXML
	JFXButton btBegin;
	
	@FXML 
	private void begin() throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		Stage janela = (Stage) btBegin.getScene().getWindow();
		janela.close();
	}
}
