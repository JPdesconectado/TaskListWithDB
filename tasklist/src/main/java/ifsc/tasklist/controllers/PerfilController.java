package ifsc.tasklist.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import ifsc.tasklist.dbentities.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PerfilController  implements Initializable{
	private Thread updateDaemon;
	
	@FXML
	GridPane gridpane;
	
	@FXML
	JFXButton btAdd;
	
	@FXML
	JFXButton btEditarNome;
	
	@FXML
	JFXButton btEditarEmail;
	
	@FXML
	JFXButton btEditarSenha;
	
	@FXML
	JFXButton btDeleteConta;
	
	@FXML
	ImageView imgview;
	
	@FXML
	ListView<User> listUser;
	
	FileChooser fc = new FileChooser();
	
	@FXML
	public void addFoto() {
		Stage janela = (Stage) btAdd.getScene().getWindow();
		File file = fc.showOpenDialog(janela);
		if (file != null) {
			Image image = new Image(file.toURI().toString());
			imgview.setImage(image);
		}else {
			Image image = new Image("../images/sil.jpg");
			imgview.setImage(image);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateDaemon = new Thread(new UpdateDaemonUser(listUser));
		updateDaemon.start();
		
		for (int i = 0; i < listUser.getItems().size(); i++) {
			System.out.println("Tururu");
			Image image = new Image(listUser.getItems().get(i).getImagem());
			imgview.setImage(image);
			break;
		}
	}

}

	

