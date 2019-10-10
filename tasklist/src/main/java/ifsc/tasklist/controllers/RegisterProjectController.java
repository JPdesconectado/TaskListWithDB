package ifsc.tasklist.controllers;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterProjectController {
	private ObservableList<String> title = FXCollections
	        .observableArrayList();
	private ObservableList<String> items = FXCollections
	        .observableArrayList();
	
	@FXML
	TextField titulo;
	
	@FXML
	TextField tarefa;
	
	@FXML
	DatePicker data;
	
	@FXML
	Button btVoltar;
	
	@FXML
	Button btAdicionar;
	
	@FXML
	
	public void novoProjeto() {
		LocalDate ld = data.getValue();	
		if (!title.contains(titulo.getText())) {
			title.add(titulo.getText());
			}
		if(!items.contains(tarefa.getText())) {
			items.addAll(tarefa.getText(), ld.toString());
		}
		voltar();
	}
	
	public void voltar() {
		Stage janela = (Stage) btVoltar.getScene().getWindow();
		janela.close();
	}
	
	public ObservableList<String> getTitle() {
		return this.title;
	}
	
	public ObservableList<String> getItems(){
		return this.items;
	}
	
	public void setItems(ObservableList<String> items) {
		this.items = items;
		
	}
	
	public void setTitle(ObservableList<String> title) {
		this.title = title;
	}
	
	
}
