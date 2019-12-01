package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Task;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.UpdateDaemon;

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
	JFXButton btPerfil;
	
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateDaemon = new Thread(new UpdateDaemon(listTask));
		if(!updateDaemon.isAlive()) {
			updateDaemon.start();
		}else {
			updateDaemon.resume();
		}
		
		
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
	

	@SuppressWarnings("deprecation")
	@FXML
	public void sair() {
		Platform.exit();
		updateDaemon.stop();
	}

	
	@SuppressWarnings("deprecation")
	@FXML
	public void pesquisar() throws UnknownHostException, IOException, InterruptedException {
		
		if(!txtSearch.getText().isBlank()) {
			List<Task> tasks = new TaskDAO().getAll();
			for(int i = 0; i < tasks.size(); i++) {
				if(tasks.get(i).getTitulo().equals(txtSearch.getText())) {
					ObservableList<Task> tarefinhas =FXCollections.observableArrayList();
					tarefinhas.add(tasks.get(i));
					listTask.setItems(tarefinhas);
					updateDaemon.suspend();
				}
			}
		}else {
			updateList();
			updateDaemon.resume();
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
	
	@SuppressWarnings("deprecation")
	@FXML
	public void irConfig(ActionEvent e) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("config.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		updateDaemon.stop();
		stage.show();
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene2 = btn.getScene();
		Stage stage2 = (Stage) scene2.getWindow();
		stage2.close();
		
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	public void irProjeto(ActionEvent e) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("project.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		updateDaemon.stop();
		stage.show();
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene2 = btn.getScene();
		Stage stage2 = (Stage) scene2.getWindow();
		stage2.close();
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	public void irMetas(ActionEvent e) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("goals.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		updateDaemon.stop();
		stage.setScene(scene);
		stage.show();
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene2 = btn.getScene();
		Stage stage2 = (Stage) scene2.getWindow();
		stage2.close();
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	public void irAjuda(ActionEvent e) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("help.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		updateDaemon.stop();
		stage.show();
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene2 = btn.getScene();
		Stage stage2 = (Stage) scene2.getWindow();
		stage2.close();
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	public void irPerfil(ActionEvent e) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("perfil.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		Button btn = (Button) e.getSource();
		Scene scene2 = btn.getScene();
		Stage stage2 = (Stage) scene2.getWindow();
		stage2.close();
		stage.setScene(scene);
		updateDaemon.stop();
		stage.show();
	}
	
	@FXML
	public void checkup(){
	}

}

