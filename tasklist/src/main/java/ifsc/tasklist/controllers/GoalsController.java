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
import javafx.scene.image.ImageView;
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
	ImageView imgPro;
	
	@FXML
	ImageView imgTP;
	
	@FXML
	ImageView imgTask;
	
	@FXML
	ImageView imgObj;
	
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
					
					
					List<Task> tasks = new TaskDAO().getAll();
					qtdeT = tasks.size();
					qtdeT = qtdeT/50;
					updateProgress(proTasks, qtdeT);
					
					List<Project> projects = new ProjectDAO().getAll();
					qtdeP = projects.size();
					qtdeP = qtdeP/10;
					updateProgress(proProject, qtdeP);
					
					List<TarefaProjeto> tp = new TarefaProjetoDAO().getAll();
					qtdeTP = tp.size();
					qtdeTP = qtdeTP/25;
					updateProgress(proTP, qtdeTP);					
					
					qtdeOBJ = tasks.size();
					qtdeOBJ = qtdeOBJ/Integer.valueOf(Obj);
					updateProgress(ObjDay, qtdeOBJ);
					
					if(proTasks.getProgress() == 1) {
						TFlag = "true";
						Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
						new GoalsDAO().update(goal);
						imgTask.setOpacity(1);
					}
							
					if(proProject.getProgress() == 1) {
						PFlag = "true";
						Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
						new GoalsDAO().update(goal);
						imgPro.setOpacity(1);
						
					}
				
					if(proTP.getProgress() == 1) {
						TPFlag = "true";
						Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
						new GoalsDAO().update(goal);
						imgTP.setOpacity(1);
						
					}
					
					if(ObjDay.getProgress() == 1) {
						Goals goal = new Goals(BeginController.name, TFlag, PFlag, TPFlag, Integer.valueOf(ObjDiaria.getText()));
						new GoalsDAO().update(goal);
						imgObj.setOpacity(1);
						
						
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
	
	public void updateList() {
		
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
			if(ObjDay.getProgress() < 1) {
				imgObj.setOpacity(0);
			}else {
				imgObj.setOpacity(1);
			}
		}
	}
}
