package devoir1;

import java.util.*;

public class ASSURE {
    private String nom;
    private int numero;
    private Set<String> remboursements;

    public ASSURE(int id) {
        numero = id;
        remboursements = new HashSet<String>();
        nom = "";
    }

    public void ajoutRemboursement(String remboursement) {
        remboursements.add(remboursement);
    }

    public void ajoutNom(String nomAssure) {
        nom = nomAssure;
    }

    public int getId() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public double valeurRemboursement(String remboursement) {
        String[] remboursementParse = remboursement.split("/");
        return (Double.parseDouble(remboursementParse[1]));
    }

    public void afficheSiCondition(double borneInf) {
        Iterator<String> itr = remboursements.iterator();
        double sommeRemboursements = 0;
        while (itr.hasNext()) {
            String o = itr.next();
            sommeRemboursements += valeurRemboursement(o);
        }
        if (sommeRemboursements > borneInf) {
            System.out.print(getId());
            System.out.println(" :  somme = " + sommeRemboursements + "euros");
        }
    }

    public void affiche() {
        System.out.println(getId() + " - " + getNom());
        afficheRemboursements();
    }

    public void afficheNom() {
        System.out.println(nom);
    }

    public void afficheRemboursements() {
        Iterator<String> itr = remboursements.iterator();
        while (itr.hasNext()) {
            String o = itr.next();
            System.out.println(" " + o + "euros");
        }
    }
}
