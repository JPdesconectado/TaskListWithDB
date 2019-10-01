package ifsc.tasklist;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class AjudaController {

	@FXML
	Hyperlink link;
	
	@FXML
	Button btVoltar;
	
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
