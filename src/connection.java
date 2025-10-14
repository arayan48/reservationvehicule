import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection {
    private String url = "jdbc:postgresql://192.168.1.46:5432/slam_reservation_vehicule";
    private String user = "rayan";
    private String passwd = "Rayan789";
    private Connection conn;

    public Connection() {
        try {
            this.conn = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
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
        }
    }

    public boolean verifierConnexion(String nom, String prenom, String mdp) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT nom, prenom FROM personne WHERE nom = ? AND prenom = ? AND mdp = ?");
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, mdp);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("OK - Bonjour " + rs.getString("prenom") + " " + rs.getString("nom"));
                return true;
            } else {
                System.out.println("ERREUR - Mauvais identifiants");
                return false;
            }
        } catch (Exception e) {
            System.out.println("ERREUR - " + e.getMessage());
            return false;
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
