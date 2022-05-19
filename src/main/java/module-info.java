module mediaplayer {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.javafx.mediaplayer to javafx.fxml;
    exports com.javafx.mediaplayer;
}