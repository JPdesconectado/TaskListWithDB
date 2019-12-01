package ifsc.tasklist.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import ifsc.tasklist.dbcontrol.ProjectDAO;
import ifsc.tasklist.dbcontrol.TarefaProjetoDAO;
import ifsc.tasklist.dbentities.Project;
import ifsc.tasklist.dbentities.TarefaProjeto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateTaskProjectController implements Initializable{

	LocalDate tempo;
	String dataatt;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	GridPane gridPane;
	
	@FXML
	TextField txtTitulo;

	@FXML
	JFXComboBox<String> cb;
	
	@FXML
	JFXTextArea txtDescricao;
	
	@FXML
	private DatePicker datapega;
	
	
	ProjectController projectController;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			List<Project> projects = new ProjectDAO().getAll();
			for (int j = 0; j < projects.size(); j++) {
					cb.getItems().add(projects.get(j).getTitulo());
					
				}
		} catch (Exception e) {

		}
	}
	@FXML
	public void update(ActionEvent e) {
		if (datapega.getValue() == null) {
			tempo = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataatt = dtf.format(tempo);
			
		}else {
			tempo = datapega.getValue();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataatt = dtf.format(tempo);
		}
		
		if(cb.getValue() == null) {
			System.err.println("Projeto não selecionado ou vazio.");
			return;
		}
		TarefaProjeto tarefaprojeto = new TarefaProjeto(txtTitulo.getText(), txtDescricao.getText(), cb.getValue(), dataatt);
		new TarefaProjetoDAO().update(tarefaprojeto);
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		projectController.updateList();
		stage.close();
	}

	public void selectedTaskProject (TarefaProjeto tarefaprojeto, ProjectController projectController) {
		txtTitulo.setText(tarefaprojeto.getTitulo());
		txtDescricao.setText(tarefaprojeto.getDescricao());
		this.projectController = projectController;
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
