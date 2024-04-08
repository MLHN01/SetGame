module com.setgame.setgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.setgame.setgame to javafx.fxml;
    exports com.setgame.setgame;
}