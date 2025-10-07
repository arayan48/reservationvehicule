import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
    String url = "jdbc:postgresql://192.168.1.46:5432/slam_reservation_vehicule";
    String user = "rayan";
    String passwd = "Rayan789";

    public connection() {
        try {
            Connection conn = DriverManager.getConnection(url, user, passwd);
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connexion effective !");
            } else {
                System.out.println("Échec de la connexion !");
            }
        } catch (Exception e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }
}
