package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import com.jfoenix.controls.JFXButton;
import ifsc.tasklist.dbcontrol.ProjectDAO;
import ifsc.tasklist.dbentities.Project;
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
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
