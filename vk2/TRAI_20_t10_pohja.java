package vk2;// TRAI_20_t10_pohja SJ

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Random;

/**
 * 10. Kirjoita algoritmi joka saa parametrinaan kaksi jÃ¤rjestÃ¤mÃ¤tÃ¶ntÃ¤ listaa A ja B (ArrayList)
 * ja joka poistaa listasta A kaikki ne alkiot jotka eivÃ¤t esiinny listassa B. Ã„lÃ¤ kÃ¤ytÃ¤ valmista
 * retainAll() -operaatioita. Aikavaativuus? Miten aikavaativuutta voisi parantaa?
 **/
/**
    Aikavaativuus on O(n^2)
    Aikavaativuutta en tiedä voiko parantaa.



 **/
public class TRAI_20_t10_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_20_10 [N] [N2] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, N2 on alkoiden mÃ¤Ã¤rÃ¤ toisessa taulukossa
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // taulukoiden koko
        int n1 = 10;
        if (args.length > 0)
            n1 = Integer.parseInt(args[0]);

        int n2 = n1 + 2;
        if (args.length > 1)
            n2 = Integer.parseInt(args[1]);

        int pituus = 1; // merkkijonojen pituus
        if (n1 > 30)
            pituus = 2;

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);


        // testataan vaihteeksi merkkijonoilla

        ArrayList<String> L1 = new ArrayList<>(n1);
        ArrayList<String> L2 = new ArrayList<>(n2);

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        Random r = new Random(siemen);
        for (int i = 0; i < n1; i++) {
            L1.add(randomString(r, pituus));
        }

        for (int i = 0; i < n2; i++) {
            L2.add(randomString(r, pituus));
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (n1 <= 20 && n2 <= 20) {
            System.out.println("L1: " + L1);
            System.out.println("L2: " + L2);
        }


        // testataan tehtÃ¤vÃ¤Ã¤ 10

        sailytaKaikki10(L1, L2);

        System.out.print("TehtÃ¤vÃ¤ 10, L1^L2: ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(L1);
        } else {
            System.out.println(L1.size() + " alkiota");
        }

    } // main()


    /**
     * Palauttaa satunnaisen len mittaisen merkkijonon.
     *
     * @param r   satunnaislukugeneraattori
     * @param len merkkijonon pituus
     * @return uusi merkkijono
     */
    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(26) + 'a');
        return new String(C);
    }


    /**
     * Poistaa listasta L1 sellaiset alkiot jotka eivÃ¤t esiinny listassa L2.
     *
     * @param L1 lista josta poistetaan
     * @param L2 alkiot jotka poistetaan
     */
    public static void sailytaKaikki10(ArrayList L1, ArrayList L2) {
        // TODO
        ArrayList poistettavat = new ArrayList<>();
        for(Object o : L1) {
            if(!L2.contains(o)) {
                poistettavat.add(o);
            }
        }
        L1.removeAll(poistettavat);

    }


} // class

