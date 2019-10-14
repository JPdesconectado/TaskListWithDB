package ifsc.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {

	@Id
	private String titulo;
	
	private String tarefas;
	
	private String data;
	
	public Project() {
		
	}
	
	public Project(String titulo, String tarefas, LocalDate data) {
		super();
		this.titulo = titulo;
		this.tarefas = tarefas;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data = dtf.format(data);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getTarefas() {
		return tarefas;
	}

	public void setTarefas(String tarefas) {
		this.tarefas = tarefas;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((tarefas == null) ? 0 : tarefas.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (tarefas == null) {
			if (other.tarefas != null)
				return false;
		} else if (!tarefas.equals(other.tarefas))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Título: " + titulo + " Tarefas: " + tarefas + ". Para o dia: " + data + ".";
	}
	
	
}
