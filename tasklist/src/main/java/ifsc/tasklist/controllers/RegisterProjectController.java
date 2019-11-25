package ifsc.tasklist.controllers;

import com.jfoenix.controls.JFXButton;
import ifsc.tasklist.Project;
import ifsc.tasklist.ProjectDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterProjectController {
	
	@FXML
	TextField titulo;
	
	@FXML
	TextField objetivo;
	
	@FXML
	JFXButton btAdicionar;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	public void adicionar(ActionEvent e) {
		Project project = new Project(titulo.getText(), objetivo.getText());
		new ProjectDAO().add(project);
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
