package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Task;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NotifyController implements Initializable{
	String tempo;
	int flagNew;
	@FXML
	GridPane gridpane;
	
	@FXML
	JFXListView<Task> listTask;
	
	@FXML
	JFXButton btConfirmar;

	@FXML
	Label labelTasks;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TaskDAO dao = new TaskDAO();
		listTask.setItems(null);
		try {
			listTask.setItems((ObservableList<Task>) dao.getAll());
			flagNew = 0;
			LocalDate data = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			tempo = dtf.format(data);
			if(listTask.getItems().isEmpty()) {
				labelTasks.setText("Sem tarefas registradas.");
			}
			int i = 0;
			while( i < listTask.getItems().size()) {
				
				if(!listTask.getItems().get(i).getData().equals(tempo)) {
					listTask.getItems().remove(i);
				}else {
					flagNew = 1;
					i++;
				}
				
			}
			
			
			if(flagNew != 0) {
				labelTasks.setText("Temos Tarefas para hoje!");
				listTask.setOpacity(1);
				
			}else {
				labelTasks.setText("Não temos tarefas para hoje =(");
				listTask.setOpacity(0);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void confirmar() {
		Stage janela = (Stage) btConfirmar.getScene().getWindow();
		janela.close();
	}
}
