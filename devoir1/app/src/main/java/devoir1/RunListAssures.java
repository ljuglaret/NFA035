package devoir1;

import java.util.*;

public class RunListAssures {

    /*
     * *********************************************SAISIES*************************
     * *******************
     */

    /*
     * Chaque remboursement est une date au format : Jour-Mois-Annee/ suivie du
     * montant correspondant
     */
    public static String saisieRemboursement() {
        Scanner scInt = new Scanner(System.in);

        System.out.println("saisie jour : ");
        int jour = scInt.nextInt();

        System.out.println("saisie mois : ");
        int mois = scInt.nextInt();

        System.out.println("saisie annee : ");
        int annee = scInt.nextInt();

        System.out.println("saisie montant : ");
        int montant = scInt.nextInt();

        return jour + "-" + mois + "-" + annee + "/" + montant;
    }

    /* L'identifiant de l'assuré est un entier contenu sur 6 caractères */
    public static int saisieID() {
        try {
            Scanner scSaisieID = new Scanner(System.in);
            int id = scSaisieID.nextInt();
            if (Integer.toString(id).length() == 6) {
                return id;
            } else {
                System.out.println("L'identifiant est sur 6 caracteres , nouvelle saisie : ");
                return saisieID();
            }
        } catch (InputMismatchException e) {
            System.out.println("L'identifiant doit etre un entier, nouvelle saisie : ");
            return saisieID();
        }
    }

    /*
     * *****************************************************************************
     * *******************
     */

    /*
     * Question a) : Demander à l'utilisateur de rentrer la liste des assurés , avec
     * leurs numéros d'identification et leurs remboursements
     */
    public static ListeAssures ajouterNouveauxAssures(ListeAssures l) {
        Scanner scInt = new Scanner(System.in);
        System.out.println("entrez nb d assures");
        int nombreAssures = scInt.nextInt();
        for (int i = 0; i < nombreAssures; i++) {
            System.out.println("Enregistrement des informations concernant l'assure numero " + (i + 1) + " : ");
            ajouterNouvelAssure(l);
            System.out.println();
        }
        return l;
    }

    public static ListeAssures ajouterNouvelAssure(ListeAssures l) {
        Scanner scString = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);

        System.out.print("Entrez id : ");
        int id = saisieID();
        System.out.print("Entrez  nombre de remboursements : ");
        int nombreDeRemboursements = scInt.nextInt();
        System.out.println("Saisie des remboursements : ");
        String[] remboursements = new String[nombreDeRemboursements];
        for (int i = 0; i < nombreDeRemboursements; i++) {
            System.out.println("remboursement numero " + (i + 1));
            remboursements[i] = saisieRemboursement();
        }

        System.out.println("Quel est le nom de l assure numero : " + id);
        String nom = scString.nextLine();

        l.nouvelAssure(id, nom, remboursements);
        return l;
    }

    /*
     * Question b) : Afficher les données rentrées
     */
    public static void afficheDonnees(ListeAssures l) {
        l.afficheTout();
    }

    /*
     * Question c) Demander à l'utilisateur s'il souhaite rajouter un remboursement
     * à un assuré déjà répertorié . Le programme principal affichera la liste
     * éventuellement modifiée .
     */
    public static void ajouterRemboursement(ListeAssures l) {
        Scanner scString = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        System.out.println("fournir id de l assure , puis remboursement");
        int id = scInt.nextInt();
        String remboursement = saisieRemboursement();
        boolean present = l.ajoutRemboursement(id, remboursement);
        if (present) {
            ASSURE a = new ASSURE(id);
            l.ajoutRemboursement(id, remboursement);
            a.afficheRemboursements();
        } else {
            System.out.println("il n'y aucun assuré correspondant à l'id " + id);
        }
    }

    /*
     * Question d : Demander un nom d'assuré , avec son numéro d'identification et
     * afficher la liste des remboursements qu'il doit recevoir
     */
    public static void afficheRemboursementsDunAssure(ListeAssures l) {
        Scanner scInt = new Scanner(System.in);
        Scanner scString = new Scanner(System.in);

        System.out.println("Saisie d un nom : ");
        String nom = scString.nextLine();

        System.out.println("Saisie d un identifiant : ");
        int id = scInt.nextInt();

        l.afficheRemboursementsDeAssure(id, nom);
    }

    /*
     * Question e) Demander à l'utilisateur de rentrer un montant en euros et
     * afficher les assurés dont la somme des remboursements dépasse ce montant .
     */
    public static void remboursementsSup(ListeAssures listeAssures) {
        System.out.println("Entrez la borne inférieure");
        Scanner scD = new Scanner(System.in);
        double borneMin = scD.nextDouble();
        listeAssures.afficheSiCondition(borneMin);
    }

    static String menuListe = "-------------MENU------------" + "\n => 1 pour saisir la liste des assures"
            + "\n => 2 pour afficher les donnees" + "\n => 3 pour ajouter un remboursement"
            + "\n => 4 pour afficher un remboursement"
            + "\n => 5 pour afficher les remboursements sup à un certain montant" + "\n => -1 pour sortir";

    public static void menu() {
        ListeAssures l = new ListeAssures();
        Scanner scInt = new Scanner(System.in);
        System.out.println(menuListe);
        boolean listeSaisie = false;
        int choix = scInt.nextInt();
        while (choix >= 1 && choix <= 5) {
            switch (choix) {
                case 1:
                    if (listeSaisie) {
                        System.out.println("la liste des assures a deja ete saisie");
                        l.afficheListeAssures();
                        break;
                    } else {
                        ajouterNouveauxAssures(l);
                        listeSaisie = true;
                        break;
                    }
                case 2:
                    afficheDonnees(l);
                    break;
                case 3:
                    ajouterRemboursement(l);
                    break;
                case 4:
                    afficheRemboursementsDunAssure(l);
                    break;
                case 5:
                    remboursementsSup(l);
                    break;

                default:
                    System.out.println("Sortie du menu");
                    break;

            }
            System.out.println(menuListe);
            choix = scInt.nextInt();
        }

    }

    public static void main(String[] args) {
        RunListAssures.menu();

    }

}