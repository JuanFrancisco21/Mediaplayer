module Controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;


    opens Controller to java.sql,java.xml.bind,javafx.fxml;
    opens Model to java.sql,java.xml.bind,javafx.fxml,Utils;
    opens Model.DAO to java.sql,java.xml.bind,javafx.fxml;
    opens Utils to Controller,Model;
    exports Controller;
    exports Model;
    exports Model.DAO;
    exports Utils;
    
}
