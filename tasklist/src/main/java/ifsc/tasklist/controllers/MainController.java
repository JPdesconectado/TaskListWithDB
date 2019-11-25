package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Task;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController implements Initializable {

	private Thread updateDaemon;
	String titulo;
	String descricao;
	
	@FXML
	GridPane gridpane;
	
	@FXML
	TextField txtSearch;
	
	@FXML
	JFXButton btSearch;
	
	@FXML
	JFXButton btCheckup;
	
	@FXML
	JFXListView<Task> listTask;
	

	public void updateList() {
		TaskDAO dao = new TaskDAO();
		listTask.setItems(null);
		try {
			listTask.setItems((ObservableList<Task>) dao.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateDaemon = new Thread(new UpdateDaemon(listTask));
		updateDaemon.start();
	}
	
	@FXML
	public void delete() {
		if (!listTask.getItems().isEmpty() || listTask.isPressed()) {
			new TaskDAO().delete(listTask.getSelectionModel().getSelectedItem());
		}else {
			System.out.println("NÃO TEM NADA INSERIDO, COMO VAI APAGAR?");
		}
		updateList();
	}
	
	@FXML
	public void update() throws IOException {
		if (!listTask.getItems().isEmpty() || listTask.isPressed()) {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("updatetask.fxml"));
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			UpdateTaskController controller = (UpdateTaskController) fxmlLoader.getController();
			controller.selectedTask(listTask.getSelectionModel().getSelectedItem(), this);
		}else {
			System.out.println("NÃO SELECIONOU NADA, COMO VAI EDITAR?");
		}
		updateList();
	}
	
	@FXML
	public void sair() {
		Platform.exit();
	}

	
	@FXML
	public void pesquisar() {
		
		if(!txtSearch.getText().isBlank()) {
		}else {
			updateList();
		}
		
	}
	
	// Mudança de Janelas:
	@FXML
	public void novaTarefa() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registertask.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		updateList();
		stage.show();
		
	}
	
	@FXML
	public void irConfig() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("config.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	public void irProjeto() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("project.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		updateDaemon.stop();
		stage.show();
	}
	
	@FXML
	public void irMetas() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("goals.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void irAjuda() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("help.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void checkup(){
	}

}

