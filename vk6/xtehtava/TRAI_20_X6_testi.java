package tra1.vk6.xtehtava;// TRAI_20_X6_testi.java SJ

import java.util.*;

public class TRAI_20_X6_testi {


    static TRAI_20_X6 testattava = new TRAI_20_X6_tommpuur(); /* <-- Oma tunnus tÃ¤hÃ¤n */
    static int print = 4;

    public static void main(String[] args) {

        int N = 5;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        int seed = N;
        if (args.length > 0)
            seed = Integer.parseInt(args[1]);

        if (args.length > 0)
            print = Integer.parseInt(args[2]);

        Random r = new Random(seed);

        testaa(r, 1, 1, 1, false, 2);    // satunnainen syÃ¶te
        testaa(r, 2, 2, 2, false, 2);    // satunnainen syÃ¶te
        testaa(r, 3, 3, 3, false, 2);    // satunnainen syÃ¶te

        testaa(r, N, N, N, false, 2);    // satunnainen syÃ¶te
        testaa(r, N, N, N, false, 1);    // satunnainen syÃ¶te
        testaa(r, N, N + 1, N + 2, false, 2);    // satunnainen syÃ¶te
        testaa(r, N + 2, N + 1, N, false, 2);    // satunnainen syÃ¶te
        testaa(r, N, N * 2, N * 3, false, 2);    // satunnainen syÃ¶te
        testaa(r, N * 3, N * 2, N, false, 2);    // satunnainen syÃ¶te
        testaa(r, N, N, N, false, 2);    // satunnainen syÃ¶te
        testaa(r, N, N, N, false, 4);    // satunnainen syÃ¶te
        testaa(r, N, N, N, false, 10);    // satunnainen syÃ¶te
        testaa(r, N, N, N, false, 100);    // satunnainen syÃ¶te
        testaa(r, N, N, N, true, 2);        // kaikkiin samat arvot
        testaa(r, N, N, 0, true, 2);    // kahteen samat arvot ja kolmas tyhjÃ¤
        testaa(r, N, 0, N, true, 2);    // kahteen samat arvot ja kolmas tyhjÃ¤
        testaa(r, 0, N, N, true, 2);    // kahteen samat arvot ja kolmas tyhjÃ¤
        testaa(r, N, N, 0, false, 1);    // kahteen jotain ja kolmas tyhjÃ¤
        testaa(r, N, 0, N, false, 1);    // kahteen jotain ja kolmas tyhjÃ¤
        testaa(r, 0, N, N, false, 1);    // kahteen jotain ja kolmas tyhjÃ¤
        testaa(r, 0, 0, N, true, 2);    // kaksi tyhjÃ¤Ã¤, kolmanteen jotain
        testaa(r, 0, 0, 0, true, 2);    // kolme tyhjÃ¤Ã¤

        r.setSeed(System.currentTimeMillis());
        testaa(r, N, N, N, false, 2);    // satunnainen syÃ¶te
        testaa(r, N, N, N, false, 4);    // satunnainen syÃ¶te


    } // main()

    /**
     * Testaa metodia halutun kokoisilla syÃ¶tteillÃ¤.
     * Joukkojen koot voivat olla pienempiÃ¤ jos/kun satunnaislukugeneraattori
     * antaa samoja lukuja uudestaan.
     *
     * @param r     satunnaislukugeneraattori
     * @param n1    S1 koko
     * @param n2    S2 koko
     * @param n3    S3 koko
     * @param samat laitetaanko joukkoisin samoja lukuja vai eri lukuja
     * @param k     kerroin N:lle satunnaisluvun maksimia varten
     */
    static void testaa(Random r, int n1, int n2, int n3, boolean samat, int k) {
        Set<Integer> S1 = new TreeSet<Integer>();
        Set<Integer> S2 = new TreeSet<Integer>();
        Set<Integer> S3 = new TreeSet<Integer>();

        if (samat) {    // samat arvot kaikkiin
            int N = Math.max(Math.max(n1, n2), n3);
            for (int i = 0; i < N; i++) {
                int x = r.nextInt(N * k);
                if (i < n1) S1.add(x);
                if (i < n2) S2.add(x);
                if (i < n3) S3.add(x);
            }

        } else {    // kullekin eri arvoja
            for (int i = 0; i < n1; i++)
                S1.add(r.nextInt(n1 * k));

            for (int i = 0; i < n2; i++)
                S2.add(r.nextInt(n2 * k));

            for (int i = 0; i < n3; i++)
                S3.add(r.nextInt(n3 * k));
        }

        System.out.println("---------------------------------------------\nSyÃ¶tejoukot:");
        System.out.println("S1:          " + S1);
        System.out.println("S2:          " + S2);
        System.out.println("S3:          " + S3);

        Set<Integer> vrt = vainYhdessaKuvauksella(S1, S2, S3);

        Set<Integer> tulos = testattava.vainYhdessa(S1, S2, S3);

        // System.out.println("vainYhdessÃ¤: " + tulos);
		System.out.println("vainYhdessÃ¤: " + (new TreeSet<>(tulos)));	// jos haluat lajiteltuna tuloksen

        System.out.println("verrokki   : " + (new TreeSet<>(vrt)));	    // jos haluat lajiteltuna tuloksen

        if (vrt.equals(tulos))
            System.out.println("SisÃ¤ltÃ¶ tÃ¤smÃ¤Ã¤, hienoa");
        else
            System.out.println("SisÃ¤llÃ¶issÃ¤ eroa");


        if (print > 2) {

            System.out.println("SyÃ¶tejoukot kutsun jÃ¤lkeen (tarkasta etteivÃ¤t muuttuneet):");
            System.out.println("S1:          " + S1);
            System.out.println("S2:          " + S2);
            System.out.println("S3:          " + S3);
        }

	
    }
    /**
     * Vain yhdessÃ¤ -yhdiste kÃ¤yttÃ¤en kuvausta.
     * TÃ„MÃ„ SIIS TEKEE SAMAN KUIN X6 VAADITAAN, MUTTA X6:SSA ON NYT KIELLETTY KUVAUKSEN KÃ„YTTÃ–
     * JOTEN TÃ„STÃ„ EI KANNATA OTTAA MALLIA
     * Luo ja palauttaa uuden joukon jossa on ne alkiot jotka ovat
     * tasan yhdessÃ¤ kolmesta syÃ¶tejoukosta.
     * Jos mikÃ¤Ã¤n alkio ei tÃ¤ytÃ¤ ehtoa, palautetaan tyhjÃ¤ joukko.
     * Ei muuta syÃ¶tejoukkoja.
     *
     * @param S1 syÃ¶tejoukko
     * @param S2 syÃ¶tejoukko
     * @param S3 syÃ¶tejoukko
     * @param <E> alkiotyyppi
     * @return tulosjoukko
     */
    public static <E> Set<E> vainYhdessaKuvauksella(Set<E> S1, Set<E> S2, Set<E> S3) {
        HashMap<E, Integer> HM = new HashMap<>((S1.size()+S2.size()+S3.size()*2));
        for (E x : S1) HM.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : S2) HM.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : S3) HM.compute(x, (k, v) -> v == null ? 1 : v+1);
        Iterator<Map.Entry<E, Integer>> i = HM.entrySet().iterator();
        while (i.hasNext())
            if (i.next().getValue() > 1)
                i.remove();
        return HM.keySet();
    }

} // class TRAI_19_X6_testi
