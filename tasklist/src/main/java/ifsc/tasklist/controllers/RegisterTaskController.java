package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterTaskController {
	LocalDate tempo;
	String dataatt;
	
	@FXML
	JFXButton btAdicionar;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	TextField txtTitle;

	@FXML
	JFXTextArea txtDescription;
	
	@FXML
	DatePicker datapega;
	
	MainController mc;
	
	@FXML
	public void adicionar(ActionEvent e) throws UnknownHostException, IOException {
		List<Task> tasks = new TaskDAO().getAll();
		for (int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i).getTitulo().equals(txtTitle.getText())) {
				System.out.println("Tarefa já criada.");
				return;
			}
		}
		
		if (datapega.getValue() == null) {
			tempo = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataatt = dtf.format(tempo);
			
		}else {
			tempo = datapega.getValue();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataatt = dtf.format(tempo);
		}
		
		if (txtTitle.getText().isBlank()) {
			System.err.println("Titulo vazio.");
			return;
		}
		
		
		Task task = new Task(txtTitle.getText(), txtDescription.getText(), dataatt);
			new TaskDAO().add(task);
			Button btn = (Button) e.getSource();
			Scene scene2 = btn.getScene();
			Stage stage2 = (Stage) scene2.getWindow();
			stage2.close();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		
	}
	
	@FXML
	public void voltar() throws IOException {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
