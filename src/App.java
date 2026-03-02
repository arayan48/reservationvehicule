import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║   SYSTÈME DE RÉSERVATION DE VÉHICULES - CENTRE HOSPITALIER    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Initialisation
        Scanner s = new Scanner(System.in);
        Passerelle db = null;
        boolean connexionReussie = false;

        // Boucle de tentative de connexion
        while (!connexionReussie) {
            db = new Passerelle();

            // Vérifier que la connexion BD est établie
            if (db.getConnection() == null) {
                System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
                System.out.println("║              PROBLÈME DE CONNEXION À LA BASE                   ║");
                System.out.println("╚════════════════════════════════════════════════════════════════╝");
                System.out.println("\nQue voulez-vous faire ?");
                System.out.println("  1. Réessayer la connexion");
                System.out.println("  2. Afficher les paramètres de connexion");
                System.out.println("  3. Quitter l'application");
                System.out.print("\nVotre choix : ");

                String choix = s.nextLine().trim();

                switch (choix) {
                    case "1":
                        System.out.println("\n🔄 Nouvelle tentative de connexion...\n");
                        continue;
                    case "2":
                        afficherParametresConnexion();
                        System.out.println("\nAppuyez sur Entrée pour continuer...");
                        s.nextLine();
                        continue;
                    case "3":
                    default:
                        System.out.println("\n👋 Au revoir !");
                        s.close();
                        return;
                }
            } else {
                connexionReussie = true;
            }
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

    private static void afficherParametresConnexion() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║           PARAMÈTRES DE CONNEXION ACTUELS                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println("\n📋 Configuration dans src/Passerelle.java :");
        System.out.println("   URL      : jdbc:postgresql://192.168.1.24:5432/slam_reservation_vehicule");
        System.out.println("   Serveur  : 192.168.1.24");
        System.out.println("   Port     : 5432");
        System.out.println("   Base     : slam_reservation_vehicule");
        System.out.println("   User     : Rayan");
        System.out.println("   Password : Rayan789");
        System.out.println("\n💡 Pour modifier :");
        System.out.println("   1. Éditez le fichier : src/Passerelle.java");
        System.out.println("   2. Modifiez les valeurs url, user, passwd");
        System.out.println("   3. Recompilez : bash compile.sh");
        System.out.println("   4. Relancez : bash run.sh");
        System.out.println("\n🧪 Pour tester la connexion PostgreSQL :");
        System.out.println("   psql -h 192.168.1.24 -p 5432 -U Rayan -d slam_reservation_vehicule");
    }
}
