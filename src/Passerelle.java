public class Passerelle {
    
    public void demande (){
        
    }
    
    public void validation (){
        
    }
    
    public void modification (){
        
    }

    public static void main(String[] args) {
        //Menu
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== BIENVENUE SUR LE SYSTEME DE RESERVATION DU CENTRE HOSPITALIER =====");

        // Connexion via Login et Mot de Passe
        System.out.print("Entrez votre identifiant : ");
        String login = scanner.nextLine();

        System.out.print("Entrez votre mot de passe : ");
        String mdp = scanner.nextLine();

        // Vérification simple
        //Si le login et mdp existe dans la bdd
        System.out.println("Connexion reussie !");
        
        //Autrement le refaire
        System.out.println("Identifiants incorrects. refaire la connexion.");
        

        // Réservation des Véhicules
        System.out.println("\n===== RESERVATION =====");
        System.out.print("Combien de vehicules souhaitez-vous reserver ? ");
        System.out.println(); // ligne vide pour aérer
        //Ajout des véhicules 
        //Avec type, heure, priorité etc ....

        // Vérification de la disponibilité du véhicule
        System.out.println("\n===== VERIFICATION DE DISPONIBILITE =====");
        //Fonction de vérif de la base de données
        //Afin de vérifier si le véhicule est présent et disponible ou bien prit
        
        //Si le véhicule est dipso
        System.out.println("Ce vehicule "  + " est disponible.");
        
        System.out.println("\nVotre reservation a ete validee pour les vehicules suivants :");
        //TO STRING des véhicules les affichant en réservation 
        //Avec le nom, le prénom et les véhicules pour telle heure
        
        System.out.println("Desole ce vehicule "  + " n est pas disponible.");
        //Autrement
        
        //Refaire la demande au besoin
        System.out.println("Aucun vehicule disponible. Merci de reessayer plus tard.");
        

        // Demande de modification de la réservation
        //Choix de la modification
        System.out.println("\nSouhaitez-vous modifier votre reservation ? (oui/non)");
        

        //Si la personne souhaite modifier 
        //activer la fonction de modification des choix 
        //que ça soit au niveau du véhicule 
        //ou de l'heure de réservation
        
        //Si le choix est fait
        System.out.println("\n Reservation mise a jour !");
        //Ou aucun choix
        System.out.println("Aucune modification effectuee.");
        

        // Message Final
        System.out.println("\n===== RESUME FINAL =====");
        System.out.println("Vehicules reserves :");
        //Affichage du nombre de Véhicules via To STRING

        System.out.println("\nMerci d avoir utilise notre service de reservation !");
        System.out.println(" Au revoir !");
        scanner.close();

    }
}

