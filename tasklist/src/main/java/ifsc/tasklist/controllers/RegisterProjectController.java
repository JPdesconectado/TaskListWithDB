package ifsc.tasklist.controllers;

import java.time.LocalDate;

import ifsc.tasklist.Project;
import ifsc.tasklist.ProjectDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterProjectController {
	LocalDate tempo;
	
	@FXML
	TextField titulo;
	
	@FXML
	TextField tarefa;
	
	@FXML
	DatePicker data;
	
	
	@FXML
	Button btAdicionar;
	
	@FXML
	private void adicionar(ActionEvent e) {
		tempo = data.getValue();
		Project project = new Project(titulo.getText(), tarefa.getText(), tempo);
		new ProjectDAO().add(project);
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
	}

	
}
