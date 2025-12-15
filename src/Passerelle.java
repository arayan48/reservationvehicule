import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class Passerelle {
    private String url = "jdbc:postgresql://192.168.1.245:5432/slam2026_AP_rayanayyoubaymane";
    private String user = "sbai";
    private String passwd = "sbaisbai";
    private java.sql.Connection conn;
    private int matriculeConnecte = 0;

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
                this.matriculeConnecte = matricule;
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

    // Fonction crée pour récupérer le numéro du type afin de fludifier la
    // vérification de la réservation
    // Réutilisable pour afficher le numero du type dans le cas de la réservation ou
    // de la modif si besoin
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

    public void modifierReservation(int numero, LocalDate datereserv,
            LocalDate dateDebut, String matricule,
            int noType, String immat, int duree,
            LocalDate dateRetourEffectif, String etat) {

        try {
            PreparedStatement stmt = conn
                    .prepareStatement(
                            "UPDATE demande SET datedebut = ?, matricule = ?, notype = ?, immat = ?, duree = ?, dateretoureffectif = ?, etat = ? WHERE numero = ? AND datereserv = ?");
            stmt.setDate(1, java.sql.Date.valueOf(dateDebut));
            stmt.setString(2, matricule);
            stmt.setInt(3, noType);
            stmt.setString(4, immat);
            stmt.setInt(5, duree);
            if (dateRetourEffectif != null) {
                stmt.setDate(6, java.sql.Date.valueOf(dateRetourEffectif));
            } else {
                stmt.setNull(6, Types.DATE);
            }
            stmt.setString(7, etat);
            stmt.setInt(8, numero);
            stmt.setDate(9, java.sql.Date.valueOf(datereserv));

            int lignes = stmt.executeUpdate();
            if (lignes > 0) {
                System.out.println("\n✅ Réservation modifiée avec succès !");
            } else {
                System.out.println("\n❌ Aucune réservation trouvée avec ce couple numéro/date.");
            }
        } catch (SQLException e) {
            System.out.println("ERREUR SQL lors de la modification - " + e.getMessage());
        }
    }

    // Fonction de Validation du Véhicule
    public boolean verifierReservation(String marque, String modele, Type unType, String immat) {
        boolean verif = false;
        try {
            PreparedStatement stmt = conn
                    .prepareStatement(
                            "SELECT COUNT(*) FROM vehicule  WHERE marque = ?  AND modele = ? AND noType = ? AND immat = ? EXIST ");
            stmt.setString(1, marque);
            stmt.setString(2, modele);
            stmt.setInt(3, unType.getNumero());
            stmt.setString(4, immat);

            ResultSet rs = stmt.executeQuery();

            // Vérifie si la première (et seule) ligne de résultat existe ET si le compte
            // est supérieur à 0
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println(
                        "ERREUR - Validation non possible - Véhicule déjà existant - Choisissez un autre véhicule ");
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

    public boolean reservationExiste(int numero, LocalDate datereserv) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COUNT(*) FROM demande WHERE nodemande = ? AND datereserv = ?");
            stmt.setInt(1, numero);
            stmt.setObject(2, datereserv);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERREUR - " + e.getMessage());
            return false;
        }
    }

    /**
     * Insère une nouvelle demande de réservation dans la base de données
     * 
     * @return true si l'insertion a réussi, false sinon
     */
    public boolean insererDemande(String datereserv, String datedebut, String matricule,
            String notype, String immat, int duree, String etat) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO demande (datereserv, datedebut, matricule, notype, immat, duree, etat) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setDate(1, java.sql.Date.valueOf(datereserv));
            stmt.setDate(2, java.sql.Date.valueOf(datedebut));
            stmt.setString(3, matricule);
            stmt.setString(4, notype);
            stmt.setString(5, immat);
            stmt.setInt(6, duree);
            stmt.setString(7, etat);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("ERREUR lors de l'insertion de la demande - " + e.getMessage());
            return false;
        }
    }

    public int getMatriculeConnecte() {
        return this.matriculeConnecte;
    }

    /**
     * Affiche tous les types de véhicules disponibles
     */
    public void afficherTypes() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT noType, libelle FROM type ORDER BY noType");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("  " + rs.getInt("noType") + " - " + rs.getString("libelle"));
            }
        } catch (Exception e) {
            System.out.println("ERREUR - " + e.getMessage());
        }
    }

    /**
     * Affiche les véhicules d'un type donné
     */
    public void afficherVehiculesParType(String noType) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT immat, marque, modele FROM vehicule WHERE notype = ? ORDER BY marque, modele");
            stmt.setString(1, noType);
            ResultSet rs = stmt.executeQuery();

            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                System.out.println("  " + rs.getString("immat") + " - " +
                        rs.getString("marque") + " " + rs.getString("modele"));
            }

            if (!trouve) {
                System.out.println("  Aucun véhicule de ce type.");
            }
        } catch (Exception e) {
            System.out.println("ERREUR - " + e.getMessage());
        }
    }

    /**
     * Récupère une personne par son matricule
     */
    public Personne recupererPersonneParMatricule(String matricule) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT matricule, nom, telephone FROM personne WHERE matricule = ?");
            stmt.setString(1, matricule);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Personne(
                        rs.getString("matricule"),
                        rs.getString("nom"),
                        rs.getString("telephone"));
            }
        } catch (Exception e) {
            System.out.println("ERREUR récupération Personne - " + e.getMessage());
        }
        return null;
    }

    /**
     * Récupère un véhicule par son immatriculation
     */
    public Vehicule recupererVehiculeParImmat(String immat) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT immat, marque, modele FROM vehicule WHERE immat = ?");
            stmt.setString(1, immat);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vehicule(
                        rs.getString("immat"),
                        rs.getString("marque"),
                        rs.getString("modele"));
            }
        } catch (Exception e) {
            System.out.println("ERREUR récupération Véhicule - " + e.getMessage());
        }
        return null;
    }

    /**
     * Affiche toutes les réservations d'un utilisateur
     */
    public void afficherMesReservations(String matricule) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT d.numero, d.datereserv, d.datedebut, d.duree, d.etat, " +
                            "v.marque, v.modele, v.immat " +
                            "FROM demande d " +
                            "JOIN vehicule v ON d.immat = v.immat " +
                            "WHERE d.matricule = ? " +
                            "ORDER BY d.datedebut DESC");
            stmt.setString(1, matricule);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n=== VOS RÉSERVATIONS ===");
            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                System.out.println("\nRéservation N°" + rs.getInt("numero"));
                System.out.println("  Date réservation : " + rs.getDate("datereserv"));
                System.out.println("  Date début       : " + rs.getDate("datedebut"));
                System.out.println("  Durée            : " + rs.getInt("duree") + " jour(s)");
                System.out.println("  Véhicule         : " + rs.getString("marque") + " " +
                        rs.getString("modele") + " (" + rs.getString("immat") + ")");
                System.out.println("  État             : " + rs.getString("etat"));
            }

            if (!trouve) {
                System.out.println("Aucune réservation trouvée.");
            }
        } catch (Exception e) {
            System.out.println("ERREUR - " + e.getMessage());
        }
    }
}
