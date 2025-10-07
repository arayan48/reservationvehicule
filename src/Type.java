/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projethopitalvehicule;

/**
 *
 * @author Chetouani
 */
public class Type {
    //Attributs
    private int numero;
    private String libelle;

    public Type(int numero, String libelle) {
        this.numero = numero;
        this.libelle = libelle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Type{" + "numero=" + numero + ", libelle=" + libelle + '}';
    }
    
    
    
}
