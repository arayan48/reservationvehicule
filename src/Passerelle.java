import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passerelle {
    private String url = "jdbc:postgresql://192.168.1.46:5432/slam_reservation_vehicule";
    private String user = "rayan";
    private String passwd = "Rayan789";
    private java.sql.Connection conn;

    public Passerelle() {
        try {
            this.conn = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
        }
    }

    public java.sql.Connection getConnection() {
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

    public boolean verifierConnexion(int matricule, String mdp) {
        try {
            PreparedStatement stmt = conn
                    .prepareStatement("SELECT nom, prenom FROM personne WHERE matricule = ?  AND mdp = ?");
            stmt.setInt(1, matricule);
            stmt.setString(2, mdp);

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

    
    //Fonction crée pour récupérer le numéro du type afin de fludifier la vérification de la réservation
    //Réutilisable pour afficher le numero du type dans le cas de la réservation ou de la modif si besoin
    public Type recupererTypeParNumero(int numero) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT noType, libelle FROM type WHERE noType = ?");
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Type(rs.getInt("noType"), rs.getString("libelle"));
            }
        } catch (Exception e) {
            System.out.println("ERREUR récupération Type : " + e.getMessage());
        }
        return null;
    }
    
    //Fonction de Validation du Véhicule
    public boolean verifierReservation(String marque, String modele, Type unType, int immat){
        boolean verif=false;
        try {
            PreparedStatement stmt = conn
                    .prepareStatement("SELECT COUNT(*) FROM vehicule  WHERE marque = ?  AND modele = ? AND noType = ? AND immat = ? EXIST ");
            stmt.setString(1, marque);
            stmt.setString(2, modele);
            stmt.setInt(3, unType.getNumero());
            stmt.setInt(4, immat);
            
            ResultSet rs = stmt.executeQuery();
            
           // Vérifie si la première (et seule) ligne de résultat existe ET si le compte est supérieur à 0
            if (rs.next() && rs.getInt(1) > 0) { 
                System.out.println("ERREUR - Validation non possible - Véhicule déjà existant - Choisissez un autre véhicule ");
                verif = true; 
            } else {
                System.out.println("OK - Validation de la Réservation");
                verif = false;
            }
            return verif;
        } catch (Exception e) {
            System.out.println("ERREUR - " + e.getMessage());
            return verif;
        }
    }
}
