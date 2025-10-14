import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Initialisation
        Scanner s = new Scanner(System.in);
        Passerelle db = new Passerelle();
        menu menu = new menu();

        boolean connecte = menu.menuConnexion(s, db);

        menu.afficherMenu();



        // Fermeture des ressources
        s.close();
        db.closeConnection();
    }
}
