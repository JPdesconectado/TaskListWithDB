package ifsc.tasklist.controllers;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;

import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateTaskController {
	LocalDate tempo;
	String dataatt;
	
	@FXML
	private JFXButton btVoltar;
	
	@FXML
	private TextField txtTitulo;

	@FXML
	private TextArea txtDescricao;
	
	@FXML
	private DatePicker datapega;
	
	private MainController mainController;

	@FXML
	private void update(ActionEvent e) {
		if (datapega.getValue() == null) {
			tempo = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataatt = dtf.format(tempo);
			
		}else {
			tempo = datapega.getValue();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataatt = dtf.format(tempo);
		}
		
		Task task = new Task(txtTitulo.getText(), txtDescricao.getText(), dataatt);
		new TaskDAO().update(task);
		mainController.updateList();
		JFXButton btn = (JFXButton) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
	}

	public void selectedTask (Task task, MainController mainController) {
		txtTitulo.setText(task.getTitulo());
		txtDescricao.setText(task.getDescricao());
		this.mainController = mainController;
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
