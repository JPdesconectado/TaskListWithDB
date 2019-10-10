package ifsc.tasklist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
		Conn.getEntityManager().close();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
		Parent parent = fxmlLoader.load();
		scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		Conn.closeConn();
		super.stop();
    }

}