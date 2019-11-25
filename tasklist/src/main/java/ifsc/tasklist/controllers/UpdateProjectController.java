package ifsc.tasklist.controllers;

import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;

import ifsc.tasklist.dbcontrol.ProjectDAO;
import ifsc.tasklist.dbentities.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateProjectController {

	LocalDate tempo;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	GridPane gridPane;
	
	@FXML
	TextField txtTitulo;

	@FXML
	TextField txtObjetivo;
	
	ProjectController projectController;

	@FXML
	public void update(ActionEvent e) {
		Project project = new Project(txtTitulo.getText(), txtObjetivo.getText());
		new ProjectDAO().update(project);
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		projectController.updateList();
		stage.close();
	}

	public void selectedProject (Project project, ProjectController projectController) {
		txtTitulo.setText(project.getTitulo());
		this.projectController = projectController;
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
