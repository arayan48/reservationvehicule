import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Initialisation
        Scanner s = new Scanner(System.in);
        Connection db = new Connection();
        Passerelle passerelle = new Passerelle();

        // Affichage du message de bienvenue
        passerelle.afficherBienvenue();

        // Menu de connexion avec boucle
        boolean connecte = passerelle.menuConnexion(s, db);



        // Fermeture des ressources
        s.close();
        db.closeConnection();
    }
}
