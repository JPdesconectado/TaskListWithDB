package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import com.jfoenix.controls.JFXButton;

import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.ProjectDAO;
import ifsc.tasklist.dbentities.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
	public void adicionar(ActionEvent e) throws UnknownHostException, IOException {
		List<Project> projects = new ProjectDAO().getAll();
		for (int i = 0; i < projects.size(); i++) {
			if(projects.get(i).getTitulo().equals(titulo.getText())) {
				System.out.println("Projeto já criado.");
				return;
			}
		}
		if (titulo.getText().isBlank()) {
			System.err.println("Titulo vazio.");
			return;
		}
		
		if(objetivo.getText().isBlank()) {
			System.err.println("Objetivo vazio.");
			return;
		}
		Project project = new Project(titulo.getText(), objetivo.getText());
		new ProjectDAO().add(project);
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("project.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene2 = new Scene(parent);
		Stage stage2 = new Stage();
		stage2.setScene(scene2);
		stage2.show();

	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
