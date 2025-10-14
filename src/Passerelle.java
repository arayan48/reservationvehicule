import java.util.Scanner;

public class Passerelle {

    public void afficherBienvenue() {
        System.out.println("===== BIENVENUE SUR LE SYSTEME DE RESERVATION DU CENTRE HOSPITALIER =====");
    }

    public boolean menuConnexion(Scanner s, Connection db) {
        System.out.println("===== CONNEXION =====");

        boolean connecte = false;

        // Boucle de connexion - redemande tant que l'utilisateur n'est pas connecté
        while (!connecte) {
            System.out.print("Entrez votre matricule : ");
            int matricule = s.nextInt();
            s.nextLine(); // Consomme le retour à la ligne

            System.out.print("Entrez votre mot de passe : ");
            String mdp = s.nextLine();

            // Vérifier la connexion
            connecte = db.verifierConnexion(matricule, mdp);

            if (!connecte) {
                System.out.println("Veuillez réessayer.\n");
            }
        }

        return connecte;
    }

    public void menuReservation(Scanner s) {
        // Réservation des Véhicules
        System.out.println("\n===== RESERVATION =====");
        System.out.print("Combien de vehicules souhaitez-vous reserver ? ");
        System.out.println(); // ligne vide pour aérer
        // Ajout des véhicules
        // Avec type, heure, priorité etc ....
    }

    public void menuVerificationDisponibilite() {
        // Vérification de la disponibilité du véhicule
        System.out.println("\n===== VERIFICATION DE DISPONIBILITE =====");
        // Fonction de vérif de la base de données
        // Afin de vérifier si le véhicule est présent et disponible ou bien prit

        // Si le véhicule est dipso
        System.out.println("Ce vehicule " + " est disponible.");

        System.out.println("\nVotre reservation a ete validee pour les vehicules suivants :");
        // TO STRING des véhicules les affichant en réservation
        // Avec le nom, le prénom et les véhicules pour telle heure

        System.out.println("Desole ce vehicule " + " n est pas disponible.");
        // Autrement

        // Refaire la demande au besoin
        System.out.println("Aucun vehicule disponible. Merci de reessayer plus tard.");
    }

    public void menuModification(Scanner s) {
        // Demande de modification de la réservation
        // Choix de la modification
        System.out.println("\nSouhaitez-vous modifier votre reservation ? (oui/non)");

        // Si la personne souhaite modifier
        // activer la fonction de modification des choix
        // que ça soit au niveau du véhicule
        // ou de l'heure de réservation

        // Si le choix est fait
        System.out.println("\n Reservation mise a jour !");
        // Ou aucun choix
        System.out.println("Aucune modification effectuee.");
    }

    public void menuResumeFinal() {
        // Message Final
        System.out.println("\n===== RESUME FINAL =====");
        System.out.println("Vehicules reserves :");
        // Affichage du nombre de Véhicules via To STRING

        System.out.println("\nMerci d avoir utilise notre service de reservation !");
        System.out.println(" Au revoir !");
    }

    // Méthodes existantes conservées
    public void connect() {

    }

    public void demande() {

    }

    public void validation() {

    }

    public void modification() {

    }

}
