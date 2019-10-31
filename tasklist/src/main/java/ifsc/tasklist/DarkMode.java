package ifsc.tasklist;

import javafx.scene.Scene;

public class DarkMode {
	
	public static boolean escolha = false;
	
	public static void dm(Scene scene){
		
		if(escolha == true){
		scene.getStylesheets().add("dark-theme.css");
		
		}else {
			scene.getStylesheets().remove("dark-theme.css");
		}
	}
}
