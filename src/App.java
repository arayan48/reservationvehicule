import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Initialisation
        Scanner s = new Scanner(System.in);
        Connection db = new Connection();
        Passerelle passerelle = new Passerelle();

        boolean connecte = passerelle.menuConnexion(s, db);

        



        // Fermeture des ressources
        s.close();
        db.closeConnection();
    }
}
