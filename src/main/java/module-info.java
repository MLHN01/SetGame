module com.setgame.setgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.setgame.setgame to javafx.fxml;
    exports com.setgame.setgame;

    opens com.setgame.setgame.ui to javafx.fxml;

}