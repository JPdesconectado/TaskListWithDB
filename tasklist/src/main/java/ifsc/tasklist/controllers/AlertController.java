package ifsc.tasklist.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AlertController {

	@FXML
	JFXButton btEntendido;
	
	@FXML
	public void voltar() {
		Stage janela = (Stage) btEntendido.getScene().getWindow();
		janela.close();
	}
}
