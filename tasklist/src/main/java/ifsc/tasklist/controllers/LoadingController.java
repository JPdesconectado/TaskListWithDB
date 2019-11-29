package ifsc.tasklist.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXProgressBar;
import ifsc.tasklist.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.LoadingRun;

public class LoadingController  implements Initializable {

	@FXML
	private JFXProgressBar progressBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Thread loadingThread = new Thread(new LoadingRun(progressBar, this));
		loadingThread.start();
	}

	public void closeWindow() throws IOException {		
		Scene scene = progressBar.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();			
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
		Parent parent = fxmlLoader.load();
		Scene newScene = new Scene(parent);
		Stage newStage = new Stage();
		newStage.setScene(newScene);
		newStage.show();
	}

}

