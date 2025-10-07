import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private String url = "jdbc:postgresql://192.168.1.46:5432/slam_reservation_vehicule";
    private String user = "rayan";
    private String passwd = "Rayan789";
    private Connection conn;

    public connection() {
        try {
            this.conn = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            // Connexion échouée, conn restera null
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Erreur lors de la fermeture, ignorée silencieusement
        }
    }

    @Override
    public String toString() {
        try {
            if (conn != null && !conn.isClosed()) {
                return "Connexion active : " + url + " | Utilisateur : " + user + " | Statut : CONNECTÉ";
            } else {
                return "Connexion inactive : " + url + " | Utilisateur : " + user + " | Statut : FERMÉE";
            }
        } catch (SQLException e) {
            return "Erreur de connexion : " + url + " | Utilisateur : " + user + " | Erreur : " + e.getMessage();
        }
    }
}
