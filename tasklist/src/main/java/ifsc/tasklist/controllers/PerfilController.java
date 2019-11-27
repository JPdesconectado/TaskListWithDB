package ifsc.tasklist.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import ifsc.tasklist.dbcontrol.UserDAO;
import ifsc.tasklist.dbentities.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PerfilController implements Initializable{

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
	
	ObservableList<User> listUser;
	
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
		UserDAO dao = new UserDAO();
		try {
			listUser.add((User) dao.getAll());
		} catch (Exception e) {
		}
		
	}
	

}
