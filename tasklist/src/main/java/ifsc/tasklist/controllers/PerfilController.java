package ifsc.tasklist.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import ifsc.tasklist.App;
import ifsc.tasklist.dbcontrol.UserDAO;
import ifsc.tasklist.dbentities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
	JFXButton btVoltar;
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
	TextField txtName;
	
	@FXML
	TextField txtEmail;
	
	@FXML
	TextField txtSenha;
	
	FileChooser fc = new FileChooser();
	String senha;
	@FXML
	public void addFoto() {
		Stage janela = (Stage) btAdd.getScene().getWindow();
		File file = fc.showOpenDialog(janela);
		if (file != null) {
			Image image = new Image(file.toURI().toString());
			imgview.setImage(image);
			User user = new User(txtName.getText(), txtEmail.getText(), senha, file.toURI().toString());
			new UserDAO().update(user);
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			List<User> users = new UserDAO().getAll();
			for (int i = 0; i < users.size(); i++) {
				if(users.get(i).getUsuario().equals(BeginController.name)) {
					txtName.setText(users.get(i).getUsuario());
					txtEmail.setText(users.get(i).getEmail());
					txtSenha.setText("*******");
					senha = users.get(i).getSenha();
					Image image = new Image(users.get(i).getImagem());
					imgview.setImage(image);
					break;
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
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
	
	@FXML
	public void editEmail() {
		if(!txtEmail.isEditable()) {
			txtEmail.setEditable(true);
		}else {
			User user = new User(txtName.getText(), txtEmail.getText(), senha, imgview.getImage().getUrl());
			new UserDAO().update(user);
		}
		
	}
	
	@FXML
	public void editSenha() {
		if(!txtSenha.isEditable()) {
			txtSenha.setEditable(true);
		}else {
			User user = new User(txtName.getText(), txtEmail.getText(), txtSenha.getText(), imgview.getImage().getUrl());
			new UserDAO().update(user);
			txtSenha.setText("*******");
		}
		
	}
	
	@FXML
	public void deletar() throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("deleteAccount.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();

	}
		
}


	

