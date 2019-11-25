package ifsc.tasklist.dbcontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import ifsc.tasklist.dbentities.TarefaProjeto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TarefaProjetoDAO implements DAO<TarefaProjeto> {

	private static ObservableList<TarefaProjeto> tps;
	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public TarefaProjeto get(String id) {
		TarefaProjeto tp = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("tarefaprojeto;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				tp = new TarefaProjeto(splitResult[0],  splitResult[1], splitResult[2],  splitResult[3]);
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return tp;
	}

	@Override
	public List<TarefaProjeto> getAll() throws UnknownHostException, IOException {
		tps = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("tarefaprojeto;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int userIndex = 0;
			while (userIndex < splitResult.length) {
				TarefaProjeto tp = new TarefaProjeto(splitResult[userIndex], splitResult[userIndex + 1],  splitResult[userIndex + 2],  splitResult[userIndex + 3]);
				tps.add(tp);
				userIndex += 4;
			}
		}
		in.close();
		out.close();
		server.close();

		return tps;
	}

	public void change(TarefaProjeto tp, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("tarefaprojeto;" + operation + ";" + tp.getTitulo() + ";" + tp.getDescricao() + ";" + tp.getProjeto() + ";" + tp.getData());
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(TarefaProjeto tp) {
		change(tp, "add");
	}

	@Override
	public void delete(TarefaProjeto tp) {
		change(tp, "delete");
	}

	@Override
	public void update(TarefaProjeto tp) {
		change(tp, "update");
	}
}