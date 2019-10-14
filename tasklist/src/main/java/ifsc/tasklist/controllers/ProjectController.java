package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import ifsc.tasklist.App;
import ifsc.tasklist.Project;
import ifsc.tasklist.ProjectDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProjectController implements Initializable {

	@FXML
	GridPane gridPane;
	
	@FXML
	Button btNewProject;
	
	@FXML
	Button btDelete;
	
	@FXML
	Button btUpdate;
	
	@FXML
	ChoiceBox<String> cb;
	
	@FXML
	ListView<Project> listProject;
	
	public void updateList() {
		ProjectDAO dao = new ProjectDAO();
		listProject.setItems(null);
		listProject.setItems((ObservableList<Project>) dao.getAll());
		cb.setValue(RegisterProjectController.title);
		cb.getItems().addAll(RegisterProjectController.title);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList();
	}
	
	@FXML
	private void delete() {
		if (!listProject.getItems().isEmpty() || listProject.isPressed()) {
			new ProjectDAO().delete(listProject.getSelectionModel().getSelectedItem());
		}else {
			System.out.println("NÃO TEM NADA INSERIDO, COMO VAI APAGAR?");
		}
		
	}
	
	@FXML
	private void update() throws IOException {
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
			System.out.println("NÃO SELECIONOU NADA, COMO VAI EDITAR?");
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
	

	
}
