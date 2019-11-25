package ifsc.tasklist.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class GoalsController implements Initializable{
	Boolean check;
	
	@FXML
	ProgressBar proTasks;
	
	@FXML
	ProgressBar proProject;
	
	@FXML
	JFXButton btVoltar;
	
	public void update() {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		update();
		
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}

	
}
