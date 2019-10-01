package ifsc.tasklist;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Stage;

public class ProjetoController {

	@FXML
	Button btNewProject;
	
	@FXML
	Button btVoltar;
	
	@FXML
	ListView<String> projectview;
	
	private ObservableList<String> title = FXCollections.observableArrayList();
	private ObservableList<String> items = FXCollections.observableArrayList();
	
	public void novoProjeto() throws IOException{
		Stage stage = new Stage();
		projectview.setEditable(true);
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("newproject.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		newProjectController controller = fxmlLoader.getController();
		controller.setItems(items);
		controller.setTitle(title);
		items = controller.getItems();
		title = controller.getTitle();
		projectview.setItems(title);
		projectview.setCellFactory(ComboBoxListCell.forListView(items));
		stage.show();
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
}
