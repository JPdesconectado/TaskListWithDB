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
	ListView<Project> listProject;
	
	public void updateList() {
		ProjectDAO dao = new ProjectDAO();
		listProject.setItems(null);
		listProject.setItems((ObservableList<Project>) dao.getAll());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList();
	}
	
	@FXML
	private void delete() {
		new ProjectDAO().delete(listProject.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	private void update() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("updateproject.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		UpdateProjectController controller = (UpdateProjectController) fxmlLoader.getController();
		controller.selectedProject(listProject.getSelectionModel().getSelectedItem(), this);
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
