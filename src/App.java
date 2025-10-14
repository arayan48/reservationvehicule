import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        connection db = new connection();

        System.out.println("CONNEXION");

        System.out.print("Nom: ");
        String nom = s.nextLine();

        System.out.print("Prenom: ");
        String prenom = s.nextLine();

        System.out.print("MDP: ");
        String mdp = s.nextLine();

        db.verifierConnexion(nom, prenom, mdp);

        s.close();
    }
}
