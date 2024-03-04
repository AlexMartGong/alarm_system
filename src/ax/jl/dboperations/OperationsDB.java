package ax.jl.dboperations;

import ax.jl.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperationsDB {

    private DatabaseConnection conn = new DatabaseConnection();

    public void insertDB(int id, int hour, int minute) {
        
        String sql = "INSERT INTO alarms (idalarm, hour, minute) VALUES (?, ?, ?)";
        try (Connection connection = conn.connectMySQL(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, hour);
            ps.setInt(3, minute);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OperationsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void deleteDB(int id) {
        
        String sql = "DELETE FROM alarms WHERE idalarm = ?";
        try (Connection connection = conn.connectMySQL(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OperationsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
