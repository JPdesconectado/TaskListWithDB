package ifsc.tasklist.controllers;

import ifsc.tasklist.Task;
import ifsc.tasklist.TaskDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterTaskController {
	
	@FXML
	Button btAdicionar;
	
	@FXML
	TextField txtTitle;

	@FXML
	TextArea txtDescription;

	@FXML
	private void adicionar(ActionEvent e) {
		Task task = new Task(txtTitle.getText(), txtDescription.getText());
		new TaskDAO().add(task);
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
	}
}
