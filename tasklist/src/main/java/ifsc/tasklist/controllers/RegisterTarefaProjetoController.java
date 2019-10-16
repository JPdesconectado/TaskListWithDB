package ifsc.tasklist.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import ifsc.tasklist.Conn;
import ifsc.tasklist.Project;
import ifsc.tasklist.TarefaProjeto;
import ifsc.tasklist.TarefaProjetoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterTarefaProjetoController implements Initializable{
	LocalDate tempo;
	
	@FXML
	Button btAdicionar;
	
	@FXML
	TextField txtTitle;

	@FXML
	TextArea txtDescription;
	
	@FXML
	ChoiceBox<String> cb;
	
	@FXML
	DatePicker datapega;
	
	void updateChoice(){
		EntityManager em = Conn.getEntityManager();
		List<Project> projects = em.createQuery("select project from Project as project", Project.class).getResultList();
		for (int j = 0; j < projects.size(); j++) {
			cb.getItems().add(projects.get(j).getTitulo());
		}
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateChoice();
		
	}
	
	@FXML
	private void adicionar(ActionEvent e) {
		if (datapega.getValue() == null) {
			tempo = LocalDate.now();
			
			
		}else {
			tempo = datapega.getValue();
			
		}
		
		if (txtTitle.getText().isBlank()) {
			txtTitle.setText("Texto Substituto para Chave-Primária");
		}
		
		
		TarefaProjeto tp = new TarefaProjeto(txtTitle.getText(), txtDescription.getText(), cb.getValue(), tempo);
			new TarefaProjetoDAO().add(tp);
			Button btn = (Button) e.getSource();
			Scene scene = btn.getScene();
			Stage stage = (Stage) scene.getWindow();
			stage.close();
			Project pj = new Project();
			pj.addTarefa(tp);
		
	}
	
}
