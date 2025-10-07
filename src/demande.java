public class demande {
    private String datereserv;
    private int numero;
    private String datedebyt;
    private String matricule;
    private String notype;
    private String immat;
    private int duree;
    private String dateretoureffectif;
    private String etat;

    public demande(String datereserv, int numero, String datedebyt, String matricule, String notype, String immat, int duree, String dateretoureffectif, String etat) {
        this.datereserv = datereserv;
        this.numero = numero;
        this.datedebyt = datedebyt;
        this.matricule = matricule;
        this.notype = notype;
        this.immat = immat;
        this.duree = duree;
        this.dateretoureffectif = dateretoureffectif;
        this.etat = etat;
    }

    public String getDatereserv() {
        return datereserv;
    }

    public void setDatereserv(String datereserv) {
        this.datereserv = datereserv;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDatedebyt() {
        return datedebyt;
    }

    public void setDatedebyt(String datedebyt) {
        this.datedebyt = datedebyt;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNotype() {
        return notype;
    }

    public void setNotype(String notype) {
        this.notype = notype;
    }

    public String getImmat() {
        return immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getDateretoureffectif() {
        return dateretoureffectif;
    }

    public void setDateretoureffectif(String dateretoureffectif) {
        this.dateretoureffectif = dateretoureffectif;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "demande [datereserv=" + datereserv + ", numero=" + numero + ", datedebyt=" + datedebyt + ", matricule="
                + matricule + ", notype=" + notype + ", immat=" + immat + ", duree=" + duree + ", dateretoureffectif="
                + dateretoureffectif + ", etat=" + etat + "]";
    }

    

}
