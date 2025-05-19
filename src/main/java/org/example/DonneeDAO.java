package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonneeDAO {
    public static void addDonnee(Donnee donnee) {

        try {
            Connection conn = Database.connect();

             PreparedStatement stmt = conn.prepareStatement("INSERT INTO donnees (date, valeur, unite) VALUES(?, ?, ?) ");
             stmt.setString(1, donnee.getDate());
             stmt.setFloat(2, donnee.getValeur());
             stmt.setString(3, donnee.getUnite());
             stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
