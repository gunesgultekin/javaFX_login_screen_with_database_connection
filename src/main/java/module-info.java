module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.kordamp.ikonli.javafx;
    requires mysql.connector.j;
    requires java.sql;

    opens org.example to javafx.fxml;
    exports org.example;
}
