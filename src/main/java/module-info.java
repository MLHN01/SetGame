module com.setgame.setgame {
    requires java.naming;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql; // JDBC API für Datenbankinteraktion
    requires org.hibernate.orm.core; // Hibernate Core
    requires org.postgresql.jdbc; // PostgreSQL JDBC-Treiber
    requires jakarta.persistence; // Jakarta Persistence API

    // Öffnen von Paketen für JavaFX
    opens com.setgame.setgame to javafx.fxml, org.hibernate.orm.core;
    opens com.setgame.setgame.ui to javafx.fxml, org.hibernate.orm.core;
    opens com.setgame.setgame.ui.controller to javafx.fxml;
    opens com.setgame.setgame.util to org.hibernate.orm.core;

    // Hibernate benötigt Zugriff auf diese Pakete zur Laufzeit für Reflection

    exports com.setgame.setgame;
    exports com.setgame.setgame.ui;
    exports com.setgame.setgame.GameObjects;
    exports com.setgame.setgame.db_models;
    exports com.setgame.setgame.ui.controller;
    exports com.setgame.setgame.util;
    opens com.setgame.setgame.db_models to jakarta.persistence, javafx.fxml, org.hibernate.orm.core;

}
