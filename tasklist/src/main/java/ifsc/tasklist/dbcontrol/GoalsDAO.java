package ifsc.tasklist.dbcontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import ifsc.tasklist.dbentities.Goals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GoalsDAO implements DAO<Goals>{


	private static ObservableList<Goals> goals;
	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public Goals get(String id) {
		Goals goals = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("goals;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				goals = new Goals(splitResult[0], splitResult[1], splitResult[2], splitResult[3], Integer.valueOf(splitResult[4]));
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return goals;
	}

	@Override
	public List<Goals> getAll() throws UnknownHostException, IOException {
		goals = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("goals;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int GoalIndex = 0;
			while (GoalIndex < splitResult.length) {
				Goals goal = new Goals(splitResult[GoalIndex], splitResult[GoalIndex + 1], splitResult[GoalIndex + 2], 
						splitResult[GoalIndex + 3], Integer.valueOf(splitResult[GoalIndex + 4]));
				goals.add(goal);
				GoalIndex += 5;
			}
		}
		in.close();
		out.close();
		server.close();

		return goals;
	}

	public void change(Goals goal, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("goals;" + operation + ";" + goal.getNomeUser() + ";" + goal.getMetaTarefaCumprida() + ";" + goal.getMetaProjetoCumprida() + ";" + 
			goal.getMetaTPCumprida() + ";" + goal.getObjDiario());
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(Goals goal) {
		change(goal, "add");
	}

	@Override
	public void delete(Goals goal) {
		change(goal, "delete");
	}

	@Override
	public void update(Goals goal) {
		change(goal, "update");
	}
}
