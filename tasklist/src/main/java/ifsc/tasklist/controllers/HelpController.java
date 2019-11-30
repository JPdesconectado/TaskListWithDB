package ifsc.tasklist.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import com.jfoenix.controls.JFXButton;

import ifsc.tasklist.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class HelpController {

	@FXML
	Hyperlink link;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	
	public void abrirNavegador() {
		try {
		    Desktop.getDesktop().browse(new URL("https://todoist.com/templates/").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
	}
	
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
}
