package devoir1;

import java.util.*;

public class ListeAssures {

    private HashMap<Integer, ASSURE> listeAssures;

    public ListeAssures() {
        listeAssures = new HashMap<Integer, ASSURE>();
    }

    public void nouvelAssure(int id, String nom, String[] remboursements) {
        ASSURE a = new ASSURE(id);
        a.ajoutNom(nom);
        for (String remboursement : remboursements) {
            a.ajoutRemboursement(remboursement);
        }
        listeAssures.put(id, a);
    }

    /************************************
     * Affichage
     ************************************/

    public void afficheRemboursementsDeAssure(int numeroassure, String nomAssure) {
        int temp;
        ASSURE A;
        int ID;
        String nom;
        Set<Integer> cle = listeAssures.keySet();
        Iterator<Integer> suivant = cle.iterator();
        boolean paireNonTrouvee = true;
        while (suivant.hasNext()) {
            temp = suivant.next();
            A = listeAssures.get(temp);
            ID = A.getId();
            nom = A.getNom();
            if (numeroassure == ID && nom.equals(nomAssure)) {
                paireNonTrouvee = false;
                System.out.println("Ses remboursements sont");
                A.affiche();
            }
        }
        if (paireNonTrouvee) {
            System.out.println("La paire " + numeroassure + " et " + nomAssure + " n'existe pas");
        }
    }

    public void afficheNomDeClient(int numeroassure) {
        int temp;
        ASSURE A;
        int ID;
        Set<Integer> cle = listeAssures.keySet();
        Iterator<Integer> suivant = cle.iterator();
        while (suivant.hasNext()) {
            temp = suivant.next();
            A = listeAssures.get(temp);
            ID = A.getId();
            if (numeroassure == ID) {
                A.afficheNom();
            }
        }
    }

    public void afficheListeAssures() {
        int temp;
        ASSURE A;
        Set<Integer> cles = listeAssures.keySet();
        Iterator<Integer> suivant = cles.iterator();
        while (suivant.hasNext()) {
            temp = suivant.next();
            A = listeAssures.get(temp);
            System.out.println(A.getId() + " " + A.getNom());
        }
        System.out.println("");
    }

    public void afficheTout() {
        int temp;
        ASSURE A;
        Set<Integer> cles = listeAssures.keySet();
        Iterator<Integer> suivant = cles.iterator();
        System.out.println(" ");
        System.out.println("Liste des assures avec les remboursements : ");
        while (suivant.hasNext()) {
            temp = suivant.next();
            A = listeAssures.get(temp);
            A.affiche();
        }
        System.out.println("");
    }

    public void afficheSiCondition(double borneMin) {
        int temp;
        ASSURE W;
        Set<Integer> cle = listeAssures.keySet();
        Iterator<Integer> suivant = cle.iterator();
        System.out.println(" ");
        System.out.println("Liste des assures dont la somme des remboursements est supérieure á  " + borneMin + "  : ");
        while (suivant.hasNext()) {
            temp = suivant.next();
            W = listeAssures.get(temp);
            W.afficheSiCondition(borneMin);
        }
        System.out.println(" ");
    }

    /***********************************************************************************/

    public boolean ajoutRemboursement(int id, String compte) {
        int temp;
        boolean b;
        ASSURE W = null;
        int NOM = 0;
        Set<Integer> cle = listeAssures.keySet();
        Iterator<Integer> suivant = cle.iterator();
        b = false;
        while (suivant.hasNext()) {
            temp = suivant.next();
            W = listeAssures.get(temp);
            NOM = W.getId();
            if (NOM == id) {
                W.ajoutRemboursement(compte);
                b = true;
            }
        }
        return b;

    }
}