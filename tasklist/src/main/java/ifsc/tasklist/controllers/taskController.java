package ifsc.tasklist.controllers;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class taskController {
	private ObservableList<String> list = FXCollections.observableArrayList();
	private ObservableList<String> title = FXCollections.observableArrayList();
	@FXML
	DatePicker dp;

	@FXML
	TextField txtTitle;

	@FXML
	TextArea txtDescription;
	
	@FXML
	Button btAdicionar;

	@FXML
	Button btVoltar;

	@FXML
	
	public ObservableList<String> getList() {
		return this.list;
	}
	
	public ObservableList<String> getTitle() {
		return this.title;
	}
	
	public void setTitle(ObservableList<String> title) {
		this.title = title;
	}

	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
	
	public void adicionar() {
		LocalDate data = dp.getValue();
		if (!title.contains(txtTitle.getText())) {
			title.add(txtTitle.getText());
			}
		list.addAll(txtDescription.getText(), data.toString());
		voltar();
	}
	
	public void setList(ObservableList<String> list) {
		this.list = list;
	}
}
