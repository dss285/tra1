package tra1.vk4.xtehtava;

import java.util.*;

/**
 * Testausluokka TRA I tehtÃ¤vÃ¤Ã¤n X4.
 */
public class TRAI_20_X4_testi {

    static Random rnd = new Random();

    static TRAI_20_X4 testattava = new TRAI_20_X4_tommpuur(); /* <-- Oma tunnus tÃ¤hÃ¤n */


    public static void main(String[] args) {

        // taulukoiden koko
        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = 43;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);

        // tulostusten mÃ¤Ã¤rÃ¤, kasvata tai vÃ¤hennÃ¤ mielesi mukaan
        int print = 3;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);

        rnd.setSeed(siemen);

        boolean ok = true;

        System.out.println("\n== satunnaisia");
        ok &= testaaTulosX4(N, N, 0, 0, N, N, false, 0, print);
        ok &= testaaTulosX4(N, N, 0, 0, N * 2, N * 5, false, 0, print);

        System.out.println("\n== paljon yhteisiÃ¤ alkioita");
        ok &= testaaTulosX4(N, N, 0, 0, N / 2, N / 2, false, 0, print);
        ok &= testaaTulosX4(N, N, 0, 0, N / 3, N / 3, false, 0, print);

        System.out.println("\n== samat listat");
        ok &= testaaTulosX4(N, N, 0, 0, N, N, false, 1, print);
        ok &= testaaTulosX4(N, N, 0, 0, N * 2, N * 2, false, 1, print);

        System.out.println("\n== toisessa pienempi alkio, mutta silti limittÃ¤in");
        ok &= testaaTulosX4(N, N, 0, 2, N, N + 2, true, 0, print);
        ok &= testaaTulosX4(N, N, 2, 0, N + 2, N, true, 0, print);

        System.out.println("\n== toisen suurin on toisen pienin");
        ok &= testaaTulosX4(N, N, 0, N, N, N * 2, true, 0, print);
        ok &= testaaTulosX4(N, N, N, 0, N * 2, N, true, 0, print);

        System.out.println("\n== toisen suurin on pienempi kuin toisen pienin");
        ok &= testaaTulosX4(N, N, 0, N + 1, N, N * 2, true, 0, print);
        ok &= testaaTulosX4(N, N, N + 1, 0, N * 2, N, true, 0, print);

        System.out.println("\n== lyhyitÃ¤ listoja");
        ok &= testaaTulosX4(1, 1, 0, 0, N, N, false, 0, print);
        ok &= testaaTulosX4(1, 2, 0, 0, 1, 1, false, 0, print);
        ok &= testaaTulosX4(2, 1, 0, 0, 3, 2, false, 0, print);
        ok &= testaaTulosX4(2, 0, 0, 0, N, N, false, 0, print);
        ok &= testaaTulosX4(0, 1, 0, 0, N, N, false, 0, print);
        ok &= testaaTulosX4(1, 1, 0, 0, N, N, false, 0, print);
        ok &= testaaTulosX4(0, 0, 0, 0, N, N, false, 0, print);

        System.out.println("\n== suurempia lukuja");
        ok &= testaaTulosX4(N, N, 1000, 1000, 1000 + N, 1000 + N, false, 0, print);
        ok &= testaaTulosX4(N, N, 1000, 1000, 1000 + N * 2, 1000 + N * 3, false, 0, print);


        if (ok)
            System.out.println("Alun testit antoivat kaikki oikean tuloksen.");

        System.out.println("\n== testataan myÃ¶s merkkijonoilla");

        ok &= testaaTulosX4mjono(N, N, 1, print);
        ok &= testaaTulosX4mjono(N * 2, N * 2, 1, print);
        ok &= testaaTulosX4mjono(N / 2, N, 1, print);
        ok &= testaaTulosX4mjono(N / 2, N / 2, 1, print);
        ok &= testaaTulosX4mjono(N, N, 2, print);
        ok &= testaaTulosX4mjono(N, N, 2, print);
        if (ok)
            System.out.println("Toimii myÃ¶s merkkijonoilla.");

        // asetetaan "satunnainen" satunnaislukusiemen
        rnd.setSeed(System.currentTimeMillis());

        System.out.println("\n== testataan monta satunnaista syÃ¶tettÃ¤");
        int nTest = 1000;
        int k = 0;
        int virheet = 0;
        for (k = 0; k < nTest; k++) {
            if (!testaaTulosX4(N, N, 0, 0, N, N, rnd.nextBoolean(), 0, 0))
                virheet++;
            if (virheet >= 30) {
                k++;
                break;
            }
        }
        if (virheet > 0)
            ok = false;
        System.out.println("\n" + k + " testistÃ¤ " + (k - virheet) + " oikein.");

        if (ok)
            System.out.println("\nKaikki tehdyt testit antoivat oikean tuloksen.\nMuista myÃ¶s itsearviointi ja aikavaativuus.");
        else
            System.out.println("\nJoissain testeissÃ¤ virheitÃ¤.");


    }

    /**
     * Testaa yhdistettÃ¤ eri kokoisille taulukoille.
     *
     * @param n1            1. taulukon alkioiden mÃ¤Ã¤rÃ¤
     * @param n2            2. taulukon alkioiden mÃ¤Ã¤rÃ¤
     * @param min1          pienin luku taulukossa 1
     * @param min2          pienin luku taulukossa 2
     * @param max1          suurin luku taulukossa 1
     * @param max2          suurin luku taulukossa 2
     * @param varmistaRajat varmistetaanko min ja max kuhunkin taulukkoon
     * @param arvonta       0 on arvottu, 1 = A ja B samat sisÃ¤llÃ¶t, ...
     * @param print         tulostuksen mÃ¤Ã¤rÃ¤
     * @return true jos testit menivÃ¤t hyvin, muuten false
     */
    static boolean testaaTulosX4(int n1, int n2, int min1, int min2,
                                 int max1, int max2, boolean varmistaRajat, int arvonta, int print) {

        // generoidaan syÃ¶tteet
        LinkedList<Integer> A;
        A = satunnainenKasvavaLista(n1, min1, max1, varmistaRajat);
        LinkedList<Integer> B;
        if (arvonta == 1)   // identtiset listat
            B = new LinkedList<>(A);
        else
            B = satunnainenKasvavaLista(n2, min2, max2, varmistaRajat);

        // kopiot syÃ¶tteistÃ¤ talteen
        LinkedList<Integer> cA = new LinkedList<>(A);
        LinkedList<Integer> cB = new LinkedList<>(B);

        // tulostetaan syÃ¶tteet
        if (print > 0) System.out.println("\nTESTI n1=" + n1 + " n2=" + n2 + " min1=" + min1 +
                " min2=" + min2 + " max1=" + max1 + " max2=" + max2);
        if ((n1 < 20 && n2 < 20 && print > 2) || print > 5) {
            System.out.println("A[" + n1 + "]: " + A);
            System.out.println("B[" + n2 + "]: " + B);
        }

        // kutsutaan testattavaa metodia
        LinkedList<Integer> tul = testattava.jarjestettyjenVainToisessaYhdiste(A, B);

        // tulostetaan tulos
        if ((tul.size() < 22 && print > 1) || print > 5)
            System.out.println("Tulos (jokotai): " + tul);

        boolean ok = true;

        ok &= onkoJarjestyksessa(tul, print);

        boolean sisaltoSailynyt = A.equals(cA) && B.equals(cB);
        if (!sisaltoSailynyt && print > 0)
            System.out.println("Virhe: metodi muuttaa listan sisÃ¤ltÃ¶Ã¤!");

        // verrataan tulosta verrokkiyhdisteeseen
        // vertaa() metodissa L = tulos, V = verrokki
        LinkedList<Integer> vrt = jarjestettyjenVainToisessaYhdisteKuvauksella(A, B);
        ok &= vertaa(tul, vrt, print);

        // tulostetaan myÃ¶s verrokki haluttaessa
        //if ((tul.size() < 20 && print > 2) || print > 6)
        //    System.out.println("Verrokki: " + vrt);

        return ok && sisaltoSailynyt;

    }

    /**
     * Testaa X4 merkkijonoilla
     *
     * @param n1    1. taulukon alkioiden mÃ¤Ã¤rÃ¤
     * @param n2    2. taulukon alkioiden mÃ¤Ã¤rÃ¤
     * @param len   merkkijonojen pituus
     * @param print tulostuksen mÃ¤Ã¤rÃ¤
     * @return true jos testit menivÃ¤t hyvin, muuten false
     */
    static boolean testaaTulosX4mjono(int n1, int n2, int len, int print) {

        // generoidaan syÃ¶tteet

        LinkedList<String> A = satunnainenKasvavaLista(n1, len);
        LinkedList<String> B = satunnainenKasvavaLista(n2, len);

        // tulostetaan syÃ¶tteet
        if (print > 0) System.out.println("\nTESTI MJONO n1=" + n1 + " n2=" + n2 + " len=" + len);
        if ((n1 < 20 && n2 < 20 && print > 2) || print > 5) {
            System.out.println("A[" + n1 + "]: " + A);
            System.out.println("B[" + n2 + "]: " + B);
        }

        // kutsutaan testattavaa metodia
        LinkedList<String> tul = testattava.jarjestettyjenVainToisessaYhdiste(A, B);

        // tulostetaan tulos
        if ((tul.size() < 22 && print > 1) || print > 5)
            System.out.println("Yhdiste: " + tul);

        boolean ok = true;

        ok &= onkoJarjestyksessa(tul, print);


        // verrataan tulosta verrokkiyhdisteeseen
        LinkedList<String> vrt = jarjestettyjenVainToisessaYhdisteKuvauksella(A, B);
        ok &= vertaa(tul, vrt, print);

        return ok;

    }


    /**
     * Generoi satunnaisen n kokoisen kasvavassa jÃ¤rjestyksessÃ¤ olevan listan
     *
     * @param n             alkioiden mÃ¤Ã¤rÃ¤
     * @param min           pienin mahdollinen alkio
     * @param max           suurin mahdollinen alkio
     * @param varmistaRajat jos tosi, niin min ja max ovat aina mukana (paitsi jos n<2)
     * @return uusi lista.
     */
    static LinkedList<Integer> satunnainenKasvavaLista(int n, int min, int max, boolean varmistaRajat) {
        LinkedList<Integer> tulos = new LinkedList<>();
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
                tulos.add(max);
                k++;
            }
        }
        for (int i = k; i < n; i++) {
            tulos.add(rnd.nextInt(max - min + 1) + min);
        }
        Collections.sort(tulos);
        return tulos;
    }

    /**
     * Generoi satunnaisen n kokoisen kasvavassa jÃ¤rjestyksessÃ¤ olevan merkkijonolista
     *
     * @param n   alkioiden mÃ¤Ã¤rÃ¤
     * @param len merkkijonojen pituus
     * @return uusi lista
     */
    static LinkedList<String> satunnainenKasvavaLista(int n, int len) {
        LinkedList<String> tulos = new LinkedList<>();
        int k = 0;
        for (int i = k; i < n; i++) {
            tulos.add(randomString(rnd, len));
        }
        Collections.sort(tulos);
        return tulos;
    }

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
     * Tarkastaa onko annettu kokonaislukulista kasvavassa jÃ¤rjestyksessÃ¤.
     *
     * @param A     tarkastettava lista
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return tosi, jos on kasvava jÃ¤rjestys, muuten epÃ¤tosi
     */
    static public <E extends Comparable<? super E>> boolean onkoJarjestyksessa(LinkedList<E> A, int print) {

        ListIterator<E> i = A.listIterator();
        E prev = i.hasNext() ? i.next() : null;
        while (i.hasNext()) {
            E x = i.next();
            if (prev == null || prev.compareTo(x) > 0) {
                if (print > 1)
                    System.out.println("VÃ¤Ã¤rÃ¤ jÃ¤rjestys: " + prev + " on ennen " + x);
                return false;
            }
            prev = x; // lisÃ¤tty 22.9.
        }
        if (print > 1)
            System.out.println("Kasvavassa jÃ¤rjestyksessÃ¤ ok");
        return true;
    }


    /**
     * Vertaa kahden listan sisÃ¤ltÃ¶Ã¤.
     *
     * @param L     verrattava
     * @param V     verrokki
     * @param print tulostuksen mÃ¤Ã¤rÃ¤
     * @return tosi jos samat alkiot samassa jÃ¤rjestyksessÃ¤, muuten epÃ¤tosi
     */
    static public <E extends Comparable<? super E>> boolean vertaa(LinkedList<E> L, LinkedList<E> V, int print) {

        if (print > 1 && L.size() <= 20 && V.size() <= 20) {
            System.out.println("Vertailu");
            System.out.println("L=" + L);
            System.out.println("V=" + V);
        }


        if (L.size() != V.size()) {
            System.out.println("Eri kokoiset: T:" + L.size() + "  V:" + V.size());
            if (L.size() > V.size())
                System.out.println("Tuloslistassa enemmÃ¤n alkioita kuin verrokissa");
            else
                System.out.println("Tuloslistassa vÃ¤hemmÃ¤n alkioita kuin verrokissa");
            return false;
        }

        if (!L.containsAll(V) || !V.containsAll(L)) {
            System.out.println("SisÃ¤llÃ¶t eivÃ¤t tÃ¤smÃ¤Ã¤");
            return false;
        }

        if (print > 1)
            System.out.println("Vertailu ok");

        return true;
    }

    /**
     * JÃ¤rjestettyjen listojen joko-tai-yhdiste.
     * TÃ„MÃ„ SIIS TEKEE SAMAN KUIN X4, MUTTA KÃ„YTTÃ„EN KIELLETTYÃ„ MENETELMÃ„Ã„ (KUVAUSTA)
     * Muodostaa uusen jÃ¤rjestetyn listan niistÃ¤ syÃ¶telistojen alkioista jotka esiintyvÃ¤t vain
     * toisessa syÃ¶telistoista.
     * Tarkkaanottaen ei kÃ¤ytÃ¤ samoja alkioita (kuten oikea ratkaisu tekisi) vaan samanlaisia alkioita.
     * Testauksessa muuttamattomilla alkioilla (Integer, String) tÃ¤llÃ¤ ei ole merkitystÃ¤.
     * Ei muuta syÃ¶telistoja.
     *
     * @param A   syÃ¶telista, kasvavassa jÃ¤rjestyksessÃ¤
     * @param B   syÃ¶telista, kasvavassa jÃ¤rjestyksessÃ¤
     * @param <E> alkiotyyppi, toteuttaa Comparable-rajapinnan
     * @return uusi lista jossa kasvavassa jÃ¤rjestyksessÃ¤ ne alkiot jotka ovat vain yhdessÃ¤ syÃ¶telistassa
     */
    static public <E extends Comparable<? super E>> LinkedList<E>
                jarjestettyjenVainToisessaYhdisteKuvauksella(LinkedList<E> A, LinkedList<E> B) {

        TreeMap<E, Integer> MA = new TreeMap<>();
        TreeMap<E, Integer> MB = new TreeMap<>();
        for (E x : A)
            MA.compute(x, (k, v) -> v == null ? 1 : v + 1);
        for (E x : B)
            MB.compute(x, (k, v) -> v == null ? 1 : v + 1);

        TreeMap<E, Integer> MT = new TreeMap<>();
        for (Map.Entry<E, Integer> e : MA.entrySet())
            if (!MB.containsKey(e.getKey()))
                MT.put(e.getKey(), e.getValue());
        for (Map.Entry<E, Integer> e : MB.entrySet())
            if (!MA.containsKey(e.getKey()))
                MT.put(e.getKey(), e.getValue());

        LinkedList<E> tulos = new LinkedList<>();
        for (Map.Entry<E, Integer> e : MT.entrySet())
            for (int i = 0; i < e.getValue(); i++)
                tulos.add(e.getKey());

        return tulos;
    }


}
