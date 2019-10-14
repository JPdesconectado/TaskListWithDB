package ifsc.tasklist.controllers;

import ifsc.tasklist.Project;
import ifsc.tasklist.ProjectDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterProjectController {
	
	public static String title;
	
	@FXML
	TextField titulo;
	
	@FXML
	TextField objetivo;
	
	@FXML
	Button btAdicionar;
	
	@FXML
	private void adicionar(ActionEvent e) {
		title = titulo.getText();
		Project project = new Project(titulo.getText(), objetivo.getText());
		new ProjectDAO().add(project);
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
		teste();
	}
	
	private void teste() {
		ProjectController p = new ProjectController();
		p.updateList();
	}

}
