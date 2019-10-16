package ifsc.tasklist.controllers;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import ifsc.tasklist.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FeedbackController {

	@FXML
	JFXSlider avaliacao;
	
	@FXML
	JFXCheckBox btGostou;
	
	@FXML
	JFXTextArea comentario;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	JFXButton btEnviar;
	
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
