package ifsc.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TarefaProjeto {

	@Id
	private String titulo;
	private String descricao;
	private String data;
	
	public TarefaProjeto() {
		
	}

	public TarefaProjeto(String titulo, String descricao, LocalDate data) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data = dtf.format(data);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}