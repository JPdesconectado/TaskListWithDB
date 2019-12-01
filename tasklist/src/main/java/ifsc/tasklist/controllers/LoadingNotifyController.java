package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXProgressBar;

import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.LoadingNotify;

public class LoadingNotifyController implements Initializable{

	@FXML
	private JFXProgressBar progressBar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Thread loadingThread = new Thread(new LoadingNotify(progressBar, this));
		loadingThread.start();
	}
	
	public void notification() throws UnknownHostException, IOException {
		List<Task> tasks = new TaskDAO().getAll();
		LocalDate data = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String tempo;
		tempo = dtf.format(data);
		ObservableList<Task> tarefinhas = FXCollections.observableArrayList();
		for(int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i).getData().equals(tempo)) {
				tarefinhas.add(tasks.get(i));
				
			}else {
				tasks.remove(i);
			}
			
		}
		
		Scene scene2 = progressBar.getScene();
		Stage stage2 = (Stage) scene2.getWindow();
		stage2.close();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("notify.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
