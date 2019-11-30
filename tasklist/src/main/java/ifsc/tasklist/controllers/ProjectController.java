package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.ProjectDAO;
import ifsc.tasklist.dbcontrol.TarefaProjetoDAO;
import ifsc.tasklist.dbentities.Project;
import ifsc.tasklist.dbentities.TarefaProjeto;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.UpdateDaemonProject;
import util.UpdateDaemonTP;

public class ProjectController implements Initializable {
	private Thread updateDaemon, updateDaemon2;
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateDaemon2 = new Thread(new UpdateDaemonTP(listTaskProject));
		updateDaemon = new Thread(new UpdateDaemonProject(listProject));
		updateDaemon2.start();
		updateDaemon.start();
		
	}

	public void updateList() {
		ProjectDAO dao = new ProjectDAO();
		TarefaProjetoDAO dao2 = new TarefaProjetoDAO();
		listProject.setItems(null);
		listTaskProject.setItems(null);
		
		try {
			listProject.setItems((ObservableList<Project>) dao.getAll());
			listTaskProject.setItems((ObservableList<TarefaProjeto>) dao2.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	@SuppressWarnings("deprecation")
	public void voltar() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		updateDaemon2.stop();
		updateDaemon.stop();
		janela.close();
	}
	
}
