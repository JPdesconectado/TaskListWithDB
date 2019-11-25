package ifsc.tasklist.dbcontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import ifsc.tasklist.dbentities.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectDAO implements DAO<Project> {

	private static ObservableList<Project> projects;
	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public Project get(String id) {
		Project project = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("project;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				project = new Project(splitResult[0], splitResult[1]);
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return project;
	}

	@Override
	public List<Project> getAll() throws UnknownHostException, IOException {
		projects = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("project;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int ProjectIndex = 0;
			while (ProjectIndex < splitResult.length) {
				Project project = new Project(splitResult[ProjectIndex], splitResult[ProjectIndex + 1]);
				projects.add(project);
				ProjectIndex += 2;
			}
		}
		in.close();
		out.close();
		server.close();

		return projects;
	}

	public void change(Project project, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("project;" + operation + ";" + project.getTitulo() + ";" + project.getObjetivo());
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(Project project) {
		change(project, "add");
	}

	@Override
	public void delete(Project project) {
		change(project, "delete");
	}

	@Override
	public void update(Project project) {
		change(project, "update");
	}
}
