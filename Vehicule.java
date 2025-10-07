/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservation_vehicule;

/**
 *
 * @author pc128
 */
public class Vehicule extends Type {
    private int immat;
    private String marque;
    private String modele;

    public Vehicule(int immat, String marque, String modele) {
        super(numero, libelle);
        this.immat = immat;
        this.marque = marque;
        this.modele = modele;
    }

    public int getImmat() {
        return immat;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public void setImmat(int immat) {
        this.immat = immat;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
   
    
}
