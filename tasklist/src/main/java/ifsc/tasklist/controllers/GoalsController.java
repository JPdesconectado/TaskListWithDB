package ifsc.tasklist.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import ifsc.tasklist.Task;
import ifsc.tasklist.TaskDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GoalsController implements Initializable{
	Boolean check;
	
	@FXML
	ProgressBar proTasks;
	
	@FXML
	ProgressBar proProject;
	
	@FXML
	JFXButton btVoltar;
	
	ObservableList<Task> teste;
	
	public void update() {
		TaskDAO dao = new TaskDAO();
		double j = 0.0;
		teste = (ObservableList<Task>) dao.getAll();
		for(int i = 0; i < teste.size(); i++) {

			j += 0.1;
			proTasks.setProgress(j);
			
			if(teste.size() == 4 && check) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Temos Tarefas para Hoje!");
				alert.setHeaderText("Título da Tarefa: ");
				alert.setContentText("Batata");
				alert.showAndWait();
				check = false;
				break;
			}
		}
		
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
