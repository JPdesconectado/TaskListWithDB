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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController implements Initializable {
<<<<<<< Updated upstream
	boolean notify = false;
=======
	private Thread updateDaemon;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
		listTask.setItems((ObservableList<Task>) dao.getAll());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = dtf.format(LocalDateTime.now());
		for (Task task: listTask.getItems()) {
				if(task.getData().contentEquals(data)){
					titulo = task.getTitulo();
					descricao = task.getDescricao();
					btCheckup.setText("Notificações [1]");
					notify = true;
					break;
				}
				btCheckup.setText("Notificações");
=======
		try {
			listTask.setItems((ObservableList<Task>) dao.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
>>>>>>> Stashed changes
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
		
<<<<<<< Updated upstream
			updateList();
			if(notify == true) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Temos Tarefas para Hoje!");
				alert.setHeaderText("Título da Tarefa: " + titulo);
				alert.setContentText("Descrição da Tarefa: " + descricao);
				alert.showAndWait();
				notify = false;
				
			}else {
				btCheckup.setText("Notificações");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Nenhuma Tarefa Para hoje =(");
				alert.setHeaderText("Você está livre! Por agora...");
				alert.setContentText("Brincadeira, mas não tem tarefas, experimente adicionar algumas =D");
				alert.showAndWait();
		}
		
=======
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Temos Tarefas para Hoje!");
			alert.setHeaderText("Título da Tarefa: " + titulo);
			alert.setContentText("Descrição da Tarefa: " + descricao);
			alert.showAndWait();
			btCheckup.setText("Notificações");

>>>>>>> Stashed changes
	}

}

