package ifsc.tasklist.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AlertController {

	@FXML
	Button btEntendido;
	
	@FXML
	public void voltar() {
		Stage janela = (Stage) btEntendido.getScene().getWindow();
		janela.close();
	}
}
