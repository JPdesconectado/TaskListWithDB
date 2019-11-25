package ifsc.tasklist.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import ifsc.tasklist.Conn;
import ifsc.tasklist.Project;
import ifsc.tasklist.TarefaProjeto;
import ifsc.tasklist.TarefaProjetoDAO;
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
	
	public void updateChoice(){
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
	public void update(ActionEvent e) {
		tempo = datapega.getValue();
		TarefaProjeto tarefaprojeto = new TarefaProjeto(txtTitulo.getText(), txtDescricao.getText(), cb.getValue(), tempo);
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
