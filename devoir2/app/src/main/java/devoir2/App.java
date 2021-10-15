/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package devoir2;

import java.io.IOException;
import java.util.*;
import java.io.*;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static String formate(String ville, int departement) {
        String s1 = ville.toUpperCase();
        String s2 = "*" + Integer.toString(departement);
        return s1 + s2;
    }

    public static void creerFichier() throws IOException {
        Scanner scString = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        String nomfich, ville;
        File f;
        FileWriter FW;
        BufferedWriter BW;
        char choix;
        Set<String> villes = new HashSet<String>();

        System.out.print("Donner le nom du fichier a creer : ");
        nomfich = scString.nextLine();
        f = new File("app/src/main/resources/" + nomfich + ".txt");

        FW = new FileWriter(f);
        BW = new BufferedWriter(FW);
        System.out.print("Voulez vous stocker une ville (O/N) ?");
        choix = scString.nextLine().charAt(0);
        while (choix == 'O') {
            System.out.print("Donner une ville : ");
            ville = scString.nextLine();
            System.out.print("Donner son departement  : ");
            int departement = scInt.nextInt();
            if (choix == 'O') {
                if (!villes.contains(ville)) {
                    String villeFormatee = formate(ville, departement);
                    BW.write(villeFormatee, 0, villeFormatee.length());
                    BW.newLine();
                    villes.add(ville);
                }
            }
            System.out.print("Voulez vous stocker une ville (O/N) ?");
            choix = scString.nextLine().charAt(0);
        }

        BW.close();
        System.out.println("Fin de la creation du fichier");

    }

    /* Lit seulement les lignes dont les villes dont le departement == dpt */
    public static void lireFichier(int dpt) throws IOException {

        Scanner scString = new Scanner(System.in);
        String nomfich = "", ligne;
        boolean correspondanceTrouvee = false;
        File f;
        FileReader FR;
        BufferedReader BR;

        System.out.print("Donner le nom du fichier a lire : ");
        try {
            nomfich = scString.nextLine();
            nomfich += ".txt";
            f = new File("app/src/main/resources/" + nomfich);
            FR = new FileReader(f);
            BR = new BufferedReader(FR);

            do {
                ligne = BR.readLine();
                if (ligne != null) {
                    String[] ligneParseEtoile = ligne.split("\\*");
                    if (ligneParseEtoile[1].equals(Integer.toString(dpt))) {
                        System.out.println(ligne);
                        correspondanceTrouvee = true;
                    }
                }
            } while (ligne != null);
            if (!correspondanceTrouvee) {
                System.out.println(nomfich + " n a aucune occurence du departement " + dpt);
            }
            BR.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier " + nomfich + " introuvable");
        }

    }

    public static void main(String[] args) throws IOException {

        Scanner scChoix = new Scanner(System.in);

        while (true) {

            System.out.println("Tapez 0 pour Saisir des donnees , 1 pour Lire");
            int choix = scChoix.nextInt();

            if (choix == 0) {
                creerFichier();
            } else {
                if (choix == 1) {
                    System.out.println("quel departement cherchez-vous");
                    Scanner scannerDPT = new Scanner(System.in);
                    int dpt = scannerDPT.nextInt();
                    lireFichier(dpt);
                } else {
                    System.exit(-1);
                }
            }
        }
    }
}
