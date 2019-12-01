package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXProgressBar;

import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.GoalsDAO;
import ifsc.tasklist.dbcontrol.ProjectDAO;
import ifsc.tasklist.dbcontrol.TarefaProjetoDAO;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbcontrol.UserDAO;
import ifsc.tasklist.dbentities.Goals;
import ifsc.tasklist.dbentities.Project;
import ifsc.tasklist.dbentities.TarefaProjeto;
import ifsc.tasklist.dbentities.Task;
import ifsc.tasklist.dbentities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.LoadingDelete;

public class LoadingDeleteController implements Initializable {

	@FXML
	private JFXProgressBar progressBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Thread loadingThread = new Thread(new LoadingDelete(progressBar, this));
		loadingThread.start();
	}
	
	public void delete() throws UnknownHostException, IOException {
		
		List<User> users = new UserDAO().getAll();
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsuario().equals(BeginController.name)) {
				new UserDAO().delete(users.get(i));
				break;
			}
		}
		
		List<Goals> goals = new GoalsDAO().getAll();
		for (int i = 0; i < goals.size(); i++) {
			if(goals.get(i).getNomeUser().equals(BeginController.name)) {
				new GoalsDAO().delete(goals.get(i));
				break;
			}
		}
		
		List<Task> tasks = new TaskDAO().getAll();
		for (int i = 0; i < tasks.size(); i++) {
			
			if(!tasks.isEmpty()) {
				new TaskDAO().delete(tasks.get(i));
			}
			
		}
		
		List<Project> projects = new ProjectDAO().getAll();
		for (int i = 0; i < projects.size(); i++) {
			
			if(!projects.isEmpty()) {
				new ProjectDAO().delete(projects.get(i));
			}
			
		}
		
		List<TarefaProjeto> tps = new TarefaProjetoDAO().getAll();
		for (int i = 0; i < tps.size(); i++) {
			if(!tps.isEmpty()) {
				new TarefaProjetoDAO().delete(tps.get(i));
			}
			
		}
		
		Scene scene2 = progressBar.getScene();
		Stage stage2 = (Stage) scene2.getWindow();
		stage2.close();		
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("begin.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		System.out.println("Conta removida.");
	}
}
