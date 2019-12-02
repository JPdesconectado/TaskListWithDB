package ifsc.tasklist.dbcontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import ifsc.tasklist.dbentities.Feedback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FeedbackDAO implements DAO<Feedback>{

	private static ObservableList<Feedback> feedback;
	private String ipServer = "localhost";
	private int portServer = 1024;

	
	@Override
	public Feedback get(String id) {
		Feedback fb = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("feedback;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				fb = new Feedback(splitResult[0], Integer.valueOf(splitResult[1]), Boolean.valueOf(splitResult[2]), splitResult[3]);
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return fb;
	}

	@Override
	public List<Feedback> getAll() throws UnknownHostException, IOException {
		feedback = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("feedback;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int FBIndex = 0;
			while (FBIndex < splitResult.length) {
				Feedback fb = new Feedback(splitResult[FBIndex], Integer.valueOf(splitResult[FBIndex + 1]), Boolean.valueOf(splitResult[FBIndex + 2]), 
						splitResult[FBIndex + 3]);
				feedback.add(fb);
				FBIndex += 4;
			}
		}
		in.close();
		out.close();
		server.close();

		return feedback;
	}
	
	public void change(Feedback fb, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("feedback;" + operation + ";" + fb.getUser() + ";" + fb.getNota() + ";" + fb.isGostou() + ";" + fb.getComentario());
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(Feedback obj) {
		change(obj, "add");
		
	}

	@Override
	public void delete(Feedback obj) {
		change(obj, "delete");
		
	}

	@Override
	public void update(Feedback obj) {
		change(obj, "update");
		
	}

}
