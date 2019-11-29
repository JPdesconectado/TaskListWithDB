package util;

import java.io.IOException;

import com.jfoenix.controls.JFXProgressBar;

import ifsc.tasklist.controllers.LoadingController;
import ifsc.tasklist.dbcontrol.TaskDAO;
import javafx.application.Platform;

public class LoadingRun implements Runnable{

	private JFXProgressBar progressBar;
	private LoadingController loadingController;
	
	public LoadingRun(JFXProgressBar progressBar, LoadingController loadingController) {
		this.progressBar = progressBar;
		this.loadingController = loadingController;
	}
	
	@Override
	public void run() {
		updateProgress(0);
		while (true) {
				try {
					new TaskDAO().getAll();
				
				Platform.runLater(() -> {
					try {
						loadingController.closeWindow();
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
