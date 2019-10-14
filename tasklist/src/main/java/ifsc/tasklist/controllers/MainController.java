package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import ifsc.tasklist.App;
import ifsc.tasklist.Task;
import ifsc.tasklist.TaskDAO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController implements Initializable {
	boolean choice = true;
	String titulo;
	String descricao;
	@FXML
	private GridPane gridpane;
	
	@FXML
	private TextField txtSearch;

	@FXML
	private Button btCheckup;
	
	@FXML
	private ListView<Task> listTask;
	

	public void updateList() {
		TaskDAO dao = new TaskDAO();
		listTask.setItems(null);
		listTask.setItems((ObservableList<Task>) dao.getAll());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = dtf.format(LocalDateTime.now());
		for (Task task: listTask.getItems()) {
			if(task.getData().contentEquals(data)){
				titulo = task.getTitulo();
				descricao = task.getDescricao();
				btCheckup.setText("Notificações [1]");
				choice = true;
				return;
			}
			choice = false;
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList();
	}
	
	@FXML
	private void delete() {
		if (!listTask.getItems().isEmpty() || listTask.isPressed()) {
			new TaskDAO().delete(listTask.getSelectionModel().getSelectedItem());
		}else {
			System.out.println("NÃO TEM NADA INSERIDO, COMO VAI APAGAR?");
		}
		
	}
	
	@FXML
	private void update() throws IOException {
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
		
	}
	
	@FXML
	private void sair() {
		Platform.exit();
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
	
	@FXML
	public void irProjeto() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("project.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
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
	public void checkup() throws IOException {
		
		if(choice) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Temos Tarefas para Hoje!");
			alert.setHeaderText("Título da Tarefa: " + titulo);
			alert.setContentText("Descrição da Tarefa: " + descricao);
			alert.showAndWait();
			btCheckup.setText("Notificações");
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Nenhuma Tarefa Para hoje =(");
			alert.setHeaderText("Você está livre! Por agora...");
			alert.setContentText("Brincadeira, mas não tem tarefas, experimente adicionar algumas =D");
			alert.showAndWait();
		}
		
	}
}

