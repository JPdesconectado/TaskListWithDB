package ifsc.tasklist.controllers;

import java.util.List;
import ifsc.tasklist.dbcontrol.ProjectDAO;
import ifsc.tasklist.dbentities.Project;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class UpdateDaemonProject implements Runnable {
	private ListView<Project> listViewProject;

	public UpdateDaemonProject(ListView<Project> listViewProject) {
		this.listViewProject = listViewProject;
	}

	@Override
	public void run() {
		while (true) {
			try {
				List<Project> projects = new ProjectDAO().getAll();

				Platform.runLater(() -> {
					listViewProject.setItems(null);
					listViewProject.setItems((ObservableList<Project>) projects);
					
				});
				System.out.println("O daemon atualizou os Projetos");
				Thread.sleep(5000);
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
		}
	}
	
}
