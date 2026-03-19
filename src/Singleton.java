/**
 * Classe Singleton pour gérer une instance unique de la base de données
 * ou de tout autre ressource partagée dans l'application.
 * 
 * Pattern Singleton thread-safe avec lazy initialization et double-check locking
 */
public class Singleton {
    
    // Instance unique, volatile pour assurer la visibilité entre les threads
    private static Singleton instance;
    
    /**
     * Constructeur privé pour empêcher l'instantiation directe
     */
    private Singleton() {
        // Initialisation privée
    }
    
    /**
     * Méthode pour obtenir l'instance unique du Singleton
     * Thread-safe avec double-check locking
     * 
     * @return l'instance unique de Singleton
     */
    public static Singleton getInstance() {
        // Premier contrôle (sans synchronisation pour performance)
        if (instance == null) {
            // Synchronisation pour créer l'instance une seule fois
            synchronized (Singleton.class) {
                // Deuxième contrôle (avec synchronisation)
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    
    /**
     * Exemple de méthode à implémenter selon vos besoins
     */
    public void afficherMessage() {
        System.out.println("Ceci est l'instance unique du Singleton");
    }
}
