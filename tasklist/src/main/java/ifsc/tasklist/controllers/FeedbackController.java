package ifsc.tasklist.controllers;

import java.io.IOException;

import ifsc.tasklist.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class FeedbackController {

	@FXML
	Slider avaliacao;
	
	@FXML
	CheckBox btGostou;
	
	@FXML
	TextArea comentario;
	
	@FXML
	Button btVoltar;
	
	@FXML
	Button btEnviar;
	
	@FXML
	
	public void enviar() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("alert.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		voltar();
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
