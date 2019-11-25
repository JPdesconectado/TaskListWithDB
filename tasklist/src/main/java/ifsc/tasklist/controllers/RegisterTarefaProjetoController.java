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
import javafx.stage.Stage;

public class RegisterTarefaProjetoController implements Initializable{
	LocalDate tempo;
	String dataatt;
	
	@FXML
	JFXButton btAdicionar;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	TextField txtTitle;

	@FXML
	JFXTextArea txtDescription;
	
	@FXML
	JFXComboBox<String> cb;
	
	@FXML
	DatePicker datapega;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			List<Project> projects = new ProjectDAO().getAll();
			for (int j = 0; j < projects.size(); j++) {
					System.out.println(projects.get(j).getTitulo());
					cb.getItems().add(projects.get(j).getTitulo());
					
				}
		} catch (Exception e) {

		}
		
	}

	@FXML
	public void adicionar(ActionEvent e) {
		if (datapega.getValue() == null) {
			tempo = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataatt = dtf.format(tempo);
			
		}else {
			tempo = datapega.getValue();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataatt = dtf.format(tempo);
			
		}
		
		if (txtTitle.getText().isBlank()) {
			txtTitle.setText("Texto Substituto para Chave-Primária");
		}
		
		
		TarefaProjeto tp = new TarefaProjeto(txtTitle.getText(), txtDescription.getText(), cb.getValue(), dataatt);
			new TarefaProjetoDAO().add(tp);
			JFXButton btn = (JFXButton) e.getSource();
			Scene scene = btn.getScene();
			Stage stage = (Stage) scene.getWindow();
			stage.close();
			Project pj = new Project();
			pj.addTarefa(tp);
		
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
	
}
