module com.graph {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.graph to javafx.fxml;
    exports com.graph;
}
