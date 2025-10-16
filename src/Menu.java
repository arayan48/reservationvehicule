import java.util.Scanner;

public class Menu {

    public void afficherBienvenue() {
        System.out.println("===== BIENVENUE SUR LE SYSTEME DE RESERVATION DU CENTRE HOSPITALIER =====");
    }

    public void afficherMenu(Scanner scanner, Passerelle db) {
        boolean continuer = true;

        while (continuer) {
            clearConsole();
            afficherBienvenue();
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Faire une réservation");
            System.out.println("2. Vérifier la disponibilité d'un véhicule");
            System.out.println("3. Modifier une réservation");
            System.out.println("0. Quitter");

            System.out.print("Veuillez choisir une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    menuReservation(scanner);
                    break;
                case 2:
                    menuVerificationDisponibilite(scanner);
                    break;
                case 3:
                    modification(scanner);
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    public boolean menuConnexion(Scanner s, Passerelle db) {
        clearConsole();
        afficherBienvenue();

        System.out.println("===== CONNEXION =====");

        boolean connecte = false;

        while (!connecte) {
            System.out.print("Entrez votre matricule : ");
            int matricule = s.nextInt();
            s.nextLine();

            System.out.print("Entrez votre mot de passe : ");
            String mdp = s.nextLine();

            connecte = db.verifierConnexion(matricule, mdp);

            if (!connecte) {
                System.out.println("Veuillez réessayer.\n");
            }
        }

        return connecte;
    }

    public void menuReservation(Scanner s) {
        clearConsole();
        // Réservation des Véhicules
        System.out.println("\n===== RESERVATION =====");
        System.out.print("Combien de vehicules souhaitez-vous reserver ? ");
        System.out.println(); // ligne vide pour aérer
        // Ajout des véhicules
        // Avec type, heure, priorité etc ....

        // Simulation du processus de réservation
        // ... code de réservation ...

        System.out.println("\nReservation terminee !");
        System.out.println("\nAppuyez sur Entree pour revenir au menu principal...");
        s.nextLine();
    }

    public void menuVerificationDisponibilite(Scanner s) {
        clearConsole();
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

        System.out.println("\nVerification terminee !");
        System.out.println("\nAppuyez sur Entree pour revenir au menu principal...");
        s.nextLine();
    }

    public void menuModification(Scanner s) {
        clearConsole();
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

        System.out.println("\nModification terminee !");
        System.out.println("\nAppuyez sur Entree pour revenir au menu principal...");
        s.nextLine();
    }

    public void menuResumeFinal() {
        // Message Final
        System.out.println("\n===== RESUME FINAL =====");
        System.out.println("Vehicules reserves :");
        // Affichage du nombre de Véhicules via To STRING

        System.out.println("\nMerci d avoir utilise notre service de reservation !");
        System.out.println(" Au revoir !");
    }

    public void modification(Scanner s) {
        clearConsole();
        System.out.println("\n===== MODIFIER UNE RESERVATION =====");
        // Affiche toute la table demande
        // Exemple
        System.out.println("Que souhaitez vous faire ? Demander : 1 Valider : 2  Annuler : 3");

        // Appel de la methode en fonction du switch case
        System.out.println("Votre reservation a été modifiée");
        // OU
        System.out.println("Aucune réservation n'as été modifié");

        System.out.println("\nModification terminee !");
        System.out.println("\nAppuyez sur Entree pour revenir au menu principal...");
        s.nextLine();
    }

    public static void clearConsole() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

}
