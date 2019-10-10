package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import ifsc.tasklist.App;
import ifsc.tasklist.Task;
import ifsc.tasklist.TaskDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController implements Initializable{
	LocalDate data;
	
	@FXML
	GridPane gridpane;
	
	@FXML
	Button btConfig;
	
	@FXML
	Button btHelp;
	
	@FXML
	Button btProject;
	
	@FXML
	Button btGoals;
	
	@FXML
	Button btNotification;
	
	@FXML
	Button btSearch;
	
	@FXML
	Button btNewTask;
	
	@FXML
	TextField txtSearch;

	@FXML
	ListView<Task> listTask;
	

	public void updateList() {
		TaskDAO dao = new TaskDAO();
		listTask.setItems(null);
		listTask.setItems((ObservableList<Task>) dao.getAll());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList();
	}
	
	// Mudança de Janelas:
	@FXML
	public void novaTarefa() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registertask.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
	}
	
	@FXML
	public void irConfig() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("config.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML
	public void irProjeto() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("project.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML
	public void irMetas() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("goals.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML
	public void irAjuda() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("help.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML
	public void irNotification() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("notification.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
}

