package ifsc.tasklist.controllers;

import com.jfoenix.controls.JFXButton;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class GoalsController {

	@FXML
	ProgressBar proTasks;
	
	@FXML
	ProgressBar proProject;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	
	public void updateTasks() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Service<Void> servico = new Service() {
            @SuppressWarnings("rawtypes")
			@Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Void call() throws Exception {
                        Thread.sleep(300);
                        updateProgress(1, 10);
                        for (int i = 0; i < 10; i++) {
                            updateProgress(i + 1, 10);
                            Thread.sleep(300);
                        }
                        return null;
                    }
                };
            }
        };
        proTasks.progressProperty().bind(servico.progressProperty());
        servico.restart();
	}
	
	public void updateProject() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Service<Void> servico = new Service() {
            @SuppressWarnings("rawtypes")
			@Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Void call() throws Exception {
                        Thread.sleep(300);
                        updateProgress(1, 36000);
                        for (int i = 0; i < 36000; i++) {
                            updateProgress(i + 1, 36000);
                            Thread.sleep(300);
                        }
                        return null;
                    }
                };
            }
        };
        proProject.progressProperty().bind(servico.progressProperty());
        servico.restart();
	}
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
