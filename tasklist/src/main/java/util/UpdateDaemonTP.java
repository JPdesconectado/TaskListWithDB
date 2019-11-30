package util;

import java.util.List;
import ifsc.tasklist.dbcontrol.TarefaProjetoDAO;
import ifsc.tasklist.dbentities.TarefaProjeto;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class UpdateDaemonTP implements Runnable{
	private ListView<TarefaProjeto> listViewTp;
	
	public UpdateDaemonTP(ListView<TarefaProjeto> listViewTp) {
		this.listViewTp = listViewTp;
	}

	@Override
	public void run() {
		while(true) {
			try {
				List<TarefaProjeto> tp = new TarefaProjetoDAO().getAll();
				
				Platform.runLater(() -> {
					listViewTp.setItems(null);
					listViewTp.setItems((ObservableList<TarefaProjeto>) tp);
				});
				System.out.println("Tarefas dos Projetos Atualizadas!");
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
