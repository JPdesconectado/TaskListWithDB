package ifsc.tasklist.controllers;

import java.io.IOException;
import java.time.LocalDate;

import ifsc.tasklist.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Stage;

public class PrincipalController {
	LocalDate data;
	
	@FXML
	Button btConfig;
	
	@FXML
	Button btHelp;
	
	@FXML
	Button btProject;
	
	@FXML
	Button btGoals;
	
	@FXML
	Button btNotification;
	
	@FXML
	Button btSearch;
	
	@FXML
	Button btNewTask;
	
	@FXML
	TextField txtSearch;

	@FXML
	ListView<String> listview;
	
	private ObservableList<String> list = FXCollections.observableArrayList();;
	private ObservableList<String> title = FXCollections.observableArrayList();
	
	public void novaTarefa() throws IOException {
		Stage stage = new Stage();
		listview.setEditable(true);
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("task.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		taskController controller = fxmlLoader.getController();
		controller.setList(list);
		controller.setTitle(title);
		title = controller.getTitle();
		list = controller.getList();
		listview.setItems(title);
		listview.setCellFactory(ComboBoxListCell.forListView(list));
		stage.show();
		
	}
 	
	public void irProjeto() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("projeto.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void pesquisaTarefa() {
		if(!txtSearch.getText().equals("")) {
			listview.setItems(getPesquisa());
		}else {
			listview.setItems(list);
		}
		
	}
 	
	public void irConfig() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("config.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void irMetas() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("metas.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void irNotification() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("notificacao.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void irAjuda() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ajuda.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	private ObservableList<String> getPesquisa(){
		ObservableList<String> encontrado = FXCollections.observableArrayList();
		for (String s: list) {
			if (s.equals(txtSearch.getText())) {
				encontrado.add(s);
			}
		}
		
		return encontrado;
	}
}

