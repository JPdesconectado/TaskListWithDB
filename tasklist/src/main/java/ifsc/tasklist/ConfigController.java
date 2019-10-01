package ifsc.tasklist;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class ConfigController {
	
	@FXML
	ToggleButton tb;
	
	@FXML
	Button btVoltar;
	
	@FXML
	Button btFeedback;
	@FXML
	Hyperlink link;
	
	@FXML
	
	public void darkmode() {
		if (tb.isSelected()) {
		btVoltar.getScene().getRoot().setStyle("-fx-base:black");
		}else {
			btVoltar.getScene().getRoot().setStyle("-fx-base:white");
		}
	}
	
	public void goCalendary() {
		try {
		    Desktop.getDesktop().browse(new URL("https://www.calendario-365.com.br/calend%C3%A1rio-2019.html").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
	}
	
	public void goFeedback() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("feedback.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
