package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import ifsc.tasklist.App;
import ifsc.tasklist.Project;
import ifsc.tasklist.ProjectDAO;
import ifsc.tasklist.TarefaProjeto;
import ifsc.tasklist.TarefaProjetoDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProjectController implements Initializable {

	@FXML
	GridPane gridPane;
	
	@FXML
	JFXButton btNovoProjeto;
	
	@FXML
	JFXButton btNovaTarefa;
	
	@FXML
	JFXButton btPDelete;
	
	@FXML
	JFXButton btPUpdate;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	JFXButton btTUpdate;
	
	@FXML
	JFXButton btTDelete;
	
	@FXML
	JFXListView<TarefaProjeto> listTaskProject;
	
	@FXML
	JFXListView<Project> listProject;
	
	public void updateList() {
		ProjectDAO dao = new ProjectDAO();
		listProject.setItems(null);
		listProject.setItems((ObservableList<Project>) dao.getAll());
		
		TarefaProjetoDAO dao2 = new TarefaProjetoDAO();
		listTaskProject.setItems(null);
		listTaskProject.setItems((ObservableList<TarefaProjeto>) dao2.getAll());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList();
	}
	
	@FXML
	public void delete() {
		if (!listProject.getItems().isEmpty() || listProject.isPressed()) {
			new ProjectDAO().delete(listProject.getSelectionModel().getSelectedItem());
			
		}else {
			System.out.println("Nada selecionado para deletar.");
		}
		
	}
	
	@FXML
	public void Tdelete() {
		if (!listTaskProject.getItems().isEmpty() || listTaskProject.isPressed()) {
			new TarefaProjetoDAO().delete(listTaskProject.getSelectionModel().getSelectedItem());
		}
	}
	
	@FXML
	public void update() throws IOException {
		if (!listProject.getItems().isEmpty() || listProject.isPressed()) {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("updateproject.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		UpdateProjectController controller = (UpdateProjectController) fxmlLoader.getController();
		controller.selectedProject(listProject.getSelectionModel().getSelectedItem(), this);
		
		}else {
			System.out.println("Nada selecionado para edição.");
		}
		
	}
	
	@FXML
	public void Tupdate() throws IOException{
		if (!listTaskProject.getItems().isEmpty() || listTaskProject.isPressed()) {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("updatetaskproject.fxml"));
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			UpdateTaskProjectController controller = (UpdateTaskProjectController) fxmlLoader.getController();
			controller.selectedTaskProject(listTaskProject.getSelectionModel().getSelectedItem(), this);
			
		}else {
			System.out.println("Nada selecionado para edição.");
		}
	}
	
	@FXML
	public void novoProjeto() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registerproject.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void novaTarefa() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registerprojecttask.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
	
}
