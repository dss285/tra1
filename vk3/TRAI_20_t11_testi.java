package vk3;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Testausluokka TRA I tehtÃ¤vÃ¤Ã¤n 11.
 *
 * TÃ¤mÃ¤ on erikseen vain koska oli nÃ¤ppÃ¤rÃ¤ kopioida ja muokata tehtÃ¤vÃ¤n X3 testiohjelmasta.
 * 
 */
public class TRAI_20_t11_testi {

    static Random rnd = new Random();

    static TRAI_20_t11_pohja testattava = new TRAI_20_t11_pohja(); /* <-- Oma luokan nimi tÃ¤hÃ¤n */


    public static void main(String[] args) {

        // taulukoiden koko
        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);

        // tulostusten mÃ¤Ã¤rÃ¤
        int print = 3;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);

        rnd.setSeed(siemen);

        boolean ok = true;

        // satunnaisia
        ok &= testaaTulosX3(N, 0, N, false, N/2, print);
        ok &= testaaTulosX3(N, 0, N * 2, false, N/2, print);

        // vain pienin
        ok &= testaaTulosX3(N, 0, N, true, 1, print);

        // kaikki paitsi suurin
        ok &= testaaTulosX3(N, 0, N, true, N, print);

        // kaikki
        ok &= testaaTulosX3(N, 0, N, true, N+1, print);


        ok &= testaaTulosX3(1, 1, 1, false, 0, print);
        ok &= testaaTulosX3(1, 1, 1, false, 1, print);
        ok &= testaaTulosX3(1, 1, 1, false, 2, print);
        ok &= testaaTulosX3(2, 1, 2, true, 0, 1);
        ok &= testaaTulosX3(2, 1, 2, true, 1, 1);
        ok &= testaaTulosX3(2, 1, 2, true, 2, 1);
        ok &= testaaTulosX3(2, 1, 2, true, 3, 1);

        if (ok)
            System.out.println("Alun testit antoivat kaikki oikean tuloksen.");

        // asetetaan "satunnainen" satunnaislukusiemen
        rnd.setSeed(System.currentTimeMillis());


        // testataan monta satunnaista syÃ¶tettÃ¤
        int nTest = 1000;
        int k = 0;
        int virheet = 0;
        for (k = 0; k < nTest; k++) {
            if (! testaaTulosX3(N, 0, N, rnd.nextBoolean(), rnd.nextInt(N+3), 0))
                virheet++;
            if (virheet > 30)
                break;
        }
        if (virheet > 0)
            ok = false;
        System.out.println("\n" + k + " testistÃ¤ " + (k-virheet) + " oikein.");

        if (ok)
            System.out.println("\nKaikki tehdyt testit antoivat oikean tuloksen.");
        else
            System.out.println("\nJoissain testeissÃ¤ virheitÃ¤.");


    }

    /**
     * Testaa yhdistettÃ¤ eri kokoisille taulukoille.
     *
     * @param n            1. taulukon alkioiden mÃ¤Ã¤rÃ¤
     * @param min          pienin luku taulukossa 1
     * @param max          suurin luku taulukossa 1
     * @param varmistaRajat varmistetaanko min ja max kuhunkin taulukkoon
     * @param x
     * @param print
     * @return
     */
    static boolean testaaTulosX3(int n, int min, int max, boolean varmistaRajat, Integer x, int print) {

        // generoidaan syÃ¶te
        LinkedList<Integer> A = satunnainenLista(n, min, max, varmistaRajat);
        LinkedList<Integer> cA = new LinkedList<>(A); // kopio syÃ¶tteestÃ¤ talteen

        if (print > 0) System.out.println("\nTesti n="+n + "min="+min + " max="+max + " x="+x);
        // tulostetaan syÃ¶tteet
        if (n < 20 && print > 2 || print > 5) {
            System.out.println("\nA[" + n + "]: " + A + " raja: " + x);
        }

        // kutsutaan testattavaa metodia
        LinkedList<Integer> tul = testattava.siirraPienemmat(A, x);

        // tulostetaan tulos
        if (print > 0) {
            if (n < 20 && print > 2 || print > 5) {
                System.out.println("JÃ¤ljelle jÃ¤i: " + A);
                System.out.println("Siirrettiin : " + tul);
            }
            System.out.println("SyÃ¶te " + n + " kpl -> A " + A.size() + " tulos " + tul.size() +
                    "  yhteensÃ¤ " + (A.size() + tul.size()) + " kpl" );
        }

        boolean ok = true;

        // tarkastetaan, ettÃ¤ alkioiden yhteismÃ¤Ã¤rÃ¤ sÃ¤ilyi
        if (n != A.size() + tul.size()) {
            ok = false;
            if (print > 0) System.out.println("Listojen alkiomÃ¤Ã¤rÃ¤t eivÃ¤t tÃ¤smÃ¤Ã¤!");
        }

        // tarkastetaan, ettÃ¤ jako meni oikein
        ok &= eihanOlePienempia(A, x, print);
        ok &= ovathanPienempia(tul, x, print);

        ok &= vertaaSisallot(cA, A, tul);

        // TODO: ei tarkista onko A:n ja tul jÃ¤rjestys sÃ¤ilynyt


        return ok;

    }


    /**
     * Generoi satunnaisen n kokoisen listan
     *
     * @param n             alkioiden mÃ¤Ã¤rÃ¤
     * @param min           pienin mahdollinen alkio
     * @param max           suurin mahdollinen alkio
     * @param varmistaRajat jos tosi, niin min ja max ovat aina mukana (paitsi jos n<2)
     * @return uusi taulukko.
     */
    static LinkedList<Integer> satunnainenLista(int n, int min, int max, boolean varmistaRajat) {
        LinkedList<Integer> tulos = new LinkedList<Integer>();
        int k = 0;
        if (max < 1)
            max = 1;
        if (n < 2)
            max = min;
        if (varmistaRajat) {
            if (n >= 1) {
                tulos.add(min);
                k++;
            }
            if (n >= 2) {
                tulos.add(rnd.nextInt(2), max);
                k++;
            }
        }
        for (int i = k; i < n; i++) {
            tulos.add(rnd.nextInt(max - min + 1) + min);
        }
        return tulos;
    }

    /**
     * Tarkastaa ettei listassa ole pienempiÃ¤ alkioita kuin x.
     *
     * @param <E>   alkiotyyppi
     * @param A     tarkastettava lista
     * @param x     vertailtava raja-arvo
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return tosi, jos mikÃ¤Ã¤n ei ole pienempi kuin x, muuten epÃ¤tosi
     */
    // TODO: tehoton linkitetylle listalle!
    static public <E extends Comparable<? super E>> boolean eihanOlePienempia(LinkedList<E> A, E x, int print) {
        for (int i = 0; i < A.size() - 1; i++) {
            if (A.get(i) == null) {
                if (print > 1)
                    System.out.println("Listassa A null alkio!");
                return false;
            }
            if (A.get(i).compareTo(x) < 0) {
                if (print > 1)
                    System.out.println("Pienempi alkio jÃ¤ljellÃ¤: A[" + i + "]=" + A.get(i) +
                            " < " + x);
                return false;
            }
        }
        return true;
    }

    /**
     * Tarkastaa ettÃ¤ listan kaikki alkiot ovat pienempiÃ¤ kuin x.
     *
     * @param <E>   alkiotyyppi
     * @param L     tarkastettava lista
     * @param x     vertailtava raja-arvo
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return tosi, jos kaikki ovat pienempiÃ¤, muuten epÃ¤tosi
     */
    // TODO: tehoton linkitetylle listalle!
    static public <E extends Comparable<? super E>> boolean ovathanPienempia(LinkedList<E> L, E x, int print) {
        for (int i = 0; i < L.size() - 1; i++) {
            if (L.get(i) == null) {
                if (print > 1)
                    System.out.println("Listassa L null alkio!");
                return false;
            }
            if (L.get(i).compareTo(x) >= 0) {
                if (print > 1)
                    System.out.println("Suurempi tai yhtÃ¤suuri lÃ¶ytyi: L[" + i + "]=" + L.get(i) +
                            " >= " + x);
                return false;
            }
        }
        return true;
    }



    /**
     * Vertaa, ettÃ¤ onhan listojen A ja B yhteissisÃ¤ltÃ¶ sama kuin listan S.
     * @param S Kaikkien alkioiden lista
     * @param A Osaalkioiden lista
     * @param B Osaalkioiden lista
     * @param <E> alkiotyyppi
     * @return tosi, jos sisÃ¤llÃ¶t tÃ¤smÃ¤Ã¤vÃ¤t, muuten epÃ¤tosi
     */
    static <E> boolean vertaaSisallot(LinkedList<E> S, LinkedList<E> A, LinkedList<E> B) {
        Map<E, Integer> MS = new HashMap<>();
        Map<E, Integer> MAB = new HashMap<>();
        for (E x : S)
            MS.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : A)
            MAB.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : B)
            MAB.compute(x, (k, v) -> v == null ? 1 : v+1);

        // TODO: voisi tulostaa mahdollisia virheitÃ¤

        return MS.equals(MAB);
    }


}
