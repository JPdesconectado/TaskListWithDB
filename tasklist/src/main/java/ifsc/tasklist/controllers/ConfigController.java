package ifsc.tasklist.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import ifsc.tasklist.App;
import ifsc.tasklist.DarkMode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfigController implements Initializable{
	public static int teste = 0;
	
	@FXML
	JFXToggleButton tb;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	JFXButton btFeedback;
	@FXML
	Hyperlink link;
	
	@FXML
	Label att;
	
	@FXML
	Label disc;
	
	@FXML
	Label plan;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(teste == 1) {
			tb.setSelected(true);
			}else {
			tb.setSelected(false);
		}
			
	}
	
	@FXML
	public void darkmode(){
		if (tb.isSelected()) {
			DarkMode.escolha = true;
			DarkMode.dm(btVoltar.getScene());
			teste = 1;
			
		}else {
			DarkMode.escolha = false;
			teste = 0;
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
