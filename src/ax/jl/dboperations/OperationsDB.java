package ax.jl.dboperations;

import ax.jl.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperationsDB {

    private DatabaseConnection conn = new DatabaseConnection();
    private Connection connection = null;

    public void insertDB(int id, int hour, int minute) {
        PreparedStatement ps = null;
        try {
            connection = conn.connectMySQL();
            String sql = "INSERT INTO alarms (idalarm, hour, minute) VALUES (?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, hour);
            ps.setInt(3, minute);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OperationsDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OperationsDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

