package ifsc.tasklist.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
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
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
