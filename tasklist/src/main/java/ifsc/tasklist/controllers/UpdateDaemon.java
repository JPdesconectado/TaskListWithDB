package ifsc.tasklist.controllers;

import java.util.List;
import ifsc.tasklist.dbcontrol.TaskDAO;
import ifsc.tasklist.dbentities.Task;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class UpdateDaemon implements Runnable {
	private ListView<Task> listViewTask;

	public UpdateDaemon(ListView<Task> listViewTask) {
		this.listViewTask = listViewTask;
	}

	@Override
	public void run() {
		while (true) {
			try {
				List<Task> tasks = new TaskDAO().getAll();
				Platform.runLater(() -> {
					listViewTask.setItems(null);
					listViewTask.setItems((ObservableList<Task>) tasks);
				});
				System.out.println("O daemon atualizou os Tarefas");
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
