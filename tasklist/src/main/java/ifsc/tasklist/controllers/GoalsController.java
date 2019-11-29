package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;


public class GoalsController implements Initializable{
	double qtde;
	
	@FXML
	JFXProgressBar proTasks;
	
	@FXML
	JFXProgressBar proProject;
	
	@FXML
	JFXButton btVoltar;

	
	@FXML
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}

	
	private void updateProgress(double value) {
		proTasks.setProgress(value);
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			List<Task> tasks = new TaskDAO().getAll();
			qtde = tasks.size();
			qtde = qtde/10;
			updateProgress(qtde);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
