import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║   SYSTÈME DE RÉSERVATION DE VÉHICULES - CENTRE HOSPITALIER    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Initialisation
        Scanner s = new Scanner(System.in);
        Passerelle db = new Passerelle();

        // Vérifier que la connexion BD est établie
        if (db.getConnection() == null) {
            System.err.println("\n❌ ARRÊT DE L'APPLICATION");
            System.err.println("Impossible de démarrer sans connexion à la base de données.");
            System.err.println("\nMerci de corriger le problème et relancer l'application.");
            s.close();
            return;
        }

        Menu menu = new Menu();

        boolean connecte = menu.menuConnexion(s, db);

        if (connecte) {
            menu.afficherMenu(s, db);
        } else {
            System.out.println("\n❌ Connexion annulée.");
        }

        // Fermeture des ressources
        System.out.println("\n👋 Merci d'avoir utilisé notre système. À bientôt !");
        s.close();
        db.closeConnection();
    }
}
