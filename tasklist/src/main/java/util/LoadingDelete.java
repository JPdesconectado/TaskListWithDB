package util;

import java.io.IOException;
import com.jfoenix.controls.JFXProgressBar;
import ifsc.tasklist.controllers.LoadingDeleteController;
import ifsc.tasklist.dbcontrol.GoalsDAO;
import ifsc.tasklist.dbcontrol.ProjectDAO;
import ifsc.tasklist.dbcontrol.TarefaProjetoDAO;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbcontrol.UserDAO;
import javafx.application.Platform;

public class LoadingDelete implements Runnable{

	private JFXProgressBar progressBar;
	private LoadingDeleteController ldc;
	
	public LoadingDelete(JFXProgressBar progressBar, LoadingDeleteController ldc) {
		this.progressBar = progressBar;
		this.ldc = ldc;
	}
	
	
	@Override
	public void run() {
		updateProgress(0);
		while (true) {
			
				try {
					new UserDAO().getAll();
					new GoalsDAO().getAll();
					new TaskDAO().getAll();
					new ProjectDAO().getAll();
					new TarefaProjetoDAO().getAll();
					
				Platform.runLater(() -> {
					try {
						ldc.delete();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				return;
				} catch (Exception e) {
					if(progressBar.getProgress() > 0.5) {
						updateProgress(0);
					}else {
						updateProgress(progressBar.getProgress() + 0.1);
					
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
				}
			
		}
		
	}
	
	
	private void updateProgress(double value) {
		Platform.runLater(() -> {
			progressBar.setProgress(value);
		});
	}
}
