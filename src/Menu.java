import java.time.LocalDate;
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
            System.out.println("4. Voir mes réservations");
            System.out.println("0. Quitter");

            System.out.print("Veuillez choisir une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    menuReservation(scanner, db, "" + db.getMatriculeConnecte());
                    break;
                case 2:
                    menuVerificationDisponibilite(scanner, db);
                    break;
                case 3:
                    menuModification(scanner, db);
                    break;
                case 4:
                    db.afficherMesReservations("" + db.getMatriculeConnecte());
                    System.out.println("\nAppuyez sur Entrée pour continuer...");
                    scanner.nextLine();
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

    public void menuReservation(Scanner s, Passerelle db, String matriculeConnecte) {
        clearConsole();
        System.out.println("\n===== NOUVELLE RESERVATION =====");

        try {
            // Date de réservation (aujourd'hui)
            System.out.print("Date de réservation (AAAA-MM-JJ) [Entrée = aujourd'hui] : ");
            String dateReservStr = s.nextLine();
            String dateReserv = dateReservStr.isBlank() ? LocalDate.now().toString() : dateReservStr;

            // Date de début
            System.out.print("Date de début d'utilisation (AAAA-MM-JJ) : ");
            String dateDebut = s.nextLine();

            // Durée
            System.out.print("Durée de la réservation (en jours) : ");
            int duree = s.nextInt();
            s.nextLine();

            // Afficher les types disponibles
            System.out.println("\n--- Types de véhicules disponibles ---");
            db.afficherTypes();
            System.out.print("\nNuméro du type de véhicule : ");
            String noType = s.nextLine();

            // Afficher les véhicules disponibles de ce type
            System.out.println("\n--- Véhicules disponibles ---");
            db.afficherVehiculesParType(noType);
            System.out.print("\nImmatriculation du véhicule : ");
            String immat = s.nextLine();

            // Récupérer la personne connectée
            Personne personne = db.recupererPersonneParMatricule(matriculeConnecte);
            if (personne == null) {
                System.out.println("\nERREUR - Impossible de récupérer vos informations.");
                return;
            }

            // Récupérer le véhicule
            Vehicule vehicule = db.recupererVehiculeParImmat(immat);
            if (vehicule == null) {
                System.out.println("\nERREUR - Véhicule introuvable.");
                return;
            }

            // Créer la demande
            Demande nouvelleDemande = new Demande(
                    dateReserv,
                    0, // numero auto-généré
                    dateDebut,
                    personne,
                    noType,
                    vehicule,
                    duree,
                    null, // pas encore retourné
                    "En attente");

            // Enregistrer la demande
            if (nouvelleDemande.faireDemande(db)) {
                System.out.println("\n✅ Réservation créée avec succès !");
                System.out.println("Véhicule : " + vehicule.getMarque() + " " + vehicule.getModele());
                System.out.println("Du " + dateDebut + " pour " + duree + " jour(s)");
            } else {
                System.out.println("\n❌ Erreur lors de la création de la réservation.");
            }

        } catch (Exception e) {
            System.out.println("\n❌ ERREUR - " + e.getMessage());
        }

        System.out.println("\nAppuyez sur Entrée pour revenir au menu principal...");
        s.nextLine();
    }

    public void menuVerificationDisponibilite(Scanner s, Passerelle db) {
        // Vérification de la disponibilité du véhicule
        System.out.println("\n===== VERIFICATION DE DISPONIBILITE =====");
        // Fonction de vérif de la base de données
        // Afin de vérifier si le véhicule est présent et disponible ou bien pris

        // Demande des informations au utilisateur
        System.out.print("Entrez la marque du véhicule : ");
        String marque = s.nextLine();

        System.out.print("Entrez le modèle du véhicule : ");
        String modele = s.nextLine();

        System.out.print("Entrez le numéro du type : ");
        int numType = s.nextInt();
        s.nextLine(); // consomme le retour à la ligne

        System.out.print("Entrez l'immatriculation du véhicule : ");
        String immat = s.nextLine();

        // Création de l'objet Type à partir du numéro saisi
        Type unType = db.recupererTypeParNumero(numType);

        if (unType == null) {
            System.out.println("\nERREUR : le type de véhicule saisi n'existe pas.");
            System.out.println("Appuyez sur Entrée pour revenir au menu principal...");
            s.nextLine();
            return; // quitte la méthode
        }

        // Appel de la fonction de vérification de réservation
        boolean existe = db.verifierReservation(marque, modele, unType, immat);

        // Si le véhicule est dispo
        if (!existe) {
            System.out.println("Ce vehicule " + modele + " est disponible.");

            System.out.println("\nVotre reservation a ete validee pour les vehicules suivants :");
            // TO STRING des véhicules les affichant en réservation
            // Avec le nom, le prénom et les véhicules pour telle heure
        } else {
            System.out.println("Desole ce vehicule " + modele + " n est pas disponible.");
            // Autrement

            // Refaire la demande au besoin
            System.out.println("Aucun vehicule disponible. Merci de reessayer plus tard.");
        }

        System.out.println("\nVerification terminee !");
        System.out.println("\nAppuyez sur Entree pour revenir au menu principal...");
        s.nextLine();
    }

    public void menuModification(Scanner s, Passerelle db) {
        clearConsole();
        System.out.println("\n===== MODIFIER UNE RESERVATION =====");

        try {
            System.out.print("Numéro de la réservation : ");
            int numero = s.nextInt();
            s.nextLine();

            System.out.print("Date de la réservation (AAAA-MM-JJ) : ");
            LocalDate datereserv = LocalDate.parse(s.nextLine());

            if (!db.reservationExiste(numero, datereserv)) {
                System.out.println("\n❌ Aucune réservation trouvée avec ce numéro et cette date !");
            } else {
                System.out.println("\n✅ Réservation trouvée. Saisissez les champs à modifier :");
                System.out.print("Nouvelle date de début (AAAA-MM-JJ) : ");
                LocalDate dateDebut = LocalDate.parse(s.nextLine());

                System.out.print("Matricule du personnel : ");
                String matricule = s.nextLine();

                System.out.print("Numéro du type : ");
                int noType = s.nextInt();
                s.nextLine();

                System.out.print("Immatriculation du véhicule : ");
                String immat = s.nextLine();

                System.out.print("Durée (jours) : ");
                int duree = s.nextInt();
                s.nextLine();

                System.out.print("Date retour effectif (AAAA-MM-JJ ou vide) : ");
                String dateRetourStr = s.nextLine();
                LocalDate dateRetourEffectif = dateRetourStr.isBlank() ? null : LocalDate.parse(dateRetourStr);

                System.out.print("État de la réservation : ");
                String etat = s.nextLine();

                // Appel à la passerelle
                db.modifierReservation(numero, datereserv, dateDebut, matricule, noType, immat, duree,
                        dateRetourEffectif, etat);
            }
        } catch (Exception e) {
            System.out.println("\n❌ ERREUR - " + e.getMessage());
        }

        System.out.println("\nAppuyez sur Entrée pour revenir au menu principal...");
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

    public static void clearConsole() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

}
