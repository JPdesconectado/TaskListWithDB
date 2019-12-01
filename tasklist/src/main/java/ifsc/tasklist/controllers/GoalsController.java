package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.GoalsDAO;
import ifsc.tasklist.dbcontrol.ProjectDAO;
import ifsc.tasklist.dbcontrol.TarefaProjetoDAO;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Goals;
import ifsc.tasklist.dbentities.Project;
import ifsc.tasklist.dbentities.TarefaProjeto;
import ifsc.tasklist.dbentities.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class GoalsController implements Initializable{
	double qtdeT, qtdeP, qtdeTP, qtdeOBJ;
	String TFlag = "false";
	String PFlag = "false";
	String TPFlag = "false";
	String Obj;
	@FXML
	JFXProgressBar proTasks;
	
	@FXML
	JFXProgressBar proProject;
	
	@FXML
	JFXProgressBar proTP;
	
	@FXML
	TextField ObjDiaria;
	
	@FXML
	JFXProgressBar ObjDay;
	
	@FXML 
	JFXButton btVoltar;

	@FXML
	JFXButton btEditar;
	
	@FXML
	public void voltar() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
	
	public void update() {
			try {	
					List<Goals> goals = new GoalsDAO().getAll();
					
					if(goals.size() == 0) {
						
					
					ObjDiaria.setText("5");
					Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
					new GoalsDAO().add(goal);
			}else {
					Obj = String.valueOf(goals.get(0).getObjDiario());
					ObjDiaria.setText(String.valueOf(goals.get(0).getObjDiario()));
					

					
					
					
					
					
					
					if(proTasks.getProgress() == 1) {
						TFlag = "true";
						Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
						new GoalsDAO().update(goal);
						
						
						
					}else {
						List<Task> tasks = new TaskDAO().getAll();
						qtdeT = tasks.size();
						qtdeT = qtdeT/2;
						updateProgress(proTasks, qtdeT);
					}
							
					if(proProject.getProgress() == 1) {
						
						PFlag = "true";
						Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
						new GoalsDAO().update(goal);
						
					}else {
						List<Project> projects = new ProjectDAO().getAll();
						qtdeP = projects.size();
						qtdeP = qtdeP/10;
						updateProgress(proProject, qtdeP);
					}
				
					if(proTP.getProgress() == 1) {
						TPFlag = "true";
						Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
						new GoalsDAO().update(goal);
					}else {
						List<TarefaProjeto> tp = new TarefaProjetoDAO().getAll();
						qtdeTP = tp.size();
						qtdeTP = qtdeTP/25;
						updateProgress(proTP, qtdeTP);
					
					}
					
					if(ObjDay.getProgress() == 1) {
						Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
						new GoalsDAO().update(goal);
						
					}else {
						List<Task> tasks = new TaskDAO().getAll();
						qtdeOBJ = tasks.size();
						qtdeOBJ = qtdeOBJ/Integer.valueOf(Obj);
						updateProgress(ObjDay, qtdeOBJ);
					}
			
			}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		update();
	}
	
	private void updateProgress(JFXProgressBar pb, double value) {
		pb.setProgress(value);
	
	}
	
	@FXML
	private void editar() throws UnknownHostException, IOException {
		if(!ObjDiaria.isEditable()) {
			ObjDiaria.setEditable(true);
		}else {
			
			Obj = ObjDiaria.getText();
			Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
			new GoalsDAO().update(goal);
			List<Task> tasks = new TaskDAO().getAll();
			qtdeOBJ = tasks.size();
			qtdeOBJ = qtdeOBJ/Integer.valueOf(Obj);
			updateProgress(ObjDay, qtdeOBJ);
		}
	}
}
