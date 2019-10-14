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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateProjectController {

	LocalDate tempo;
	
	
	@FXML
	private GridPane gridPane;
	
	@FXML
	private TextField txtTitulo;

	@FXML
	private TextField txtTarefa;
	
	@FXML
	private DatePicker datapega;
	
	private ProjectController projectController;

	@FXML
	private void update(ActionEvent e) {
		tempo = datapega.getValue();
		Project project = new Project(txtTitulo.getText(), txtTarefa.getText(), tempo);
		new ProjectDAO().update(project);
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		projectController.updateList();
		stage.close();
	}

	public void selectedProject (Project project, ProjectController projectController) {
		txtTitulo.setText(project.getTitulo());
		txtTarefa.setText(project.getTarefas());
		this.projectController = projectController;
	}
	
}
