module ifsc.tasklist {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;

    opens ifsc.tasklist to javafx.fxml;
    exports ifsc.tasklist;
}