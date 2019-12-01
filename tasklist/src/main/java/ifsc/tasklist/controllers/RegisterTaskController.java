package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
			Scene scene = btn.getScene();
			Stage stage = (Stage) scene.getWindow();
			stage.close();
		
	}
	
	@FXML
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
