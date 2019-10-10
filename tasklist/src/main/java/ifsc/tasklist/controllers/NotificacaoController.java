package ifsc.tasklist.controllers;

import java.io.IOException;
import java.util.Calendar;

import ifsc.tasklist.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class NotificacaoController {
    Calendar now = Calendar.getInstance();
	
    @FXML
    RadioButton rb;
    
    @FXML
    Button btVoltar;
    
    @FXML
    Button btTarefas;
    
    @FXML
    Label lb;
    
	@FXML
	public void seeData() {
		if(rb.isSelected()) {
		lb.setText("Data Atual: " + now.get(Calendar.DAY_OF_MONTH) + "/0" + now.get(Calendar.MONTH) + "/" + now.get(Calendar.YEAR));
		}else {
			lb.setText("Aguardando");
		}
	}
	
	public void seeTarefas() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("projeto.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
	
}
