package ifsc.tasklist.controllers;


import java.time.LocalDate;

import ifsc.tasklist.Task;
import ifsc.tasklist.TaskDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateTaskController {
	LocalDate tempo;
	
	@FXML
	private TextField txtTitulo;

	@FXML
	private TextArea txtDescricao;
	
	@FXML
	private DatePicker datapega;
	
	private MainController mainController;

	@FXML
	private void update(ActionEvent e) {
		tempo = datapega.getValue();
		Task task = new Task(txtTitulo.getText(), txtDescricao.getText(), tempo);
		new TaskDAO().update(task);
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		mainController.updateList();
		stage.close();
	}

	public void selectedTask (Task task, MainController mainController) {
		txtTitulo.setText(task.getTitulo());
		txtDescricao.setText(task.getDescricao());
		this.mainController = mainController;
	}
	
}
