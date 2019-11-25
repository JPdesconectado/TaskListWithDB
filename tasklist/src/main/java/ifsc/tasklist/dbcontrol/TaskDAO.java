package ifsc.tasklist.dbcontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import ifsc.tasklist.dbentities.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskDAO implements DAO<Task> {

	private static ObservableList<Task> tasks;
	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public Task get(String id) {
		Task task = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("task;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				task = new Task(splitResult[0], splitResult[1], splitResult[2]);
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return task;
	}

	@Override
	public List<Task> getAll() throws UnknownHostException, IOException {
		tasks = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("task;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int taskIndex = 0;
			while (taskIndex < splitResult.length) {
				Task task = new Task(splitResult[taskIndex], splitResult[taskIndex + 1],
						splitResult[taskIndex + 2]);
				tasks.add(task);
				taskIndex += 3;
			}
		}
		in.close();
		out.close();
		server.close();

		return tasks;
	}

	public void change(Task task, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("task;" + operation + ";" + task.getTitulo() + ";" + task.getDescricao() + ";" + task.getData());
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(Task task) {
		change(task, "add");
	}

	@Override
	public void delete(Task task) {
		change(task, "delete");
	}

	@Override
	public void update(Task task) {
		change(task, "update");
	}
}
