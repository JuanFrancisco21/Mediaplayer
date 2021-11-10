module Controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;


    opens Controller to java.sql,java.xml.bind,javafx.fxml;
    opens Model to java.sql,java.xml.bind,javafx.fxml;
    opens Model.DAO to java.sql,java.xml.bind,javafx.fxml;
    exports Controller;
}
