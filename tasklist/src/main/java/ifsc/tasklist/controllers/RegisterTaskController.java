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

public class RegisterTaskController {
	LocalDate tempo;
	
	@FXML
	Button btAdicionar;
	
	@FXML
	TextField txtTitle;

	@FXML
	TextArea txtDescription;
	
	@FXML
	DatePicker datapega;

	@FXML
	private void adicionar(ActionEvent e) {
		if (datapega.getValue() == null) {
			tempo = LocalDate.now();
			
			
		}else {
			tempo = datapega.getValue();
			
		}
		
		if (txtTitle.getText().isBlank()) {
			txtTitle.setText("Texto Substituto para Chave-Primária");
		}
		
		
		Task task = new Task(txtTitle.getText(), txtDescription.getText(), tempo);
			new TaskDAO().add(task);
			Button btn = (Button) e.getSource();
			Scene scene = btn.getScene();
			Stage stage = (Stage) scene.getWindow();
			stage.close();
		
	}
}
