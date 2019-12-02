package ifsc.tasklist.controllers;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.FeedbackDAO;
import ifsc.tasklist.dbentities.Feedback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FeedbackController {

	@FXML
	JFXSlider avaliacao;
	
	@FXML
	JFXCheckBox btGostou;
	
	@FXML
	JFXTextArea comentario;
	
	@FXML
	JFXButton btVoltar;
	
	@FXML
	JFXButton btEnviar;
	
	@FXML
	
	public void enviar() throws IOException {
		List<Feedback> feedback = new FeedbackDAO().getAll();
		
		if(feedback.isEmpty()) {
		Feedback fb = new Feedback(BeginController.name, Integer.valueOf((int) avaliacao.getValue()), btGostou.isSelected(), comentario.getText());
		new FeedbackDAO().add(fb);
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("alert.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		voltar();
	}else {
		System.err.println("1 avaliação por pessoa.");
	}
	}
	public void voltar() throws IOException {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
